package com.luizalebs.comunicacao_api.business.converter;

import com.luizalebs.comunicacao_api.api.dto.in.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.api.dto.out.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.api.dto.out.NotificacaoOutDTO;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-21T10:54:27-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 11.0.32 (Microsoft)"
)
@Component
public class ConverterImpl implements Converter {

    @Override
    public ComunicacaoEntity paraEntity(ComunicacaoInDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ComunicacaoEntity.ComunicacaoEntityBuilder comunicacaoEntity = ComunicacaoEntity.builder();

        comunicacaoEntity.id( dto.getId() );
        comunicacaoEntity.dataHoraEnvio( dto.getDataHoraEnvio() );
        comunicacaoEntity.nomeDestinatario( dto.getNomeDestinatario() );
        comunicacaoEntity.emailDestinatario( dto.getEmailDestinatario() );
        comunicacaoEntity.telefoneDestinatario( dto.getTelefoneDestinatario() );
        comunicacaoEntity.mensagem( dto.getMensagem() );
        comunicacaoEntity.modoDeEnvio( dto.getModoDeEnvio() );
        comunicacaoEntity.statusEnvio( dto.getStatusEnvio() );

        return comunicacaoEntity.build();
    }

    @Override
    public ComunicacaoOutDTO paraDTO(ComunicacaoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ComunicacaoOutDTO.ComunicacaoOutDTOBuilder comunicacaoOutDTO = ComunicacaoOutDTO.builder();

        comunicacaoOutDTO.id( entity.getId() );
        comunicacaoOutDTO.dataHoraEnvio( entity.getDataHoraEnvio() );
        comunicacaoOutDTO.nomeDestinatario( entity.getNomeDestinatario() );
        comunicacaoOutDTO.emailDestinatario( entity.getEmailDestinatario() );
        comunicacaoOutDTO.telefoneDestinatario( entity.getTelefoneDestinatario() );
        comunicacaoOutDTO.mensagem( entity.getMensagem() );
        comunicacaoOutDTO.modoDeEnvio( entity.getModoDeEnvio() );
        comunicacaoOutDTO.statusEnvio( entity.getStatusEnvio() );

        return comunicacaoOutDTO.build();
    }

    @Override
    public NotificacaoOutDTO paraNotificacaoDTO(ComunicacaoOutDTO dto) {
        if ( dto == null ) {
            return null;
        }

        NotificacaoOutDTO.NotificacaoOutDTOBuilder notificacaoOutDTO = NotificacaoOutDTO.builder();

        if ( dto.getId() != null ) {
            notificacaoOutDTO.id( String.valueOf( dto.getId() ) );
        }

        return notificacaoOutDTO.build();
    }

    @Override
    public List<ComunicacaoOutDTO> paraList(List<ComunicacaoEntity> entity) {
        if ( entity == null ) {
            return null;
        }

        List<ComunicacaoOutDTO> list = new ArrayList<ComunicacaoOutDTO>( entity.size() );
        for ( ComunicacaoEntity comunicacaoEntity : entity ) {
            list.add( paraDTO( comunicacaoEntity ) );
        }

        return list;
    }

    @Override
    public List<NotificacaoOutDTO> paraListNotificacao(List<ComunicacaoOutDTO> dto) {
        if ( dto == null ) {
            return null;
        }

        List<NotificacaoOutDTO> list = new ArrayList<NotificacaoOutDTO>( dto.size() );
        for ( ComunicacaoOutDTO comunicacaoOutDTO : dto ) {
            list.add( paraNotificacaoDTO( comunicacaoOutDTO ) );
        }

        return list;
    }
}
