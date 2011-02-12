package br.com.mypaint.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.mypaint.model.Pessoa;
import br.com.mypaint.model.Usuario;
import br.com.mypaint.service.UsuarioService;

public class UsuarioControllerTest {

	private Result result;
	
	private Validator validator;
	
	private UsuarioController controller;
	
	@Mock
	private UsuarioService usuarioService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		result = new MockResult();
		validator = new MockValidator();
		controller = new UsuarioController(result, validator, usuarioService);
	}
	
	@Test
	public void deveriaSalvarUmUsuario() {
		Usuario pessoa = criaUsuarioComPessoa();
		
		when(usuarioService.salvar(pessoa)).thenReturn(pessoa);
		controller.salvar(pessoa);
		
		assertTrue("pessoa salva com sucesso", result.included().containsKey("sucesso"));
	}
	
	public Usuario criaUsuarioComPessoa() {
		Pessoa pessoa = new Pessoa();
		Usuario usuario = new Usuario();
		
		pessoa.setNome("Renan Montenegro");
		pessoa.setEmail("renanigt@gmail.com");
		
		usuario.setUsername("renanigt");
		usuario.setPassword("123");
		usuario.setPessoa(pessoa);
		
		return usuario;
	}
	
}
