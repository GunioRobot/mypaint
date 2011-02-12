package br.com.mypaint.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.com.mypaint.model.Usuario;
import br.com.mypaint.service.UsuarioService;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Usuario salvar(Usuario usuario) {
		entityManager.persist(usuario);
		
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> pesquisarPorUsername(String nome) {
		Session session = getSession();
		
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.ilike("username", "%" + nome + "%"));
		criteria.addOrder(Order.asc("username"));
		
		List<Usuario> listaUsuarios = criteria.list();
		
		return listaUsuarios;
	}
	
	private Session getSession() {
		return ((Session) entityManager.getDelegate());
	}

	public Usuario pesquisarPorId(Integer id) {
		return entityManager.find(Usuario.class, id);
	}

	public Usuario atualiza(Usuario usuario) {
		return null;
	}

}
