package br.com.mypaint.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> pesquisarPorNome(String nome) {
		Session session = getSession();
		
		Criteria criteria = session.createCriteria(Pessoa.class);
		criteria.add(Restrictions.ilike("nome", "%" + nome + "%"));
		criteria.addOrder(Order.asc("nome"));
		
		List<Pessoa> listaPessoas = criteria.list();
		
		return listaPessoas;
	}
	
	private Session getSession() {
		return ((Session) entityManager.getDelegate());
	}

	public Pessoa pesquisarPorId(Integer i) {
		return entityManager.find(Pessoa.class, i);
	}

	public Pessoa atualiza(Pessoa pessoa) {
		return null;
	}

}
