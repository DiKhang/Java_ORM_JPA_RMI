package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.OrderDetailDao;
import entity.OrderDetail;
import util.HibernateUltil;

public class OrderDetailImpl extends UnicastRemoteObject implements OrderDetailDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1114758424249045746L;
	private EntityManager em;

	public OrderDetailImpl() throws RemoteException {
		em = HibernateUltil.getInstance().getEntityManager();
	}

	@Override
	public boolean addOrderDetail(OrderDetail odd) throws RemoteException {
		boolean result = false;

		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();

			em.persist(odd);
			
			tr.commit();
			result = true;
		}catch (RuntimeException e) {
			tr.rollback();
			throw e;
		}

		return result;
	}

}
