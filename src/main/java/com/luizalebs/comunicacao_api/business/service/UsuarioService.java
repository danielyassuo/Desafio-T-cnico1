package com.luizalebs.comunicacao_api.business.service;


import com.luizalebs.comunicacao_api.api.dto.in.EnderecoInDTO;
import com.luizalebs.comunicacao_api.api.dto.in.TelefoneInDTO;
import com.luizalebs.comunicacao_api.api.dto.in.UsuarioInDTO;
import com.luizalebs.comunicacao_api.api.dto.out.*;
import com.luizalebs.comunicacao_api.infraestructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioClient client;


    public UsuarioOutDTO salvaUsuario(UsuarioInDTO dto){
        return client.salvaUsuario(dto);
    }

    public String login(LoginOutDTO dto){
        return client.login(dto);
    }

    public UsuarioOutDTO buscarUsuarioPorEmail (String email, String token){
        return client.buscarUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token){
        client.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioOutDTO atualizaDadosUsuario (UsuarioInDTO dto, String token){
        return client.atualizaDadosUsuario(dto, token);
    }

    public EnderecoOutDTO atualizaEndereco(EnderecoInDTO dto, Long id, String token){
        return client.atualizaEndereco(dto, id, token);
    }

    public TelefoneOutDTO atualizaTelefone (TelefoneInDTO dto, Long id, String token){
        return client.atualizaTelefone(dto, id, token);
    }

    public EnderecoOutDTO cadastraEndereco(EnderecoInDTO dto, String token){
        return client.cadastraEndereco(dto, token);
    }

    public TelefoneOutDTO cadastraTelefone (TelefoneInDTO dto, String token){
        return client.cadastraTelefone(dto, token);
    }

    public ViaCepOutDTO buscarDadosCep(String cep){
        return client.buscarDadosCep(cep);
    }


}
