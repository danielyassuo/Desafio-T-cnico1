package com.luizalebs.comunicacao_api.api.responses;

import com.luizalebs.comunicacao_api.api.dto.out.LoginOutDTO;

public class LoginOutDTOFixture {

    public static LoginOutDTO build(String email,
                                    String senha) {
        return new LoginOutDTO(email, senha);
    }
}
