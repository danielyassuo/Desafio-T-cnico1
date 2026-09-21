package com.luizalebs.comunicacao_api;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class TesteContexto {

    @Test
    void testaContexto() {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext()) {

            context.refresh();

            System.out.println(">>> CONTEXTO SPRING FUNCIONOU <<<");
        }
    }
}
