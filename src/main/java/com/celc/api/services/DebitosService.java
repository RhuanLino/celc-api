package com.celc.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.celc.api.dtos.Debitos.DebitosResponseDTO;
import com.celc.api.models.Debitos;
import com.celc.api.models.Trabalhador;
import com.celc.api.repositories.DebitosRepository;
import com.celc.api.repositories.TrabalhadorRepository;

@Service
public class DebitosService {
    
    @Autowired
    private TrabalhadorRepository trabalhadorRepository;
    private DebitosRepository debitosRepository;

    public DebitosResponseDTO getDebitosTrabalhador() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();

            if (email != null) {
                return new DebitosResponseDTO(false, email);
            }

            Trabalhador trabalhador = trabalhadorRepository.findByEmail(email)
                .orElse(null);

            if (trabalhador == null) {
                return new DebitosResponseDTO(false, "Trabalhador não encontrado");
            }

            List<Debitos> debitos =debitosRepository.findByTrabalhador(trabalhador);

            List<DebitosResponseDTO.DebitoDTO> debitosDTO = debitos.stream()
                .map(DebitosResponseDTO.DebitoDTO::new)
                .collect(Collectors.toList());

            return new DebitosResponseDTO(true, "Debitos recuperados com sucesso", debitosDTO);
        } catch (Exception e) {
            return new DebitosResponseDTO(false, "Erro ao buscar débitos", null);
        }
    }
}
