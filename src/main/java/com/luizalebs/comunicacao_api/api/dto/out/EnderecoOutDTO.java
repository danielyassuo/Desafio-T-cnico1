package com.luizalebs.comunicacao_api.api.dto.out;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoOutDTO {

    private Long id;
    private String rua;
    private Long numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;
}
