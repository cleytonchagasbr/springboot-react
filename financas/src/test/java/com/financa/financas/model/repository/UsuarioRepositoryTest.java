package com.financa.financas.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.financa.financas.model.entity.Usuario;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository repository;
	
	@Test
	public void verificaUmEmail() {
		
		Usuario usuario = Usuario.builder().nome("usuario").email("usuario@email.com").build();
		repository.save(usuario);
		
		boolean response = repository.existsByEmail("usuario@email.com");
		
		Assertions.assertThat(response).isTrue();
	}
	
	@Test
	public void retornaFalseQuandoUsuarioNaoExist() {
		
		repository.deleteAll();
		
		boolean result = repository.existsByEmail("usuario@email.com");
		
		Assertions.assertThat(result).isFalse();
	}

}
