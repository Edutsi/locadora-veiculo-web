package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class ModeloCarroDAO implements Serializable {

	@Inject
	private EntityManager manager;
	
	public ModeloCarro buscarPeloCodigo(Long codigo) {
		return manager.find(ModeloCarro.class, codigo);
	}
	
	public void salvar(ModeloCarro modeloCarro) {
		manager.merge(modeloCarro);
	}

	public List<ModeloCarro> buscarTodos() {
		return manager.createQuery("from ModeloCarro").getResultList();
	}
	
	@Transactional
	public void excluir(ModeloCarro modeloCarro) throws NegocioException {
		modeloCarro = buscarPeloCodigo(modeloCarro.getCodigo());
		try {
			manager.remove(modeloCarro);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Este modelo não pode ser excluído.");
		}
	}
	@SuppressWarnings("unchecked")
	public List<ModeloCarro> buscarComPaginacao(int first, int pageSize) {

		return manager.createNamedQuery("ModeloCarro.buscarTodos").setFirstResult(first).setMaxResults(pageSize)
				.getResultList();
	}

	public Long encontrarQuantidadeDeModeloCarros() {

		return manager.createQuery("select count(m) from Motorista m", Long.class).getSingleResult();
	}
	
}
