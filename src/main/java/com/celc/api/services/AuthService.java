package com.celc.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.celc.api.dtos.LoginRequestDTO;
import com.celc.api.dtos.LoginResponseDTO;
import com.celc.api.models.Trabalhador;
import com.celc.api.repositories.TrabalhadorRepository;

@Service
public class AuthService {
    
    @Autowired
    private TrabalhadorRepository trabalhadorRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponseDTO autenticar(LoginRequestDTO loginRequest) {
        Trabalhador trabalhador = trabalhadorRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        boolean senhaOk = passwordEncoder.matches(loginRequest.getSenha(), trabalhador.getSenha());

        if (!senhaOk) {
            throw new RuntimeException("Senha incorreta");
        }

        String token = jwtUtil.generateToken(trabalhador.getEmail());
        return new LoginResponseDTO(true, "Login bem-sucedido", null, token);
    }
    
}
