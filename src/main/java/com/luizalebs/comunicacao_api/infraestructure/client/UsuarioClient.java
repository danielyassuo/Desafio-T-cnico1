package com.luizalebs.comunicacao_api.infraestructure.client;


import com.luizalebs.comunicacao_api.api.dto.in.EnderecoInDTO;
import com.luizalebs.comunicacao_api.api.dto.in.TelefoneInDTO;
import com.luizalebs.comunicacao_api.api.dto.in.UsuarioInDTO;
import com.luizalebs.comunicacao_api.api.dto.out.*;
import com.luizalebs.comunicacao_api.infraestructure.security.JwtUtil;
import com.luizalebs.comunicacao_api.infraestructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuarios", url = "${usuarios.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    UsuarioOutDTO buscarUsuarioPorEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioOutDTO salvaUsuario(@RequestBody UsuarioInDTO usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginOutDTO usuarioDTO);

    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,  @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioOutDTO atualizaDadosUsuario(@RequestBody UsuarioInDTO dto , @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoOutDTO atualizaEndereco(@RequestBody EnderecoInDTO dto, @RequestParam("id") Long id, @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneOutDTO atualizaTelefone(@RequestBody TelefoneInDTO dto, @RequestParam("id") Long id, @RequestHeader("Authorization") String token);


    @PostMapping("/endereco")
    EnderecoOutDTO cadastraEndereco(@RequestBody EnderecoInDTO dto, @RequestHeader("Authorization") String token);
    @PostMapping("/telefone")
    TelefoneOutDTO cadastraTelefone(@RequestBody TelefoneInDTO dto, @RequestHeader("Authorization") String token);

    @GetMapping("/endereco/{cep}")
    ViaCepOutDTO buscarDadosCep(@PathVariable("cep") String cep);

    }


