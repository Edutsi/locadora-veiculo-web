package com.algaworks.curso.jpa2.service;

import java.io.Serializable;


import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.AcessorioDAO;
import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class CadastroAcessorioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AcessorioDAO acessorioDAO;
	
	@Transactional
	public void salvar(Acessorio acessorio) throws NegocioException {
		
		/*if (acessorio.getDescricao() == null || acessorio.getDescricao().trim().equals("")) {
			throw new NegocioException("A descrição do acessório é obrigatório");
		} usando o bean.validation, não preciso desse IF v11.7-bean-validation-v1
		ideal para deixar só a logica de negocio e não logica de validação*/
		
		this.acessorioDAO.salvar(acessorio);
	}

}
