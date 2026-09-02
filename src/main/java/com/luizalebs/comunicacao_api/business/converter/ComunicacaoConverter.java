package com.luizalebs.comunicacao_api.business.converter;

import com.luizalebs.comunicacao_api.api.dto.in.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.api.dto.out.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.api.dto.out.NotificacaoOutDTO;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class ComunicacaoConverter {

    public ComunicacaoEntity paraEntity(ComunicacaoInDTO dto) {
        return ComunicacaoEntity.builder()
                .dataHoraEnvio(dto.getDataHoraEnvio())
                .emailDestinatario(dto.getEmailDestinatario())
                .nomeDestinatario(dto.getNomeDestinatario())
                .mensagem(dto.getMensagem())
                .modoDeEnvio(dto.getModoDeEnvio())
                .statusEnvio(dto.getStatusEnvio())
                .telefoneDestinatario(dto.getTelefoneDestinatario())
                .build();
    }

    public ComunicacaoOutDTO paraDTO(ComunicacaoEntity entity) {
        return ComunicacaoOutDTO.builder()
                .id(entity.getId())
                .dataHoraEnvio(entity.getDataHoraEnvio())
                .emailDestinatario(entity.getEmailDestinatario())
                .nomeDestinatario(entity.getNomeDestinatario())
                .mensagem(entity.getMensagem())
                .modoDeEnvio(entity.getModoDeEnvio())
                .telefoneDestinatario(entity.getTelefoneDestinatario())
                .statusEnvio(entity.getStatusEnvio())
                .build();
    }

    public NotificacaoOutDTO paraNotificacaoDTO(ComunicacaoOutDTO dto){
        return NotificacaoOutDTO.builder()
                .id(dto.getId().toString())
                .nomeTarefa(dto.getMensagem())
                .descricao(dto.getMensagem())
                .dataCriacao(dto.getDataHoraEnvio())
                .emailUsuario(dto.getEmailDestinatario())
                .status(dto.getStatusEnvio())
                .build();
    }

    public List<ComunicacaoOutDTO> paraList (List<ComunicacaoEntity> entity){
        List<ComunicacaoOutDTO> lista = new ArrayList<>();

        for(ComunicacaoEntity entitys : entity){
            lista.add(paraDTO(entitys));
        }
        return lista;
    }

    public List<NotificacaoOutDTO> paraListNotif (List<ComunicacaoOutDTO> dto){
        List<NotificacaoOutDTO> listaa = new ArrayList<>();

        for(ComunicacaoOutDTO dtos : dto){
            listaa.add(paraNotificacaoDTO(dtos));
        }
        return listaa;
    }






}
