package br.com.mypaint.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.mypaint.model.Pessoa;
import br.com.mypaint.service.PessoaService;

@Resource
public class PessoaController {

	private final Result result;
	
	private Validator validator;
	
	private PessoaService pessoaService;
	
	public PessoaController(Result result, Validator validator, PessoaService pessoaService) {
		this.result = result;
		this.validator = validator;
		this.pessoaService = pessoaService;
	}

	public Pessoa salvar(Pessoa pessoa) {
		try {
			pessoaService.salvar(pessoa);
			
			result.include("sucesso", "Pessoa criado com sucesso !");
		} catch (Exception e) {
			result.include("erros", e.getMessage());
		}
		
		return pessoa;
	}
	
	
	
}
