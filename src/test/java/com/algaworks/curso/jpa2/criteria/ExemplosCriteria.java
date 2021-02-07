package com.algaworks.curso.jpa2.criteria;
//AULA v8.3-projecoes-v1
//TESTE PARA FAZER UM CONSULTA COM CRITERIA -- FAZENDO UM FROM NO CARRO E TRAZENDO APENAS A PLACA
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

import com.algaworks.curso.jpa2.modelo.Carro;

public class ExemplosCriteria {
	
	
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
	public void projecoes() {///metodo para buscar as projeções co CRITERIA
		//buscar somente a placa do carro
		CriteriaBuilder builder = manager.getCriteriaBuilder();//OBJ que auxilia na criação dos demais OBJ na criteria
		CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);//dizendo  que deve retornar uma STRING
		
		Root<Carro> carro = criteriaQuery.from(Carro.class);//vou fazer o FROM  em cima de CARRO
		criteriaQuery.select(carro.<String>get("placa"));//só que no SELECT me devolva apenas a placa
		
		TypedQuery<String> query = manager.createQuery(criteriaQuery);
		List<String> placas = query.getResultList();
		
		for(String  placa : placas) {
			System.out.println(placa);
		}
	}
	
	
}
