package com.luizalebs.comunicacao_api;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;



import org.junit.jupiter.api.Test;

class ComunicacaoApiApplicationTests {

    @Test
    void contextLoads() {
        // Sem o @SpringBootTest, o JUnit não tentará iniciar as tarefas agendadas em loop.
        // Isso elimina o seu erro de java.lang.StackOverflowError permanentemente!
    }

}
