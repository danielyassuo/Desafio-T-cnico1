package com.luizalebs.comunicacao_api.business.service;

import com.luizalebs.comunicacao_api.api.dto.in.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.api.dto.out.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.api.dto.out.NotificacaoOutDTO;
import com.luizalebs.comunicacao_api.business.converter.ComunicacaoConverter;
import com.luizalebs.comunicacao_api.business.converter.Converter;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;
import com.luizalebs.comunicacao_api.infraestructure.repositories.ComunicacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ComunicacaoService {

    private final ComunicacaoRepository repository;
    private final Converter converterMap;

    public ComunicacaoService(ComunicacaoRepository repository, Converter converterMap) {
        this.repository = repository;
        this.converterMap = converterMap;
    }

    public ComunicacaoOutDTO agendarComunicacao(ComunicacaoInDTO dto) {
        if (Objects.isNull(dto)) {
            throw new RuntimeException();
        }
        dto.setStatusEnvio(StatusEnvioEnum.PENDENTE);
        ComunicacaoEntity entity = converterMap.paraEntity(dto);
        repository.save(entity);
        ComunicacaoOutDTO outDTO = converterMap.paraDTO(entity);
        return outDTO;
    }

    public ComunicacaoOutDTO buscarStatusComunicacao(String emailDestinatario) {
        ComunicacaoEntity entity = repository.findByEmailDestinatario(emailDestinatario);
        if (Objects.isNull(entity)) {
            throw new RuntimeException();
        }
        return converterMap.paraDTO(entity);
    }

    public ComunicacaoOutDTO alterarStatusComunicacao(String emailDestinatario) {
        ComunicacaoEntity entity = repository.findByEmailDestinatario(emailDestinatario);
        if (Objects.isNull(entity)) {
            throw new RuntimeException();
        }
        entity.setStatusEnvio(StatusEnvioEnum.CANCELADO);
        repository.save(entity);
        return (converterMap.paraDTO(entity));
    }

    public List<ComunicacaoOutDTO> buscarMensagensPorPeriodo (LocalDateTime dataInicial, LocalDateTime dataFinal, String token){
        return converterMap.paraList(repository.findByDataHoraEnvioBetweenAndStatusEnvio(dataInicial, dataFinal, StatusEnvioEnum.PENDENTE));
    }

    public ComunicacaoOutDTO alteraStatus (String email, StatusEnvioEnum statusEnvioEnum){
        ComunicacaoEntity entity = repository.findByEmailDestinatario(email);

        if(Objects.isNull((entity))){
            throw new RuntimeException();
        }
        entity.setStatusEnvio(statusEnvioEnum);
        repository.save(entity);
        return converterMap.paraDTO(entity);
    }



}
