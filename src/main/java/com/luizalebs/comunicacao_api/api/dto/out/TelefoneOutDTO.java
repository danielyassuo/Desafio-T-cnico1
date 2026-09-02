package com.luizalebs.comunicacao_api.api.dto.out;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneOutDTO {

    private Long id;
    private String numero;
    private String ddd;
}
