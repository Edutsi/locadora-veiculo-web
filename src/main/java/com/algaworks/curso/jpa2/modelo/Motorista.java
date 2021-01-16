package com.algaworks.curso.jpa2.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
@NamedQuery(name ="Motorista.buscarTodos", query="select m from Motorista m")

})
@DiscriminatorValue("MOTORISTA")
public class Motorista extends Pessoa{
	
	private String numeroCNH;

	public String getNumeroCNH() {
		return numeroCNH;
	}

	public void setNumeroCNH(String numeroCNH) {
		this.numeroCNH = numeroCNH;
	}

}
