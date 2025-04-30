package com.celc.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.celc.api.dtos.Cadastro.CadastroTrabalhadorRequestDTO;
import com.celc.api.dtos.Cadastro.CadastroTrabalhadorResponseDTO;
import com.celc.api.dtos.Login.LoginRequestDTO;
import com.celc.api.dtos.Login.LoginResponseDTO;
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

    public CadastroTrabalhadorResponseDTO cadastrar(CadastroTrabalhadorRequestDTO cadastroTrabalhadorRequest) {
        try {
            if (cadastroTrabalhadorRequest.getNome() == null || cadastroTrabalhadorRequest.getNome().isBlank()) {
                return new CadastroTrabalhadorResponseDTO(false, "Nome não informado.");
            }

            if (cadastroTrabalhadorRequest.getEmail() == null || cadastroTrabalhadorRequest.getEmail().isBlank()) {
                return new CadastroTrabalhadorResponseDTO(false, "Email não informado.");
            }

            if (cadastroTrabalhadorRequest.getSenha() == null || cadastroTrabalhadorRequest.getSenha().isBlank()) {
                return new CadastroTrabalhadorResponseDTO(false, "Senha não informada.");
            }

            if (trabalhadorRepository.existsByEmail(cadastroTrabalhadorRequest.getEmail())) {
                throw new RuntimeException("Já existe um usuário com este email!");
            }

            try {
                Trabalhador novo = new Trabalhador();
                novo.setNome(cadastroTrabalhadorRequest.getNome());
                novo.setEmail(cadastroTrabalhadorRequest.getEmail());
                novo.setSenha(passwordEncoder.encode(cadastroTrabalhadorRequest.getSenha()));

                // Campo opcional
                if (cadastroTrabalhadorRequest.getNomeEspiritual() != null) {
                    novo.setNomeEspiritual(cadastroTrabalhadorRequest.getNomeEspiritual());
                }
                
                trabalhadorRepository.save(novo);

                return new CadastroTrabalhadorResponseDTO(true, "Usuário cadastrado com sucesso!");
            } catch (Exception e) {
                return new CadastroTrabalhadorResponseDTO(false, "Erro ao cadastrar usuário");
            }
        } catch (Exception e) {
            return new CadastroTrabalhadorResponseDTO(false, "Houve um erro no cadastro");
        }
    }
}
