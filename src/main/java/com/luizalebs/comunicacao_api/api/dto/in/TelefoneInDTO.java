package com.luizalebs.comunicacao_api.api.dto.in;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneInDTO {

    private String numero;
    private String ddd;
}
