package com.algaworks.curso.jpa2.modelolazy;
///IMPLEMENTANDO O LAZY LOAD A PAGINAÇÃO FICA ARMAZENADA NO BANCO E NÃO NA MEMÓRIA
import java.io.Serializable;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.algaworks.curso.jpa2.dao.FabricanteDAO;
import com.algaworks.curso.jpa2.modelo.Fabricante;

public class LazyFabricanteDataModel extends LazyDataModel<Fabricante> implements Serializable {

	private FabricanteDAO FabricanteDAO;
	
    public LazyFabricanteDataModel(FabricanteDAO FabricanteDAO) {
    	this.FabricanteDAO = FabricanteDAO;
    	
    }
    
    @Override
	public List<Fabricante> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		List<Fabricante> Fabricantes = this.FabricanteDAO.buscarComPaginacao(first, pageSize);
		
		this.setRowCount(this.FabricanteDAO.encontrarQuantidadeDeFabricantes().intValue());
		
		return Fabricantes;
	}
}
