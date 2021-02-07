package com.algaworks.curso.jpa2.criteria;
import java.math.BigDecimal;
//v8.4-funcoes-de-agregacao-v1
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;//NAO IMPORTAR DO HIBERNATE
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.curso.jpa2.modelo.Aluguel;
import com.algaworks.curso.jpa2.modelo.Carro;

public class ExemplosCriteria2 {
	
	
	private static EntityManagerFactory factory;
	private EntityManager manager;
	
	@BeforeClass///executa o init() antes de iniciar os testes Só uma vez
	//beforeCLASS é estatico
	public static void init() {
		factory = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
	}

	@Before // não é estatico
	public void setUp() {///criacao do entity manager
		this.manager = factory.createEntityManager();
	}
	///AFTER e BEFORE acontece antes e depois de cada metodo executado com o @TEST
	//terminando de usar o entityManager deve ser fechado
	@After
	public void tearDown() {
		this.manager.close();
	
	}
	
	@Test
	///para teste vamos somar os valores recebidos dos alugueis
	//funcoes de agragação a baixo
			//builder.max
			//builder.sum
			//builder.min
	public void funcoesAgregacao() {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<BigDecimal> criteriaQuery = builder.createQuery(BigDecimal.class);
		
		Root<Aluguel> aluguel = criteriaQuery.from(Aluguel.class);
		criteriaQuery.select(builder.sum(aluguel.<BigDecimal>get("valorTotal")));
		// no JPQL o select seria assim: select sum(a.valorTotal) from Aluguel a
		
		TypedQuery<BigDecimal> query = manager.createQuery(criteriaQuery);
		BigDecimal total = query.getSingleResult();
		
		System.out.println("Soma de todos os alugueis: " + total);
		
		
	}
	
}
