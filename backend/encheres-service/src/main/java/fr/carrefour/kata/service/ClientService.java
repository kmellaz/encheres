package fr.carrefour.kata.service;

import fr.carrefour.kata.dto.ClientDto;
import fr.carrefour.kata.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;

    /**
     * renvoie la liste des client du système
     * @return
     */
    public List<ClientDto> trouverClients() {
        return this.clientRepository.findAll().stream()
                .map(client -> ClientDto.builder()
                        .nom(client.getNom())
                        .prenom(client.getPrenom())
                        .email(client.getEmail())
                        .build())
                .toList();
    }
}
