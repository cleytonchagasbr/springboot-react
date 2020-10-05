package com.financa.financas.api.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financa.financas.api.dto.UsuarioDTO;
import com.financa.financas.exception.RegrasExpections;
import com.financa.financas.model.entity.Usuario;
import com.financa.financas.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {

	private UsuarioService service;
	
	public UsuarioResource(UsuarioService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
		
		Usuario usuario = Usuario.builder().nome(dto.getNome()).email(dto.getEmail()).senha(dto.getSenha()).build();
		
		try {
			
			Usuario usuarioSalvo = service.salvarUsuario(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
			
		} catch (RegrasExpections e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
}
