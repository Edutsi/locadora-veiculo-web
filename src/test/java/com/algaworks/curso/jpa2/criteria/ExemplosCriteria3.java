package com.algaworks.curso.jpa2.criteria;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;//NAO IMPORTAR DO HIBERNATE
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

///v8.5-resultados-complexos-tuplas-e-construtores-v1
////// TRAS A MESMA CONSULTA DE FORMA DIFERENTES

import com.algaworks.curso.jpa2.modelo.Carro;

public class ExemplosCriteria3 {
	
	
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
	///neste teste vou chamar um OBJ para usar vário atributos dele
	public void resultadoComplexo() {
		//CHAMA O RESULTADO PELO ARRAY
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Object[]>criteriaQuery = builder.createQuery(Object[].class);
		
		Root<Carro> carro = criteriaQuery.from(Carro.class);/// do OBJ carro chamamos os atributos a baixo
		criteriaQuery.multiselect(carro.get("placa"), carro.get("valorDiaria"));
		
		TypedQuery<Object[]> query = manager.createQuery(criteriaQuery);
		List<Object[]> resultado = query.getResultList();
		
		for(Object[] valores : resultado) {
			System.out.println("Placa do carro: " + valores[0]+"-"+"Valor da diária -"+ valores[1]);
		}
	}
	@Test
	public void resultadoTupla() {
		//CHAMA O RESULTADO PELO INDICE
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteriaQuery = builder.createTupleQuery();
		
		Root<Carro> carro = criteriaQuery.from(Carro.class);
		criteriaQuery.multiselect(carro.get("placa").alias("placaCarro")
				, carro.get("valorDiaria").alias("valorCarro"));
		
		TypedQuery<Tuple> query = manager.createQuery(criteriaQuery);
		List<Tuple> resultado = query.getResultList();
		for(Tuple tupla : resultado) {
		System.out.println(tupla.get("placaCarro")+" - "+tupla.get("valorCarro"));
			
		}
		
	}
	@Test
	public void resultadoConstrutores() {
		//MAIS ALTO NIVEL- USA ORIENTAÇÃO AO OBJETO CONSTRUINDO UM OBJ
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<PrecoCarro> criteriaQuery = builder.createQuery(PrecoCarro.class);
		
		Root<Carro> carro = criteriaQuery.from(Carro.class);
		criteriaQuery.select(builder.construct(PrecoCarro.class, 
						carro.get("placa"), carro.get("valorDiaria")));
		
		TypedQuery<PrecoCarro> query = manager.createQuery(criteriaQuery);
		List<PrecoCarro> resultado = query.getResultList();
		
		for(PrecoCarro precoCarro : resultado) {
			System.out.println(precoCarro.getPlaca()+" - "+precoCarro.getValor());
		}
	}
	
}
