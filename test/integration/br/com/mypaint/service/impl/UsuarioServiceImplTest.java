package br.com.mypaint.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import br.com.mypaint.model.Usuario;
import br.com.mypaint.service.UsuarioService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:./WebContent/WEB-INF/config/spring/applicationContext.xml",
		"file:./WebContent/WEB-INF/config/spring/applicationContext-persistence-test.xml"
})

@Transactional
public class UsuarioServiceImplTest {

	private static final String DATASET = "./test/integration/base/dbunit/xml/UsuarioServiceImplTest.xml";

	private static final Integer ID_VALIDO = 9999;
	private static final Integer ID_INVALIDO = -1;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	DbUnitManager dbUnitManager;

	@Before
	public void setUp() {
		dbUnitManager.cleanAndInsert(DATASET);
	}

	@Test(expected = Exception.class)
	public void naoDeveriaInserirUsuarioSemPessoa() {
		Usuario usuario = criaUsuarioSemPessoa();

		usuarioService.salvar(usuario);
	}

	@Test
	public void deveriaInserirUsuarioComPessoa() {
		Usuario usuario = criaUsuarioComPessoa();

		usuarioService.salvar(usuario);
		Usuario usuarioRetorno = usuarioService.pesquisarPorId(usuario.getId());

		assertNotNull("usuario não deveria ser nulo", usuarioRetorno);
		assertNotNull("pessoa não deveria ser nula", usuarioRetorno.getPessoa());

		verificarUsuario(usuarioRetorno, "iagot", "123");
		verificarPessoa(usuarioRetorno.getPessoa(), "Iago Teixeira Araújo", "iago@gmail.com");
	}

	@Test
	public void deveriaAtualizar() {
		Usuario usuario = usuarioService.pesquisarPorId(ID_VALIDO);

		verificarUsuario(usuario, "RENANIGT", "123");

		usuario.setUsername("renan_iguatu");

		usuarioService.atualiza(usuario);

		usuario = usuarioService.pesquisarPorId(ID_VALIDO);

		verificarUsuario(usuario, "renan_iguatu", "123");
	}

	@Test
	public void deveriaCarregarPorNomeSemCaseSensitive() {
		List<Usuario> listaUsuario = usuarioService.pesquisarPorUsername("rEnAnIgT");

		assertEquals("quantidade de registros deveria ser 1", 1, listaUsuario.size());
	}

	@Test
	public void deveriaPesquisarPorId() {
		Usuario usuario = usuarioService.pesquisarPorId(ID_VALIDO);
		Usuario usuarioNulo = usuarioService.pesquisarPorId(ID_INVALIDO);

		assertNotNull("Usuario encontrado.", usuario);
		assertNull("Usuario não econtrado.", usuarioNulo);

		verificarUsuario(usuario, "RENANIGT", "123");
	}

	private Usuario criaUsuarioComPessoa() {
		Usuario usuario = new Usuario();
		Pessoa pessoa = new Pessoa();

		pessoa.setNome("Iago Teixeira Araújo");
		pessoa.setEmail("iago@gmail.com");

		usuario.setUsername("iagot");
		usuario.setPassword("123");
		usuario.setPessoa(pessoa);

		return usuario;
	}

	private Usuario criaUsuarioSemPessoa() {
		Usuario usuario = new Usuario();

		usuario.setUsername("iagot");
		usuario.setPassword("123");
		usuario.setPessoa(null);

		return usuario;
	}

	private void verificarPessoa(Pessoa pessoa, String nome, String email) {
		assertEquals("nome da pessoa", nome, pessoa.getNome());
		assertEquals("email da pessoa", email, pessoa.getEmail());
	}

	private void verificarUsuario(Usuario usuario, String username, String password) {
		assertEquals("login do usuario", username, usuario.getUsername());
		assertEquals("senha do usuario", password, usuario.getPassword());
	}

}
