package com.luizalebs.comunicacao_api.api.controller;


import com.luizalebs.comunicacao_api.api.dto.in.EnderecoInDTO;
import com.luizalebs.comunicacao_api.api.dto.in.TelefoneInDTO;
import com.luizalebs.comunicacao_api.api.dto.in.UsuarioInDTO;
import com.luizalebs.comunicacao_api.api.dto.out.*;
import com.luizalebs.comunicacao_api.business.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Cadastro e login de usuários")
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    @Operation(summary = "Cadastro de usuário", description = "Realiza Cadastro de nobos usuários")
    @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso")
    @ApiResponse(responseCode = "409", description = "Usuário ja cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioOutDTO> cadastroDeUsuario (@RequestBody UsuarioInDTO dto){
        return ResponseEntity.ok(service.salvaUsuario(dto));
    }

    @GetMapping
    @Operation(summary = "Buscar dados de Usuários por Email", description = "Buscar dados do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário Encontrado")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioOutDTO> buscarUsuarioPorEmail (@RequestParam("email") String email, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(service.buscarUsuarioPorEmail(email, token));
    }

    @PostMapping("/login")
    @Operation(summary = "Login Usuários", description = "Login do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public String  login(@RequestBody LoginOutDTO dto){
        return service.login(dto);
    }

    @DeleteMapping("/email")
    @Operation(summary = "Deletar Usuários por email", description = "Deleta usuário")
    @ApiResponse(responseCode = "200", description = "Usuário Deletado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail (@PathVariable String email, @RequestHeader("Authorization") String token){
        service.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }


    @PutMapping
    @Operation(summary = "Atualiza dados", description = "Atualizar dados de usuário")
    @ApiResponse(responseCode = "200", description = "Usuário Atualizado/salvo com sucesso")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    @ApiResponse(responseCode = "403", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioOutDTO> atualizaDadosUsuario (@RequestBody UsuarioInDTO dto, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(service.atualizaDadosUsuario(dto, token));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza endereço de usuário", description = "Atualiza endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<EnderecoOutDTO> atualizaEndereco(@RequestBody EnderecoInDTO dto, @RequestParam("id") Long id, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(service.atualizaEndereco(dto, id, token ));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza telefone de usuário", description = "Atualiza telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrad")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TelefoneOutDTO> atualizaTelefone (@RequestBody TelefoneInDTO dto, @RequestParam(value = "id") Long id, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(service.atualizaTelefone(dto, id, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva Endereço de usuário", description = "Salva endereço do usuário ja cadastrado")
    @ApiResponse(responseCode = "200", description = "Endereço cadastrado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<EnderecoOutDTO> cadastraEndereco(@RequestBody EnderecoInDTO dto, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(service.cadastraEndereco(dto, token));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva telefone de usuário", description = "Salva  telefone de usuário já cadastrado")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TelefoneOutDTO> cadastraTelefone (@RequestBody TelefoneInDTO dto, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(service.cadastraTelefone(dto, token));
    }

    @GetMapping("/endereco/{cep}")
    @Operation(summary = "Busca Endereço pelo CEP", description = "Faz a busca de um endereço através do CEP")
    @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso")
    @ApiResponse(responseCode = "400", description = "CEP inválido")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<ViaCepOutDTO> buscarDadosCep(@PathVariable("cep") String cep){
        return ResponseEntity.ok(service.buscarDadosCep(cep));
    }






}
