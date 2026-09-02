package com.luizalebs.comunicacao_api.api.dto.out;

import lombok.*;

import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioOutDTO {
    private String nome;
    private String email;
    private String senha;
    private List<EnderecoOutDTO> enderecos;
    private List<TelefoneOutDTO> telefones;
}
