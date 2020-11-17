package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import com.algaworks.curso.jpa2.modelo.ModeloCarro;
import com.algaworks.curso.jpa2.service.NegocioException;

public class ModeloCarroDAO implements Serializable{
	
	@Inject
	private EntityManager manager;
	
	public ModeloCarro buscarPeloCodigo(Long codigo){
		return manager.find(ModeloCarro.class, codigo);
		
	}
	public void salvar(ModeloCarro modeloCarro) {
		manager.merge(modeloCarro);//para alterações, pois depois de desconectar do obj o merge junta as alterações de antes e depois da desconexão
	}
	public List<ModeloCarro>buscarTodos(){
		return manager.createQuery("from ModeloCarro").getResultList();
	}
	
	@Transactional
	public void excluir(ModeloCarro modeloCarro) throws NegocioException{
		modeloCarro = buscarPeloCodigo(modeloCarro.getCodigo());
		try {
			manager.remove(modeloCarro);
			manager.flush();
		}catch(PersistenceException e) {
			throw new NegocioException("Este modelo não pode ser excluído. ");
		}
		
	}
	
	

}
