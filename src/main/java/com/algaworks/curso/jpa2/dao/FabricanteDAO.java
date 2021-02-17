package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class FabricanteDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;
	
	//@Transactional
	public void salvar(Fabricante fabricante) {
		em.merge(fabricante);
	}

	@SuppressWarnings("unchecked")
	public List<Fabricante> buscarTodos() {
		return em.createQuery("from Fabricante").getResultList();
	}

	@Transactional
	public void excluir(Fabricante fabricante) throws NegocioException {
		Fabricante fabricanteTemp = em.find(Fabricante.class, fabricante.getCodigo());
		
		em.remove(fabricanteTemp);
		em.flush();
	}

	public Fabricante buscarPeloCodigo(Long codigo) {
		return em.find(Fabricante.class, codigo);
	}
	
	@SuppressWarnings("unchecked")
	public List<Fabricante> buscarComPaginacao(int first, int pageSize) {
		
		return em.createNamedQuery("Fabricante.buscarTodos")
				              .setFirstResult(first)
				              .setMaxResults(pageSize)
				              .getResultList();
	}

	public Long encontrarQuantidadeDeFabricantes() {
		
		return em.createQuery("select count(f) from Fabricante f", Long.class).getSingleResult();
	}
	
}
