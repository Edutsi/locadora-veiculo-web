package com.algaworks.curso.jpa2.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
@NamedQuery(name ="Funcionario.buscarTodos", query="select f from Funcionario f")

})
@DiscriminatorValue("FUNCIONARIO")
public class Funcionario extends Pessoa{
	private String matricula;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	

}