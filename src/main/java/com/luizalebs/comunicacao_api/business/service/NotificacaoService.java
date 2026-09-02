package com.luizalebs.comunicacao_api.business.service;


import com.luizalebs.comunicacao_api.api.dto.out.NotificacaoOutDTO;
import com.luizalebs.comunicacao_api.infraestructure.client.NotificacaoClient;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class NotificacaoService {

    private final NotificacaoClient client;


    public void enviaEmail(NotificacaoOutDTO dto){
        client.enviarEmail(dto);
    }
}
