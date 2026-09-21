package com.luizalebs.comunicacao_api.api.responses;

import com.luizalebs.comunicacao_api.api.dto.out.ErrorResponseDTO;

import java.time.LocalDateTime;

public class ErrorResponseDTOFixture {

    public static ErrorResponseDTO build(LocalDateTime timeStamp,
                                         int status,
                                         String erro,
                                         String message,
                                         String path) {
        return new ErrorResponseDTO(timeStamp, status, erro, message, path);
    }
}
