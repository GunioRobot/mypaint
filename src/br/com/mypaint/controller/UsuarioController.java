package br.com.mypaint.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.mypaint.model.Usuario;
import br.com.mypaint.service.UsuarioService;

@Resource
public class UsuarioController {

	private final Result result;

	private Validator validator;

	private UsuarioService usuarioService;

	public UsuarioController(Result result, Validator validator, UsuarioService usuarioService) {
		this.result = result;
		this.validator = validator;
		this.usuarioService = usuarioService;
	}

	@Path("/usuarios")
	public void index() {
	}

	@Path("/usuario/novo")
	public void novo() {
	}

	@Post
	@Path("/usuario/salvar")
	public void salvar(Usuario usuario) {
		try {
			if(usuario.getPessoa() == null) {
				throw new RuntimeException("Não foi possível inserir o Usuário. Verifique todos os campos.");
			}

			usuarioService.salvar(usuario);

			result.include("sucesso", "Usário criado com sucesso !");
		} catch (Exception e) {
			result.include("erro", e.getMessage());
		}

		result.redirectTo(this).index();
	}

}
