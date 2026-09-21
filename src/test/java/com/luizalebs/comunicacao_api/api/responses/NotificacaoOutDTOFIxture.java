package com.luizalebs.comunicacao_api.api.responses;

import com.luizalebs.comunicacao_api.api.dto.out.NotificacaoOutDTO;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;

import java.time.LocalDateTime;

public class NotificacaoOutDTOFIxture {

    public static NotificacaoOutDTO build(String id,
                                          String nomeTarefa,
                                          String descricao,
                                          LocalDateTime dataCriacao,
                                          LocalDateTime dataEvento,
                                          String emailUsuario,
                                          LocalDateTime dataAlteracao,
                                          StatusEnvioEnum status) {
        return new NotificacaoOutDTO(id, nomeTarefa, descricao, dataCriacao, dataEvento, emailUsuario, dataAlteracao, status);
    }
}
