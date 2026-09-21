package com.luizalebs.comunicacao_api.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.luizalebs.comunicacao_api.api.controller.ComunicacaoController;
import com.luizalebs.comunicacao_api.api.controller.GlobalExceptionHandler;
import com.luizalebs.comunicacao_api.api.dto.in.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.api.dto.out.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.business.service.ComunicacaoService;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import com.luizalebs.comunicacao_api.infraestructure.enums.ModoEnvioEnum;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ComunicacaoControllerTest {

    @InjectMocks
    ComunicacaoController comunicacaoController;

    @Mock
    ComunicacaoService comunicacaoService;

    ComunicacaoInDTO comunicacaoInDTO;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());


    ComunicacaoOutDTO comunicacaoOutDTO;

    ComunicacaoEntity comunicacao;

    List<ComunicacaoEntity> listaComunicacoes;
    List<ComunicacaoOutDTO> listaComunicacoesDTO;

    private MockMvc mockMvc;

    private String url;

    private String json;




    @BeforeEach
    public void setup () throws JsonProcessingException {

        mockMvc = MockMvcBuilders.standaloneSetup(comunicacaoController).setControllerAdvice(new GlobalExceptionHandler()).alwaysDo(print()).build();
        url = "/comunicacao";


        comunicacaoInDTO = ComunicacaoInDTO.builder()


                .id(1L)
                .dataHoraEnvio(LocalDateTime.of(2026, 9, 21, 10, 39))
                .nomeDestinatario("Usuário")
                .emailDestinatario("usuario@gmail.com")
                .telefoneDestinatario("43991368486")
                .mensagem("Olá estou testando a mensagem")
                .modoDeEnvio(ModoEnvioEnum.WHATSAPP)
                .build();

        comunicacaoOutDTO = ComunicacaoOutDTO.builder()
                .id(1L)
                .dataHoraEnvio(LocalDateTime.of(2026, 9, 21, 10, 39))
                .nomeDestinatario("Usuário")
                .emailDestinatario("usuario@gmail.com")
                .telefoneDestinatario("43991368486")
                .mensagem("Olá estou testando a mensagem")
                .modoDeEnvio(ModoEnvioEnum.WHATSAPP)
                .build();


        comunicacao = ComunicacaoEntity.builder()
                .id(1L)
                .dataHoraEnvio(LocalDateTime.of(2026, 9, 21, 10, 39))
                .nomeDestinatario("Usuário")
                .emailDestinatario("usuario@gmail.com")
                .telefoneDestinatario("43991368486")
                .mensagem("Olá estou testando a mensagem")
                .modoDeEnvio(ModoEnvioEnum.WHATSAPP)
                .build();

        List<ComunicacaoEntity> listaComunicacao = List.of(comunicacao);
        List<ComunicacaoOutDTO> listaComunicacaoOutDTO = List.of(comunicacaoOutDTO);

        json = objectMapper.writeValueAsString(comunicacaoInDTO);
    }

    @Test
    void deveAgendarComunicacao() throws Exception {
        when(comunicacaoService.agendarComunicacao(comunicacaoInDTO)).thenReturn(comunicacaoOutDTO);

        mockMvc.perform(post(url+"/agendar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isOk());

        verify(comunicacaoService).agendarComunicacao(comunicacaoInDTO);
        verifyNoMoreInteractions(comunicacaoService);
    }

    @Test
    void naoDeveAgendarComunicacao() throws Exception {
        mockMvc.perform(post(url+"/agendar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

        verifyNoInteractions(comunicacaoService);
    }

    @Test
    void deveBuscarStatus () throws Exception {
        when(comunicacaoService.buscarStatusComunicacao(comunicacaoInDTO.getEmailDestinatario())).thenReturn(comunicacaoOutDTO);

        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("emailDestinatario", comunicacaoInDTO.getEmailDestinatario())
        ).andExpect(status().isOk());

        verify(comunicacaoService).buscarStatusComunicacao(comunicacaoInDTO.getEmailDestinatario());
        verifyNoMoreInteractions(comunicacaoService);
    }

    @Test
    void naoDeveBuscarStatus () throws Exception {

        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

        verifyNoInteractions(comunicacaoService);
    }

    @Test
    void deveAlterarStatusComunicacao () throws Exception {
        when(comunicacaoService.alterarStatusComunicacao(comunicacaoInDTO.getEmailDestinatario())).thenReturn(comunicacaoOutDTO);

        mockMvc.perform(patch(url+"/cancelar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("emailDestinatario", comunicacaoInDTO.getEmailDestinatario())
        ).andExpect(status().isOk());

        verify(comunicacaoService).alterarStatusComunicacao(comunicacaoInDTO.getEmailDestinatario());
        verifyNoMoreInteractions(comunicacaoService);
    }

    @Test
    void naoDeveAlterarStatusComunicacao () throws Exception {
        mockMvc.perform(patch(url+"/cancelar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

        verifyNoInteractions(comunicacaoService);
    }

    @Test
    void deveBuscarMensagensPorPeriodo () throws Exception {
        when(comunicacaoService.buscarMensagensPorPeriodo(LocalDateTime.of(2026, 9, 21, 0, 0), LocalDateTime.of(2026, 9, 10, 23, 59), "Bearer tokenGerado")).thenReturn(listaComunicacoesDTO);

        mockMvc.perform(get(url+"/mensagem")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("dataInicial",  "2026-09-21T00:00:00")
                .param("dataFinal", "2026-09-10T23:59:00")
                .header("Authorization", "Bearer tokenGerado")
        ).andExpect(status().isOk());

        verify(comunicacaoService).buscarMensagensPorPeriodo(LocalDateTime.of(2026, 9, 21, 0, 0), LocalDateTime.of(2026, 9, 10, 23, 59), "Bearer tokenGerado");
        verifyNoMoreInteractions(comunicacaoService);
    }




}
