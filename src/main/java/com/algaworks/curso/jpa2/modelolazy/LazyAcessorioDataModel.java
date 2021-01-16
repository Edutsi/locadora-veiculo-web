package com.algaworks.curso.jpa2.modelolazy;
///IMPLEMENTANDO O LAZY LOAD A PAGINAÇÃO FICA ARMAZENADA NO BANCO E NÃO NA MEMÓRIA
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.algaworks.curso.jpa2.dao.AcessorioDAO;
import com.algaworks.curso.jpa2.modelo.Acessorio;

public class LazyAcessorioDataModel extends LazyDataModel<Acessorio> implements Serializable {

	private AcessorioDAO acessorioDAO;
	
    public LazyAcessorioDataModel(AcessorioDAO acessorioDAO) {
    	this.acessorioDAO = acessorioDAO;
    	
    }
    
    @Override
	public List<Acessorio> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		List<Acessorio> acessorios = this.acessorioDAO.buscarComPaginacao(first, pageSize);
		
		this.setRowCount(this.acessorioDAO.encontrarQuantidadeDeAcessorios().intValue());
		
		return acessorios;
	}
}
