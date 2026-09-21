package com.luizalebs.comunicacao_api.api.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luizalebs.comunicacao_api.api.dto.in.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.infraestructure.enums.ModoEnvioEnum;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;

import java.time.LocalDateTime;

public class ComunicacaoInDTOFixture {

    public static ComunicacaoInDTO build (Long id,
                                          LocalDateTime dataHoraEnvio,
                                          String nomeDestinatario,
                                          String emailDestinatario,
                                          String telefoneDestinatario,
                                          String mensagem,
                                          ModoEnvioEnum modoDeEnvio,
                                          StatusEnvioEnum statusEnvio){
        return new ComunicacaoInDTO(id, dataHoraEnvio, nomeDestinatario, emailDestinatario, telefoneDestinatario, mensagem, modoDeEnvio, statusEnvio);

    }
}
