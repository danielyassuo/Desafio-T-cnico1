package com.luizalebs.comunicacao_api.api.controller;

import com.luizalebs.comunicacao_api.api.dto.in.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.api.dto.out.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.api.dto.out.NotificacaoOutDTO;
import com.luizalebs.comunicacao_api.business.service.ComunicacaoService;


import com.luizalebs.comunicacao_api.business.service.NotificacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/comunicacao")
@Tag(name = "Comunicacao", description = "Agendamento de mensagens")
public class ComunicacaoController {

    private final ComunicacaoService service;

    public ComunicacaoController(ComunicacaoService service) {
        this.service = service;
    }

    @PostMapping("/agendar")
    @Operation(summary = "Agendar" , description = "Agenda uma mensagem")
    @ApiResponse(responseCode = "200", description = "Agendado com sucesso")
    @ApiResponse(responseCode = "409", description = "Conflito, mensagem agendada anteriormente")
    @ApiResponse(responseCode = "500", description = "Erro no Servidor")
    public ResponseEntity<ComunicacaoOutDTO> agendar(@RequestBody ComunicacaoInDTO dto)  {
        return ResponseEntity.ok(service.agendarComunicacao(dto));
    }

    @GetMapping()
    @Operation(summary = "Busca do Status", description = "Faz a busca do status da mensagem através do email do destinatário")
    @ApiResponse(responseCode = "200", description = "Email encontrado com sucesso, e status retornado")
    @ApiResponse(responseCode = "401", description = "Credenciais Inválidas")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<ComunicacaoOutDTO> buscarStatus(@RequestParam String emailDestinatario) {
        return ResponseEntity.ok(service.buscarStatusComunicacao(emailDestinatario));
    }

    @PatchMapping("/cancelar")
    @Operation(summary = "Cancela a Mensagem", description = "Realiza o cancelamento da mensagem e muda o status para cancelado" +
            " através do email do destinatário")
    @ApiResponse(responseCode = "200", description = "Mensagem cancelada com sucesso")
    @ApiResponse(responseCode = "401", description = "Crendeciais Inválidas")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<ComunicacaoOutDTO> cancelarStatus(@RequestParam String emailDestinatario) {
        return ResponseEntity.ok(service.alterarStatusComunicacao(emailDestinatario));
    }

    @GetMapping("/mensagem")
    @Operation(summary = "Faz a busca de mensagens por periodo", description = "Busca Mensagens enviadas")
    @ApiResponse(responseCode = "200", description = "mensagem encontrada com sucesso")
    @ApiResponse(responseCode = "401", description = "mensagem nã oencontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<ComunicacaoOutDTO>> buscarMensagensPorPeriodo (@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
                                                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal,
                                                                              @RequestHeader(name = "Authorization") String token){
        return ResponseEntity.ok(service.buscarMensagensPorPeriodo(dataInicial, dataFinal, token));
    }


}
