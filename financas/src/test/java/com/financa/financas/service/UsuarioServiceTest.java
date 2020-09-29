package com.financa.financas.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.financa.financas.exception.RegrasExpections;
import com.financa.financas.model.entity.Usuario;
import com.financa.financas.model.repository.UsuarioRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@Autowired
	UsuarioService service;
	
	@Autowired
	UsuarioRepository repository;
	
	@Test(expected = Test.None.class)
	public void validaEmailExiste() {
		
		repository.deleteAll();
		service.validarEmail("email@email.com");
	}
	
	@Test(expected = RegrasExpections.class)
	public void lancaErroQuandoExistirEmailCadastrado() {
		Usuario usuario = Usuario.builder().nome("usuario").email("email@email.com").build();
		repository.save(usuario);
		
		service.validarEmail("email@email.com");
	}

}
