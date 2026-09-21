package com.luizalebs.comunicacao_api.api.requests;

import com.luizalebs.comunicacao_api.api.dto.in.EnderecoInDTO;
import com.luizalebs.comunicacao_api.api.dto.in.TelefoneInDTO;
import com.luizalebs.comunicacao_api.api.dto.in.UsuarioInDTO;

import java.util.List;

public class UsuarioInDTOFixture {

    public static UsuarioInDTO build(String nome,
                                     String email,
                                     String senha,
                                     List<EnderecoInDTO> enderecos,
                                     List<TelefoneInDTO> telefones) {
        return new UsuarioInDTO(nome, email, senha, enderecos, telefones);
    }
}
