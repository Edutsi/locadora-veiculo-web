package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.modelo.Fabricante;
import com.algaworks.curso.jpa2.service.CadastroFabricanteService;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

///esta classe esta diretamente ligada a tela
//função, pegar os dados do formulario da tela e mandar pra baixo

///@named  - faze parte do framework CDI que ajuda no processo de injeção de dependencia 

//viewScoped - enquanto eu estiver naquela tela, só vai ter um OBJ daquele bean/ trocou de página, trocou de bean
@Named
@ViewScoped
public class CadastroFabricanteBean implements Serializable{
	
	
	@Inject///injetando o OBJ fabricante.
	private CadastroFabricanteService cadastroFabricanteService;
	
	private Fabricante fabricante;
	
	public void salvar() {
		try {
			this.cadastroFabricanteService.salvar(fabricante);
			FacesUtil.addSuccessMessage("Fabricante salvo com sucesso!");
			
			this.limpar();//limpa a mensagem "Fabricante salvo com sucesso!"
		} catch (NegocioException e) {
			
			//e.printStackTrace();
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	//o INIT é chamado toda vez que esse bean for criado
	@PostConstruct
	public void init() {
		this.limpar();
	}
	public void limpar() {
		this.fabricante = new Fabricante();
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
	
	
	
	
	
	
}
