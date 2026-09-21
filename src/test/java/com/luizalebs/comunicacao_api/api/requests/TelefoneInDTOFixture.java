package com.luizalebs.comunicacao_api.api.requests;

import com.luizalebs.comunicacao_api.api.dto.in.TelefoneInDTO;

public class TelefoneInDTOFixture {

    public static TelefoneInDTO build(String numero,
                                      String ddd) {
        return new TelefoneInDTO(numero, ddd);
    }
}
