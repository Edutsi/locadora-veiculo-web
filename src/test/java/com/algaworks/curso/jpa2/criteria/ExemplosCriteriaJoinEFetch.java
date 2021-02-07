package com.algaworks.curso.jpa2.criteria;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;//NAO IMPORTAR DO HIBERNATE
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

///v8.8-join-e-fetch-v1
//FETCCH faz a consulta e já tras o resultado - enquanto o JOIN só faz a consulta - melhora a performance 

import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;

public class ExemplosCriteriaJoinEFetch {
	
	
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
	public void exemploJoinEFetch() {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Carro>criteriaQuery = builder.createQuery(Carro.class);
		
		Root<Carro> carro = criteriaQuery.from(Carro.class);/// do OBJ carro chamamos os atributos a baixo
		Join<Carro, ModeloCarro> modelo = (Join) carro.join("modelo");
		
		criteriaQuery.select(carro);
		criteriaQuery.where(builder.equal(modelo.get("descricao"), "Fit"));
		
		TypedQuery<Carro> query = manager.createQuery(criteriaQuery);
		List<Carro> carros = query.getResultList();
		
		for(Carro c : carros) {
			System.out.println("Placa do carro: "+c.getPlaca()+"-"+"Valor da diária: "+ c.getValorDiaria());
		}
	
	
	}
	
}
