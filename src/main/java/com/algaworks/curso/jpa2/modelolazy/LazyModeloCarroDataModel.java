package com.algaworks.curso.jpa2.modelolazy;
///IMPLEMENTANDO O LAZY LOAD A PAGINAÇÃO FICA ARMAZENADA NO BANCO E NÃO NA MEMÓRIA
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.algaworks.curso.jpa2.dao.ModeloCarroDAO;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;

public class LazyModeloCarroDataModel extends LazyDataModel<ModeloCarro> implements Serializable {

	private ModeloCarroDAO ModeloCarroDAO;
	
    public LazyModeloCarroDataModel(ModeloCarroDAO ModeloCarroDAO) {
    	this.ModeloCarroDAO = ModeloCarroDAO;
    	
    }
    
    @Override
	public List<ModeloCarro> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		List<ModeloCarro> ModeloCarros = this.ModeloCarroDAO.buscarComPaginacao(first, pageSize);
		
		this.setRowCount(this.ModeloCarroDAO.encontrarQuantidadeDeModeloCarros().intValue());
		
		return ModeloCarros;
	}
}
