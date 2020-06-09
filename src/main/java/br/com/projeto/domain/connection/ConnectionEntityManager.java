package br.com.projeto.domain.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionEntityManager {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("todoProject");
	
	public EntityManager getConnection() {
		return entityManagerFactory.createEntityManager();
	}

}
