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
            if (loginRequest.getUsername() == null || loginRequest.getUsername().isBlank()) {
                return new LoginResponseDTO(false, "Usuário não informado", null);
            }

            if (loginRequest.getSenha() == null || loginRequest.getSenha().isBlank()) {
                return new LoginResponseDTO(false, "Senha não informada", null);
            }

            Usuario usuario = usuarioRepository.findByUsername(loginRequest.getUsername())
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

            if (cadastroUsuarioRequest.getUsername() == null || cadastroUsuarioRequest.getUsername().isBlank()) {
                return new CadastroUsuarioResponseDTO(false, "Username não informado.");
            }

            if (cadastroUsuarioRequest.getEmail() == null || cadastroUsuarioRequest.getEmail().isBlank()) {
                return new CadastroUsuarioResponseDTO(false, "Email não informado.");
            }

            if (cadastroUsuarioRequest.getSenha() == null || cadastroUsuarioRequest.getSenha().isBlank()) {
                return new CadastroUsuarioResponseDTO(false, "Senha não informada.");
            }

            if (usuarioRepository.existsByEmail(cadastroUsuarioRequest.getEmail())) {
                return new CadastroUsuarioResponseDTO(false, "Já existe um usuário com este email.");
            }

            if (usuarioRepository.existsByUsername(cadastroUsuarioRequest.getUsername())) {
                return new CadastroUsuarioResponseDTO(false, "Já existe um usuário com este username.");
            }

            if (cadastroUsuarioRequest.getCpf() != null && !cadastroUsuarioRequest.getCpf().isBlank()
                    && usuarioRepository.existsByCpf(cadastroUsuarioRequest.getCpf())) {
                return new CadastroUsuarioResponseDTO(false, "Já existe um usuário com este CPF.");
            }

            if (cadastroUsuarioRequest.getNomeEspiritual() != null && !cadastroUsuarioRequest.getNomeEspiritual().isBlank()
                    && usuarioRepository.existsByNomeEspiritual(cadastroUsuarioRequest.getNomeEspiritual())) {
                return new CadastroUsuarioResponseDTO(false, "Já existe um usuário com este nome espiritual.");
            }

            try {
                Usuario novo = new Usuario();
                novo.setNome(cadastroUsuarioRequest.getNome());
                novo.setUsername(cadastroUsuarioRequest.getUsername());
                novo.setEmail(cadastroUsuarioRequest.getEmail());
                novo.setSenha(passwordEncoder.encode(cadastroUsuarioRequest.getSenha()));

                novo.setNomeEspiritual(cadastroUsuarioRequest.getNomeEspiritual());
                novo.setCpf(cadastroUsuarioRequest.getCpf());
                novo.setRg(cadastroUsuarioRequest.getRg());
                novo.setDataNascimento(cadastroUsuarioRequest.getDataNascimento());
                novo.setTelefone(cadastroUsuarioRequest.getTelefone());
                novo.setPai(cadastroUsuarioRequest.getPai());
                novo.setMae(cadastroUsuarioRequest.getMae());
                novo.setCidade(cadastroUsuarioRequest.getCidade());
                novo.setUf(cadastroUsuarioRequest.getUf());
                novo.setComplemento(cadastroUsuarioRequest.getComplemento());
                novo.setAnoIngresso(cadastroUsuarioRequest.getAnoIngresso());
                novo.setValorMensalidade(cadastroUsuarioRequest.getValorMensalidade());

                if (cadastroUsuarioRequest.getEstadoCivil() != null) {
                    novo.setEstadoCivil(cadastroUsuarioRequest.getEstadoCivil());
                }

                if (cadastroUsuarioRequest.getMedium() != null) {
                    novo.setMedium(cadastroUsuarioRequest.getMedium());
                }

                if (cadastroUsuarioRequest.getQuadro() != null) {
                    novo.setQuadro(cadastroUsuarioRequest.getQuadro());
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
