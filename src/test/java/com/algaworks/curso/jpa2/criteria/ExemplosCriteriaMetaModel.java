
 package com.algaworks.curso.jpa2.criteria;
 import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

///v8.9-subqueries-v1
///
 import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;

 public class  ExemplosCriteriaMetaModel {
 	
 	
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
 	//Para executar cada test individual ALT+SHIFT+X T
 	@Test
 	public void exemploMetamodel() {
 		
 		CriteriaBuilder builder = manager.getCriteriaBuilder();
 		CriteriaQuery<Carro>criteriaQuery = builder.createQuery(Carro.class);
 		
 		Root<Carro> carro = criteriaQuery.from(Carro.class);
 		Join<Carro, ModeloCarro> modelo = (Join) carro.fetch("modelo");
 		
 		criteriaQuery.select(carro);
 		criteriaQuery.where(builder.equal(modelo.get("desccricao"), "Fit"));

 		
 		TypedQuery<Carro> query = manager.createQuery(criteriaQuery);
 		List<Carro> carros = query.getResultList();
 		
 		for(Carro c : carros){
 			System.out.println("Placa: "+ c.getPlaca()+"-"+" modelo: "+c.getModelo());
 		}
 	
 	}
 	
 	
 	
 }

