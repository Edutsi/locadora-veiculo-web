

/*
v9.4-exclusao-em-cascata-v1
Exemplo de exclusão de um carro alugado
*/

package com.algaworks.curso.jpa2.criteria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.curso.jpa2.modelo.Acessorio;
import com.algaworks.curso.jpa2.modelo.Carro;
import com.algaworks.curso.jpa2.modelo.Categoria;
import com.algaworks.curso.jpa2.modelo.ModeloCarro;

public class ExemplosCriteriaExclusaoEmCascata {

	private static EntityManagerFactory factory;
	private EntityManager manager;

	@BeforeClass /// executa o init() antes de iniciar os testes Só uma vez
	// beforeCLASS é estatico
	public static void init() {
		factory = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
	}

	@Before // não é estatico
	public void setUp() {/// criacao do entity manager
		this.manager = factory.createEntityManager();
	}

	/// AFTER e BEFORE acontece antes e depois de cada metodo executado com o @TEST
	// terminando de usar o entityManager deve ser fechado
	@After
	public void tearDown() {
		this.manager.close();

	}

	// Para executar cada test individual ALT+SHIFT+X T
	@Test
	public void exemploEntidadeTransiente() {
		Carro carro = this.manager.find(Carro.class, 2L);
	
		this.manager.getTransaction().begin();
		this.manager.remove(carro);
		this.manager.getTransaction().commit();
	}

}