package com.luizalebs.comunicacao_api.business.service;


import com.luizalebs.comunicacao_api.api.dto.out.LoginOutDTO;
import com.luizalebs.comunicacao_api.api.dto.out.NotificacaoOutDTO;
import com.luizalebs.comunicacao_api.business.converter.ComunicacaoConverter;
import com.luizalebs.comunicacao_api.business.converter.Converter;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final NotificacaoService notificacaoService;
    private final ComunicacaoService comunicacaoService;
    private final UsuarioService usuarioService;
    private final Converter converterMap;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscarMensagensProximaHora(){
        log.info("Iniciada a busca de mensagens");
        String token = login(converterParaLogin());
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaMaisCinco = LocalDateTime.now().plusHours(5);
        List<NotificacaoOutDTO> listaMensagens =  converterMap.paraListNotificacao(comunicacaoService.buscarMensagensPorPeriodo(horaFutura, horaMaisCinco, token));
        log.info("Mensagens Encontradas  : " + listaMensagens);

        for(NotificacaoOutDTO listaMensagem : listaMensagens) {
            notificacaoService.enviaEmail(listaMensagem);
            log.info("Email enviado : " + listaMensagem.getEmailUsuario());
            comunicacaoService.alteraStatus(listaMensagem.getEmailUsuario(), StatusEnvioEnum.ENVIADO);
        }

        log.info("Finalizando a busca! ");

    }


    public String login(LoginOutDTO login){
        return usuarioService.login(login);
    }

    public LoginOutDTO converterParaLogin (){
        return LoginOutDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }

}
