package com.luizalebs.comunicacao_api.api.responses;

import com.luizalebs.comunicacao_api.api.dto.out.EnderecoOutDTO;
import com.luizalebs.comunicacao_api.api.dto.out.TelefoneOutDTO;
import com.luizalebs.comunicacao_api.api.dto.out.UsuarioOutDTO;

import java.util.List;

public class UsuarioOutDTOFixture {

    public static UsuarioOutDTO build(String nome,
                                      String email,
                                      String senha,
                                      List<EnderecoOutDTO> enderecos,
                                      List<TelefoneOutDTO> telefones) {
        return new UsuarioOutDTO(nome, email, senha, enderecos, telefones);
    }
}
