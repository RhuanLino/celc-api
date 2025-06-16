package com.celc.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String secretKey;

    private final String headerName;

    public JwtTokenAuthenticationFilter(String secretKey, String headerName) {
        this.secretKey = secretKey;
        this.headerName = headerName;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) throws ServletException, IOException {
        
        // 1. Obter o token do cabeçalho Authorization
        String header = request.getHeader(headerName);
        
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.replace("Bearer ", "");

        try {
            // 2. Validar o token e extrair claims
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
            
            Claims claims = Jwts.parser()
                .verifyWith((SecretKey) key)  // Novo método em vez de setSigningKey
                .build()
                .parseSignedClaims(token)  // Novo método em vez de parseClaimsJws
                .getPayload();

            String username = claims.getSubject();
            
            @SuppressWarnings("unchecked")
            List<String> authorities = claims.get("authorities", List.class);
            
            // 3. Criar objeto Authentication
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            );

            // 4. Autenticar o usuário
            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (Exception e) {
            // Em caso de token inválido, limpar o contexto de segurança
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}