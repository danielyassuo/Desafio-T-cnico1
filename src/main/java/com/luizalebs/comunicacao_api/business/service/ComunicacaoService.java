package com.luizalebs.comunicacao_api.business.service;

import com.luizalebs.comunicacao_api.api.dto.in.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.api.dto.out.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.api.dto.out.NotificacaoOutDTO;
import com.luizalebs.comunicacao_api.business.converter.ComunicacaoConverter;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;
import com.luizalebs.comunicacao_api.infraestructure.repositories.ComunicacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ComunicacaoService {

    private final ComunicacaoRepository repository;
    private final ComunicacaoConverter converter;

    public ComunicacaoService(ComunicacaoRepository repository, ComunicacaoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public ComunicacaoOutDTO agendarComunicacao(ComunicacaoInDTO dto) {
        if (Objects.isNull(dto)) {
            throw new RuntimeException();
        }
        dto.setStatusEnvio(StatusEnvioEnum.PENDENTE);
        ComunicacaoEntity entity = converter.paraEntity(dto);
        repository.save(entity);
        ComunicacaoOutDTO outDTO = converter.paraDTO(entity);
        return outDTO;
    }

    public ComunicacaoOutDTO buscarStatusComunicacao(String emailDestinatario) {
        ComunicacaoEntity entity = repository.findByEmailDestinatario(emailDestinatario);
        if (Objects.isNull(entity)) {
            throw new RuntimeException();
        }
        return converter.paraDTO(entity);
    }

    public ComunicacaoOutDTO alterarStatusComunicacao(String emailDestinatario) {
        ComunicacaoEntity entity = repository.findByEmailDestinatario(emailDestinatario);
        if (Objects.isNull(entity)) {
            throw new RuntimeException();
        }
        entity.setStatusEnvio(StatusEnvioEnum.CANCELADO);
        repository.save(entity);
        return (converter.paraDTO(entity));
    }

    public List<ComunicacaoOutDTO> buscarMensagensPorPeriodo (LocalDateTime dataInicial, LocalDateTime dataFinal, String token){
        return converter.paraList(repository.findByDataHoraEnvioBetweenAndStatusEnvio(dataInicial, dataFinal, StatusEnvioEnum.PENDENTE));
    }

    public ComunicacaoOutDTO alteraStatus (String email, StatusEnvioEnum statusEnvioEnum){
        ComunicacaoEntity entity = repository.findByEmailDestinatario(email);

        if(Objects.isNull((entity))){
            throw new RuntimeException();
        }
        entity.setStatusEnvio(statusEnvioEnum);
        repository.save(entity);
        return converter.paraDTO(entity);
    }



}
