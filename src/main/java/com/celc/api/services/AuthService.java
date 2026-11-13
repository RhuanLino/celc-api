package com.celc.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.celc.api.dtos.Cadastro.CadastroUsuarioRequestDTO;
import com.celc.api.dtos.Cadastro.CadastroUsuarioResponseDTO;
import com.celc.api.dtos.Login.LoginRequestDTO;
import com.celc.api.dtos.Login.LoginResponseDTO;
import com.celc.api.models.Usuario;
import com.celc.api.repositories.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

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

            Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
                    .orElse(null);

            if (usuario == null) {
                return new LoginResponseDTO(false, "Usuário não encontrado", null);
            }

            boolean senhaOk = passwordEncoder.matches(loginRequest.getSenha(), usuario.getSenha());

            if (!senhaOk) {
                throw new RuntimeException("Senha incorreta");
            }

            String token;
            try {
                token = jwtUtil.generateToken(usuario.getEmail());
            } catch (Exception e) {
                return new LoginResponseDTO(false, "Erro ao gerar token: " + e.getMessage(), null);
            }

            return new LoginResponseDTO(true, "Login bem-sucedido", token);
        } catch (Exception e) {
            return new LoginResponseDTO(false, "Erro ao autenticar", null);
        }
    }

    public CadastroUsuarioResponseDTO cadastrar(CadastroUsuarioRequestDTO cadastroUsuarioRequest) {
        try {
            if (cadastroUsuarioRequest.getNome() == null || cadastroUsuarioRequest.getNome().isBlank()) {
                return new CadastroUsuarioResponseDTO(false, "Nome não informado.");
            }

            if (cadastroUsuarioRequest.getEmail() == null || cadastroUsuarioRequest.getEmail().isBlank()) {
                return new CadastroUsuarioResponseDTO(false, "Email não informado.");
            }

            if (cadastroUsuarioRequest.getSenha() == null || cadastroUsuarioRequest.getSenha().isBlank()) {
                return new CadastroUsuarioResponseDTO(false, "Senha não informada.");
            }

            if (usuarioRepository.existsByEmail(cadastroUsuarioRequest.getEmail())) {
                throw new RuntimeException("Já existe um usuário com este email!");
            }

            try {
                Usuario novo = new Usuario();
                novo.setNome(cadastroUsuarioRequest.getNome());
                novo.setEmail(cadastroUsuarioRequest.getEmail());
                novo.setSenha(passwordEncoder.encode(cadastroUsuarioRequest.getSenha()));

                // Campo opcional
                if (cadastroUsuarioRequest.getNomeEspiritual() != null) {
                    novo.setNomeEspiritual(cadastroUsuarioRequest.getNomeEspiritual());
                }
                
                usuarioRepository.save(novo);

                return new CadastroUsuarioResponseDTO(true, "Usuário cadastrado com sucesso!");
            } catch (Exception e) {
                return new CadastroUsuarioResponseDTO(false, "Erro ao cadastrar usuário");
            }
        } catch (Exception e) {
            return new CadastroUsuarioResponseDTO(false, "Houve um erro no cadastro");
        }
    }
}
