package com.luizalebs.comunicacao_api.api.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luizalebs.comunicacao_api.infraestructure.enums.ModoEnvioEnum;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;
import lombok.*;
import org.springframework.cglib.core.Local;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class    ComunicacaoInDTO implements Serializable {


    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHoraEnvio;
    private String nomeDestinatario;
    private String emailDestinatario;
    private String telefoneDestinatario;
    private String mensagem;
    private ModoEnvioEnum modoDeEnvio;
    @JsonIgnore
    private StatusEnvioEnum statusEnvio;

}
