package com.luizalebs.comunicacao_api.api.dto.out;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginOutDTO {

    private String email;
    private String senha;
}
