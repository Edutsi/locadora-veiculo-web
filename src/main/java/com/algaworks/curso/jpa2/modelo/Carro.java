package com.algaworks.curso.jpa2.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
@NamedQuery(name ="Carro.buscarTodos", query="select c from Carro c inner join fetch c.modelo"),//aula 8.8 inserido o join FETCH para melhorar a performance do banco
@NamedQuery(name="Carro.buscarCarroComAcessorios", query="select c "
		                                              + "from Carro c JOIN c.acessorios a"
		                                              + " where c.codigo = :codigo")//JPQL


})
public class Carro {

	private Long codigo;
	private String placa;
	private String cor;
	private String chassi;
	private BigDecimal valorDiaria;
	private ModeloCarro modelo;
	private List<Acessorio> acessorios;
	private List<Aluguel> alugueis;//para guardar o historico de algueis de um carro
	
	
	private Date dataCriacao;
	private Date dataModificacao;
	private byte[] foto;//v10.6-objetos-grandes-v1
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	
	public BigDecimal getValorDiaria() {
		return valorDiaria;
	}
	public void setValorDiaria(BigDecimal valorDiaria) {
		this.valorDiaria = valorDiaria;
	}
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)//retornar os modelos pedidos e não todos com o EAGer alteração feita na aula 8.8
	 //cascade implantado na aula 9.1
	
	@JoinColumn(name="codigo_modelo")
	public ModeloCarro getModelo() {
		return modelo;
	}
	public void setModelo(ModeloCarro modelo) {
		this.modelo = modelo;
	}
	
	//@ManyToMany(fetch=FetchType.EAGER)//toda vez q buscar um carro tras todos os acessorios- icone acessorios pesado pois traz toos os acessorios
	@ManyToMany(fetch=FetchType.LAZY)//Lazy nao traz todos os acessorios-retorna somente o acessorio do ítem requisitado
	@JoinTable(name="carro_acessorio"
				, joinColumns=@JoinColumn(name="codigo_carro")
				, inverseJoinColumns=@JoinColumn(name="codigo_acessorio"))
	public List<Acessorio> getAcessorios() {
		return acessorios;
	}
	public void setAcessorios(List<Acessorio> acessorios) {
		this.acessorios = acessorios;
	}
	///util para liberar q o gerente apague um carro
	//@OneToMany(mappedBy= "carro", cascade=CascadeType.REMOVE)//somente para teste da aula 9.4-exclusao-em-cascata-v1- pq permite apagar um carro q tenha um aluguel
	//@OneToMany(mappedBy= "carro", cascade=CascadeType.PERSIST, orphanRemoval=true)//aula9.5 remover obj orfao
	@OneToMany(mappedBy= "carro")//foi mapeado na classe aluguel no atributo carro
	public List<Aluguel> getAlugueis() {
		return alugueis;
	}
	public void setAlugueis(List<Aluguel> alugueis) {
		this.alugueis = alugueis;
	}
	
	@Temporal(TemporalType.TIMESTAMP)///mapeando data e hora
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataModificacao() {
		return dataModificacao;
	}
	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}
	@PrePersist/// o metodo vai ser executado antes de ser persistido
	@PreUpdate///executa o metodo antes de ser atualizado
	public void configuraDatasCriacaoAlteracao() {
		this.dataModificacao = new Date();
		
		if(this.dataCriacao ==null){
			this.dataCriacao = new Date();
		}
	}
	@Lob//anotacao para salvar a foto
	@Column(name="foto_carro")
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
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
		Carro other = (Carro) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
