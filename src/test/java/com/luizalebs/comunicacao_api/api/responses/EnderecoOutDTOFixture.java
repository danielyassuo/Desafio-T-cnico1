package com.luizalebs.comunicacao_api.api.responses;

import com.luizalebs.comunicacao_api.api.dto.out.EnderecoOutDTO;

public class EnderecoOutDTOFixture {

    public static EnderecoOutDTO build(Long id,
                                       String rua,
                                       Long numero,
                                       String complemento,
                                       String cidade,
                                       String estado,
                                       String cep) {
        return new EnderecoOutDTO(id, rua, numero, complemento, cidade, estado, cep);
    }
}
