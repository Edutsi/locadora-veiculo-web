package com.algaworks.curso.jpa2.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
@NamedQuery(name ="ModeloCarro.buscarTodos", query="select m from ModeloCarro m")

})
public class ModeloCarro {

	private Long codigo;
	private String descricao;
	private Fabricante fabricante;
	private Categoria categoria;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@ManyToOne(fetch=FetchType.LAZY)//anotação para um classe - aula 8.8 para que o JOIN FETCH não traga tudo que foi consultado pq o default é FetchType.EAGER mudei para o LAZY que traz so o pedido
	@JoinColumn(name="codigo_fabricante")
	public Fabricante getFabricante() {
		return fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	//@Enumerated(EnumType.ORDINAL)//se quiser guardar o código em vez da string 
	@Enumerated(EnumType.STRING)//enumType.string - anotação para guardar a palavra inteira EX: HATCH_COMPACTO
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModeloCarro other = (ModeloCarro) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
