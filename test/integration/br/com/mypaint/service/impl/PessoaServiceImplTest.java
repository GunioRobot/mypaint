package br.com.mypaint.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import base.dbunit.DbUnitManager;
import br.com.mypaint.model.Pessoa;
import br.com.mypaint.service.PessoaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./WebContent/WEB-INF/config/spring/applicationContext.xml",
		"file:./WebContent/WEB-INF/config/spring/applicationContext-persistence-test.xml"
})

@Transactional
public class PessoaServiceImplTest {

	private static final String DATASET = "./test/integration/base/dbunit/xml/PessoaServiceImplTest.xml";
	
	private static final Integer ID_VALIDO = 9999;
	private static final Integer ID_INVALIDO = -1;
	
	@Autowired
	PessoaService pessoaService;
	
	@Autowired
	DbUnitManager dbUnitManager;
	
	@Before
	public void setUp() {
		dbUnitManager.cleanAndInsert(DATASET);
	}
	
	@Test
	public void deveriaInserir() {
		Pessoa pessoa = criaPessoa();
		
		pessoaService.salvar(pessoa);
		Pessoa pessoaRetorno = pessoaService.pesquisarPorId(pessoa.getId());
		
		assertNotNull("pessoa deveria ser diferente de nulo", pessoaRetorno);
	}
	
	@Test
	public void deveriaAtualizar() {
		Pessoa pessoa = pessoaService.pesquisarPorId(ID_VALIDO);
		
		verificarPessoa(pessoa, "Renan Teixeira Lima Verde Montenegro", "fulano@gmail.com");
		
		pessoa.setNome("Elson");
		pessoa.setEmail("elson@gmail.com");
		
		pessoaService.atualiza(pessoa);
		
		pessoa = pessoaService.pesquisarPorId(ID_VALIDO);
		
		verificarPessoa(pessoa, "Elson", "elson@gmail.com");
	}
	
	@Test
	public void deveriaCarregarPorNomeSemCaseSensitive() {
		List<Pessoa> listaPessoa = pessoaService.pesquisarPorNome("renan TeiXeira");
		
		assertEquals("quantidade de registros", 1, listaPessoa.size());
	}
	
	@Test
	public void deveriaPesquisarPorId() {
		Pessoa pessoa = pessoaService.pesquisarPorId(ID_VALIDO);
		Pessoa pessoaNula = pessoaService.pesquisarPorId(ID_INVALIDO);
		
		assertNotNull("Pessoa encontrada.", pessoa);
		assertNull("Pessoa não econtrada.", pessoaNula);
		
		verificarPessoa(pessoa, "Renan Teixeira Lima Verde Montenegro", "fulano@gmail.com");
	}
	
	private Pessoa criaPessoa() {
		Pessoa pessoa = new Pessoa();
		
		pessoa.setNome("Iago Teixeira Araújo");
		pessoa.setEmail("iago@gmail.com");
		
		return pessoa;
	}
	
	private void verificarPessoa(Pessoa pessoa, String nome, String email) {
		assertEquals("nome da pessoa", nome, pessoa.getNome());
		assertEquals("email da pessoa", email, pessoa.getEmail());
	}
	
}
