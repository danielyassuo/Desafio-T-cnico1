package com.luizalebs.comunicacao_api.business.converter;


import com.luizalebs.comunicacao_api.api.dto.in.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.api.dto.out.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.api.dto.out.NotificacaoOutDTO;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Converter {

    ComunicacaoEntity paraEntity (ComunicacaoInDTO dto);

    ComunicacaoOutDTO paraDTO (ComunicacaoEntity entity);

    NotificacaoOutDTO paraNotificacaoDTO (ComunicacaoOutDTO dto);

    List<ComunicacaoOutDTO> paraList (List<ComunicacaoEntity> entity);

    List<NotificacaoOutDTO> paraListNotificacao (List<ComunicacaoOutDTO> dto);
}
