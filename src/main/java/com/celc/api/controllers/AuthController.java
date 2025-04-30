package com.celc.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.celc.api.dtos.Cadastro.CadastroTrabalhadorRequestDTO;
import com.celc.api.dtos.Cadastro.CadastroTrabalhadorResponseDTO;
import com.celc.api.dtos.Login.LoginRequestDTO;
import com.celc.api.dtos.Login.LoginResponseDTO;
import com.celc.api.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            LoginResponseDTO response = authService.autenticar(loginRequest);

            if (!response.isSucesso()) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(response);
            }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LoginResponseDTO(false, "Erro ao fazer login",  null));
        }
    }
    
    @PostMapping("/cadastro")
    public ResponseEntity<CadastroTrabalhadorResponseDTO> cadastrar(@RequestBody CadastroTrabalhadorRequestDTO cadastroTrabalhadorRequestDTO) {
        try {
            CadastroTrabalhadorResponseDTO response = authService.cadastrar(cadastroTrabalhadorRequestDTO);

            if (!response.isSucesso()) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(response);
            }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CadastroTrabalhadorResponseDTO(false, "Erro ao fazer cadastro c"));
        }
    }
    

}
