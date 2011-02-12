package br.com.mypaint.controller;

import br.com.caelum.vraptor.Path;
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

	@Path("/pessoa/salvar")
	public Usuario salvar(Usuario usuario) {
		try {
			usuarioService.salvar(usuario);
			
			result.include("sucesso", "Pessoa criada com sucesso !");
		} catch (Exception e) {
			result.include("erro", e.getMessage());
		}
		
		return usuario;
	}
	
}