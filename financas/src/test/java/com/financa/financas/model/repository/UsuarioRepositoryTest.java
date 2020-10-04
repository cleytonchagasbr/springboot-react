package com.financa.financas.model.repository;

import java.util.Optional;


import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.financa.financas.model.entity.Usuario;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void verificaUmEmail() {
		Usuario usuario = criarUsuarioBuilder();
		entityManager.persist(usuario);
		
		boolean response = repository.existsByEmail("usuario@email.com");
		Assertions.assertThat(response).isTrue();
		

	}
	
	@Test
	public void retornaFalseQuandoUsuarioNaoExist() {
		boolean result = repository.existsByEmail("usuario@email.com");
		Assertions.assertThat(result).isFalse();
	}
	
	@Test
	public void persistirUsuarioDataBase() {
		
		Usuario usuario = criarUsuarioBuilder();
		Usuario usuarioSalvo = repository.save(usuario);
		
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
		
	}
	
	@Test
	public void buscaUmUsuarioPorEmail( ) {
		Usuario usuario = criarUsuarioBuilder();
		entityManager.persist(usuario);
		
		Optional<Usuario> result = repository.findByEmail("usuario@email.com");
		
		Assertions.assertThat(result.isPresent()).isTrue();
	}
	
	@Test
	public void retornaVazioQuandoNaoEncontraUsuario( ) {
		
		Optional<Usuario> result = repository.findByEmail("usuario@email.com");
		
		Assertions.assertThat(result.isPresent()).isFalse();
	}
	
	public static Usuario criarUsuarioBuilder() {
		return Usuario.builder().nome("usuario")
				.email("usuario@email.com")
				.senha("senha").build();
	}
	

}
