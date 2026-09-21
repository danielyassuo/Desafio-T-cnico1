package com.luizalebs.comunicacao_api.api.requests;

import com.luizalebs.comunicacao_api.api.dto.in.EnderecoInDTO;

public class EnderecoInDTOFixture {

    public static EnderecoInDTO build(String rua,
                                    Long numero,
                                    String complemento,
                                    String cidade,
                                    String estado,
                                    String cep) {
        return new EnderecoInDTO(rua, numero, complemento, cidade, estado, cep);
    }
}
