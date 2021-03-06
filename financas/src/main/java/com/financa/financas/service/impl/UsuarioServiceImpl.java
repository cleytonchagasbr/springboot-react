package com.financa.financas.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.financa.financas.exception.AutenticacaoException;
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
		Optional<Usuario> usuario = repository.findByEmail(email);
		
		if(!usuario.isPresent()) {
			throw new AutenticacaoException("Usuário não encontrado");
		}
		
		if(!usuario.get().getSenha().equals(senha)) {
			throw new AutenticacaoException("Senha inválida");
		}
		
		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		
		boolean emailStatus = repository.existsByEmail(email);
		if(emailStatus) {
			throw new RegrasExpections("Email já cadastrado, use outro email");
		}
		
	}

	@Override
	public Optional<Usuario> obterUsuarioPorId(Long id) {
		return repository.findById(id);
	}

}
