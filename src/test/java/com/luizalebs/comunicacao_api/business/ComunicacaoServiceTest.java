package com.luizalebs.comunicacao_api.business;

import com.luizalebs.comunicacao_api.api.dto.in.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.api.dto.out.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.business.converter.Converter;
import com.luizalebs.comunicacao_api.business.service.ComunicacaoService;
import com.luizalebs.comunicacao_api.business.service.UsuarioService;
import com.luizalebs.comunicacao_api.infraestructure.client.UsuarioClient;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import com.luizalebs.comunicacao_api.infraestructure.enums.ModoEnvioEnum;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;
import com.luizalebs.comunicacao_api.infraestructure.repositories.ComunicacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComunicacaoServiceTest {

    @InjectMocks
    ComunicacaoService comunicacaoService;

    @Mock
    private ComunicacaoRepository comunicacaoRepository;

    @Mock
    private Converter converter;

    ComunicacaoInDTO comunicacaoInDTO;


    ComunicacaoOutDTO  comunicacaoOutDTO;

    ComunicacaoEntity comunicacao;

     List<ComunicacaoEntity> listaComunicacoes;
     List<ComunicacaoOutDTO> listaComunicacoesDTO;


    @BeforeEach
    public void setup () {



        comunicacaoInDTO = ComunicacaoInDTO.builder()


                .id(1L)
                .dataHoraEnvio(LocalDateTime.of(2026, 9, 21, 10, 39))
                .nomeDestinatario("Usuário")
                .emailDestinatario("usuario@gmail.com")
                .telefoneDestinatario("43991368486")
                .mensagem("Olá estou testando a mensagem")
                .modoDeEnvio(ModoEnvioEnum.WHATSAPP)
                .statusEnvio(StatusEnvioEnum.PENDENTE)
                .build();

        comunicacaoOutDTO = ComunicacaoOutDTO.builder()
                .id(1L)
                .dataHoraEnvio(LocalDateTime.of(2026, 9, 21, 10, 39))
                .nomeDestinatario("Usuário")
                .emailDestinatario("usuario@gmail.com")
                .telefoneDestinatario("43991368486")
                .mensagem("Olá estou testando a mensagem")
                .modoDeEnvio(ModoEnvioEnum.WHATSAPP)
                .statusEnvio(StatusEnvioEnum.PENDENTE)
                .build();


        comunicacao = ComunicacaoEntity.builder()
                .id(1L)
                .dataHoraEnvio(LocalDateTime.of(2026, 9, 21, 10, 39))
                .nomeDestinatario("Usuário")
                .emailDestinatario("usuario@gmail.com")
                .telefoneDestinatario("43991368486")
                .mensagem("Olá estou testando a mensagem")
                .modoDeEnvio(ModoEnvioEnum.WHATSAPP)
                .statusEnvio(StatusEnvioEnum.PENDENTE)
                .build();

        List<ComunicacaoEntity> listaComunicacao = List.of(comunicacao);
        List<ComunicacaoOutDTO> listaComunicacaoOutDTO = List.of(comunicacaoOutDTO);
    }

    @Test
    void deveAgendarComunicacao() {
        when(converter.paraEntity(comunicacaoInDTO)).thenReturn(comunicacao);
        when(comunicacaoRepository.save(any(ComunicacaoEntity.class))).thenReturn(comunicacao);
        when(converter.paraDTO(comunicacao)).thenReturn(comunicacaoOutDTO);

        ComunicacaoOutDTO resultado = comunicacaoService.agendarComunicacao(comunicacaoInDTO);

        assertEquals(comunicacaoOutDTO, resultado);
    }

    @Test
    void naoDeveAgendarComunicacao () {
       assertThrows(RuntimeException.class, () -> comunicacaoService.agendarComunicacao(null));
    }

    @Test
    void deveBuscarStatusDaComunicacaoPorEmail () {
        when(comunicacaoRepository.findByEmailDestinatario(comunicacaoInDTO.getEmailDestinatario())).thenReturn(comunicacao);
        when(converter.paraDTO(comunicacao)).thenReturn(comunicacaoOutDTO);

        ComunicacaoOutDTO resultado = comunicacaoService.buscarStatusComunicacao(comunicacaoOutDTO.getEmailDestinatario());

        assertEquals(comunicacaoOutDTO, resultado);
    }

    @Test
    void naoDeveBuscarStatusDaComunicacaoPorEmail () {
        assertThrows(RuntimeException.class, () -> comunicacaoService.buscarStatusComunicacao(null));
    }

    @Test
    void deveAlterarStatusDaComunicacaoPorEmail () {
        when(comunicacaoRepository.findByEmailDestinatario(comunicacaoInDTO.getEmailDestinatario())).thenReturn(comunicacao);
        when(comunicacaoRepository.save(comunicacao)).thenReturn(comunicacao);
        when(converter.paraDTO(comunicacao)).thenReturn(comunicacaoOutDTO);

        ComunicacaoOutDTO resultado = comunicacaoService.alterarStatusComunicacao(comunicacaoInDTO.getEmailDestinatario());

        assertEquals(comunicacaoOutDTO, resultado);
    }

    @Test
    void naoDeveAlterarStatusDaComunicacaoPorEmail () {
        assertThrows(RuntimeException.class, () -> comunicacaoService.alterarStatusComunicacao(null));
    }

    @Test
    void deveBuscarMensagensPorPeriodo () {
        when(comunicacaoRepository.findByDataHoraEnvioBetweenAndStatusEnvio(LocalDateTime.of(2026, 9, 21, 0, 0), LocalDateTime.of(2026, 9, 10, 23, 59), StatusEnvioEnum.PENDENTE)).thenReturn(listaComunicacoes);
        when(converter.paraList(listaComunicacoes)).thenReturn(listaComunicacoesDTO);
        List<ComunicacaoOutDTO> resultado = comunicacaoService.buscarMensagensPorPeriodo(LocalDateTime.of(2026, 9, 21, 0, 0), LocalDateTime.of(2026, 9, 10, 23, 59), "Bearer tokenGerado");

        assertEquals(listaComunicacoesDTO, resultado);
    }

    @Test
    void deveAlterarStatus () {
        when(comunicacaoRepository.findByEmailDestinatario(comunicacaoInDTO.getEmailDestinatario())).thenReturn(comunicacao);
        when(comunicacaoRepository.save(comunicacao)).thenReturn(comunicacao);
        when(converter.paraDTO(comunicacao)).thenReturn(comunicacaoOutDTO);

        ComunicacaoOutDTO resultado = comunicacaoService.alteraStatus(comunicacaoOutDTO.getEmailDestinatario(), comunicacaoOutDTO.getStatusEnvio());

        assertEquals(comunicacaoOutDTO, resultado);
    }

    @Test
    void naoDeveAlterarStatus () {
        assertThrows(RuntimeException.class, () -> comunicacaoService.alteraStatus(null , null));
    }






}