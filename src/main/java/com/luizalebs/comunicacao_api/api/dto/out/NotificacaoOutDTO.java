package com.luizalebs.comunicacao_api.api.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;
import lombok.*;

import java.time.LocalDateTime;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificacaoOutDTO {

    private String id;
    private String nomeTarefa;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
    private String emailUsuario;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAlteracao;
    private StatusEnvioEnum status;
}
