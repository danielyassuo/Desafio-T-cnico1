package com.luizalebs.comunicacao_api.api.dto.out;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViaCepOutDTO {

    public String cep;
    public String logradouro;
    public String complemento;
    public String unidade;
    public String bairro;
    public String localidade;
    public String uf;
    public String estado;
    public String regiao;
    public String ibge;
    public String gia;
    public String ddd;
    public String siafi;
}
