package com.luizalebs.comunicacao_api.infraestructure.client;


import com.luizalebs.comunicacao_api.api.dto.out.NotificacaoOutDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface NotificacaoClient {


    @PostMapping
    void enviarEmail(@RequestBody NotificacaoOutDTO dto);
}
