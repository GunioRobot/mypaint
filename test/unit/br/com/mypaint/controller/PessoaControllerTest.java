package br.com.mypaint.controller;

import static org.mockito.Mockito.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.mypaint.model.Pessoa;
import br.com.mypaint.service.PessoaService;

public class PessoaControllerTest {

	private Result result;
	
	private Validator validator;
	
	private PessoaController controller;
	
	@Mock
	private PessoaService pessoaService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		result = new MockResult();
		validator = new MockValidator();
		controller = new PessoaController(result, validator, pessoaService);
	}
	
	@Test
	public void deveriaSalvarUmaPessoa() {
		Pessoa pessoa = criaPessoa();
		
		when(pessoaService.salvar(pessoa)).thenReturn(pessoa);
		controller.salvar(pessoa);
		
		assertTrue("pessoa salva com sucesso", result.included().containsKey("sucesso"));
	}
	
	public Pessoa criaPessoa() {
		Pessoa pessoa = new Pessoa();
		
		pessoa.setNome("Renan Montenegro");
		pessoa.setEmail("renanigt@gmail.com");
		
		return pessoa;
	}
	
}
