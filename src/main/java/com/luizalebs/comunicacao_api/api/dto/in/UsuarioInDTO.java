package com.luizalebs.comunicacao_api.api.dto.in;



import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioInDTO {
    private String nome;
    private String email;
    private String senha;
    private List<EnderecoInDTO> enderecos;
    private List<TelefoneInDTO> telefones;
}
