package com.algaworks.curso.jpa2.modelolazy;
///IMPLEMENTANDO O LAZY LOAD A PAGINAÇÃO FICA ARMAZENADA NO BANCO E NÃO NA MEMÓRIA
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.algaworks.curso.jpa2.dao.FuncionarioDAO;
import com.algaworks.curso.jpa2.modelo.Funcionario;

public class LazyFuncionarioDataModel extends LazyDataModel<Funcionario> implements Serializable {

	private FuncionarioDAO FuncionarioDAO;
	
    public LazyFuncionarioDataModel(FuncionarioDAO FuncionarioDAO) {
    	this.FuncionarioDAO = FuncionarioDAO;
    	
    }
    
    @Override
	public List<Funcionario> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		List<Funcionario> Funcionarios = this.FuncionarioDAO.buscarComPaginacao(first, pageSize);
		
		this.setRowCount(this.FuncionarioDAO.encontrarQuantidadeDeFuncionarios().intValue());
		
		return Funcionarios;
	}
}
