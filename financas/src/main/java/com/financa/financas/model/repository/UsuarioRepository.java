package com.financa.financas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financa.financas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	boolean existsByEmail(String email);
	
}
