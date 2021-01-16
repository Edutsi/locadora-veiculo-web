package com.algaworks.curso.jpa2.modelolazy;
///IMPLEMENTANDO O LAZY LOAD A PAGINAÇÃO FICA ARMAZENADA NO BANCO E NÃO NA MEMÓRIA
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.algaworks.curso.jpa2.dao.MotoristaDAO;
import com.algaworks.curso.jpa2.modelo.Motorista;

public class LazyMotoristaDataModel extends LazyDataModel<Motorista> implements Serializable {

	private MotoristaDAO MotoristaDAO;
	
    public LazyMotoristaDataModel(MotoristaDAO MotoristaDAO) {
    	this.MotoristaDAO = MotoristaDAO;
    	
    }
    
    @Override
	public List<Motorista> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		List<Motorista> Motoristas = this.MotoristaDAO.buscarComPaginacao(first, pageSize);
		
		this.setRowCount(this.MotoristaDAO.encontrarQuantidadeDeMotoristas().intValue());
		
		return Motoristas;
	}
}
