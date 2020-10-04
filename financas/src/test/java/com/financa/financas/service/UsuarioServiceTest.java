package com.financa.financas.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.financa.financas.exception.AutenticacaoException;
import com.financa.financas.exception.RegrasExpections;
import com.financa.financas.model.entity.Usuario;
import com.financa.financas.model.repository.UsuarioRepository;
import com.financa.financas.service.impl.UsuarioServiceImpl;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@SpyBean
	UsuarioServiceImpl service;
	
	@MockBean
	UsuarioRepository repository;
	
	
	@Test(expected = Test.None.class)
	public void deveSalvarUsuario() {
		Mockito.doNothing().when(service).validarEmail(Mockito.anyString());
		Usuario usuario = Usuario.builder().id(1l).nome("nome").email("email@email.com").senha("senha").build();
		
		Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
		
		Usuario usuarioSalvo = service.salvarUsuario(new Usuario());
		
		Assertions.assertThat(usuarioSalvo).isNotNull();
		Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(1l);
		Assertions.assertThat(usuarioSalvo.getNome()).isEqualTo("nome");
		Assertions.assertThat(usuarioSalvo.getEmail()).isEqualTo("email@email.com");
		Assertions.assertThat(usuarioSalvo.getSenha()).isEqualTo("senha");
		
	}
	
	@Test(expected = RegrasExpections.class)
	public void naoDeveSalvarUsuarioComEmailJaCadastrado() {
		String email = "email@email.com";
		Usuario usuario = Usuario.builder().email(email).build();
		Mockito.doThrow(RegrasExpections.class).when(service).validarEmail(email);
		
		service.salvarUsuario(usuario);
		
		Mockito.verify(repository, Mockito.never()).save(usuario);
	}
	
	@Test(expected = Test.None.class)
	public void validaEmailExiste() {
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
		service.validarEmail("email@email.com");
	}
	
	@Test(expected = RegrasExpections.class)
	public void lancaErroQuandoExistirEmailCadastrado() {
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
		
		service.validarEmail("email@email.com");
	}
	
	@Test(expected = Test.None.class)
	public void autenticarUmUsuarioSucesso() {
		String email = "usuario@email.com";
		String senha = "senha";
		
		Usuario usuario = Usuario.builder().email(email).senha(senha).id(1l).build();
		Mockito.when( repository.findByEmail(email)	).thenReturn(Optional.of(usuario));
		
		Usuario result = service.autenticar(email, senha);
		
		Assertions.assertThat(result).isNotNull();
		
	}
	
	@Test
	public void lancaErroQuandoUsuarioCadastradoNaoEncontrado() {
		
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
		Throwable exception = Assertions.catchThrowable( () -> service.autenticar("email@email.com", "senha"));
		
		Assertions.assertThat(exception).isInstanceOf(AutenticacaoException.class).hasMessage("Usuário não encontrado");
	}
	
	@Test
	public void lancaErroSenhaDivergente() {
		
		Usuario usuario = Usuario.builder().email("email@email.com").senha("senha").build();
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));
		
		Throwable exception = Assertions.catchThrowable( () -> service.autenticar("email@email.com", "123abc"));
		
		Assertions.assertThat(exception).isInstanceOf(AutenticacaoException.class).hasMessage("Senha inválida");
	}
	

}
