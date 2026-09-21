package com.luizalebs.comunicacao_api.api.responses;

import com.luizalebs.comunicacao_api.api.dto.out.TelefoneOutDTO;

public class TelefoneOutDTOFixture {

    public static TelefoneOutDTO build(Long id,
                                       String numero,
                                       String ddd) {
        return new TelefoneOutDTO(id, numero, ddd);
    }
}
