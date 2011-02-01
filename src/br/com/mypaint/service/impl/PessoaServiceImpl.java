package br.com.mypaint.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import br.com.mypaint.model.Pessoa;
import br.com.mypaint.service.PessoaService;

@Service("pessoaService")
public class PessoaServiceImpl implements PessoaService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Pessoa salvar(Pessoa pessoa) {
		entityManager.persist(pessoa);
		
		return pessoa;
	}

}
