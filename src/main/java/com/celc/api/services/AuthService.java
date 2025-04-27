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
        try {
            if (loginRequest.getEmail() == null || loginRequest.getEmail().isBlank()) {
                return new LoginResponseDTO(false, "Email não informado", null);
            }

            if (loginRequest.getSenha() == null || loginRequest.getSenha().isBlank()) {
                return new LoginResponseDTO(false, "Senha não informada", null);
            }

            Trabalhador trabalhador = trabalhadorRepository.findByEmail(loginRequest.getEmail())
                .orElse(null);

            if (trabalhador == null) {
                return new LoginResponseDTO(false, "Usuário não encontrado", null);
            }
            
            boolean senhaOk = passwordEncoder.matches(loginRequest.getSenha(), trabalhador.getSenha());
            
            if (!senhaOk) {
                throw new RuntimeException("Senha incorreta");
            }

            String token;
            try {
                token = jwtUtil.generateToken(trabalhador.getEmail());
            } catch (Exception e) {
                return new LoginResponseDTO(false, "Erro ao gerar token: " + e.getMessage(), null);
            }

            return new LoginResponseDTO(true, "Login bem-sucedido", token);
        } catch (Exception e) {
            return new LoginResponseDTO(false, "Erro ao autenticar", null);
        }
    }
    
}
