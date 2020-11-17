package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class FabricanteDAO implements Serializable{
	
	@Inject
	private EntityManager em;///injeta o EM e depois fecha ele
	
	public void salvar(Fabricante fabricante) {
		em.persist(fabricante);
	}

	@SuppressWarnings("unchecked")
	public List<Fabricante> buscarTodos() {
	
		return em.createQuery("from Fabricante").getResultList();
	}

	@Transactional
	public void excluir(Fabricante fabricante) throws NegocioException {
		//crio um fabricante temporario
		//vou no entity Manager e busco a classe fabricante e pego o codigo desse mesmo fabricante.
		Fabricante fabricanteTemp =em.find(Fabricante.class, fabricante.getCodigo());
		em.remove(fabricanteTemp);
		em.flush();
	}

}
