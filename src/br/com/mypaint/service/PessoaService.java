package br.com.mypaint.service;

import java.util.List;

import br.com.mypaint.model.Pessoa;


public interface PessoaService {

	public Pessoa salvar(Pessoa pessoa);
	
	public List<Pessoa> pesquisarPorNome(String nome);

	public Pessoa pesquisarPorId(Integer i);

	public Pessoa atualiza(Pessoa pessoa);
	
}
