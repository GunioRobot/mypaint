package br.com.mypaint.controller;

import static org.junit.Assert.assertFalse;
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
	public void deveriaSalvarUmUsuarioComPessoa() {
		Usuario usuario = criaUsuarioComPessoa();
		
		when(usuarioService.salvar(usuario)).thenReturn(usuario);
		controller.salvar(usuario);
		
		assertTrue("usuario salva com sucesso", result.included().containsKey("sucesso"));
		assertFalse("usuario salva com sucesso", result.included().containsKey("erro"));
	}
	
	@Test
	public void naoDeveriaSalvarUmUsuarioSemPessoa() {
		Usuario usuario = criaUsuarioSemPessoa();
		
		when(usuarioService.salvar(usuario)).thenReturn(usuario);
		controller.salvar(usuario);
		
		assertTrue("usuario nao deve ser salvo com sucesso", result.included().containsKey("erro"));
		assertFalse("usuario nao deve ser salvo com sucesso", result.included().containsKey("sucesso"));
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
	
	public Usuario criaUsuarioSemPessoa() {
		Usuario usuario = new Usuario();
		
		usuario.setUsername("iagot");
		usuario.setPassword("123");
		
		return usuario;
	}
	
}
