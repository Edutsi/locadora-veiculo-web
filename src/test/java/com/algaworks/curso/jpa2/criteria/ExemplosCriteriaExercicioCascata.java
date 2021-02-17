/*
 9.2. Gravação em cascata
Faça a gravação em cascata entre Carro e Acessório.
A ideia é você criar um método de teste que irá criar um Carro, vários Acessórios NOVOS, adicioná-los na lista e salvar o carro. Neste momento, todos os acessórios deverão ser salvos também.

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

public class ExemplosCriteriaExercicioCascata {

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
		Carro carro = new Carro();
		carro.setCor("Vermelho");
		carro.setPlaca("MMA-2222");

		ModeloCarro modelo = new ModeloCarro();
		modelo.setCategoria(Categoria.ESPORTIVO);
		modelo.setDescricao("Masserati");
		carro.setModelo(modelo);
		
		Acessorio acessorio = new Acessorio();
		acessorio.setDescricao("Teto solar");
		

		this.manager.getTransaction().begin();
		this.manager.persist(carro);
		this.manager.getTransaction().commit();
	}

}