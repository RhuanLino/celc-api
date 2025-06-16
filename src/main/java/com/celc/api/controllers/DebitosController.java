package com.celc.api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.celc.api.dtos.Debitos.DebitosResponseDTO;
import com.celc.api.services.DebitosService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/debitos")
public class DebitosController {

    @Autowired
    private DebitosService debitosService;

    @GetMapping("/buscar")
    public ResponseEntity<DebitosResponseDTO> getDebitos(
        @RequestHeader(value = "Authorization", required = false) String bearerToken
    ) {
        try {
            if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
                return ResponseEntity.status(401)
                    .body(new DebitosResponseDTO(false, "Token JWT ausente ou inválido"));
            }

            DebitosResponseDTO response = debitosService.getDebitosTrabalhador();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                .body(new DebitosResponseDTO(false, "Erro interno ao processar requisição"));
        }
    }
}
