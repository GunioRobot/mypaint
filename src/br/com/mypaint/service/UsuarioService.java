package br.com.mypaint.service;

import java.util.List;

import br.com.mypaint.model.Usuario;


public interface UsuarioService {

	public Usuario salvar(Usuario usuario);
	
	public List<Usuario> pesquisarPorUsername(String username);

	public Usuario pesquisarPorId(Integer id);

	public Usuario atualiza(Usuario usuario);
	
}
