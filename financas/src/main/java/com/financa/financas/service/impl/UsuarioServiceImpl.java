package com.financa.financas.service.impl;

import org.springframework.stereotype.Service;

import com.financa.financas.exception.RegrasExpections;
import com.financa.financas.model.entity.Usuario;
import com.financa.financas.model.repository.UsuarioRepository;
import com.financa.financas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository repository;
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		
		return null;
	}

	@Override
	public Usuario salvarUsuario(Usuario usuario) {
		
		return null;
	}

	@Override
	public void validarEmail(String email) {
		
		boolean emailStatus = repository.existsByEmail(email);
		if(emailStatus) {
			throw new RegrasExpections("Email já cadastrado, use outro email");
		}
		
	}

}