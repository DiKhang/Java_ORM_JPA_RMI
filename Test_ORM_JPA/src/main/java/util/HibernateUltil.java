package util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class HibernateUltil {

	private static HibernateUltil instance = null;
	private EntityManager entityManager;

	public HibernateUltil() {
		super();
		entityManager = Persistence.createEntityManagerFactory("Test_ORM_JPA").createEntityManager();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public synchronized static HibernateUltil getInstance() {
		if (instance == null)
			instance = new HibernateUltil();
		return instance;
	}
	
	
}
