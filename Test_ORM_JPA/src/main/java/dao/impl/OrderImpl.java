package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.OrderDao;
import entity.Order;
import util.HibernateUltil;

public class OrderImpl extends UnicastRemoteObject implements OrderDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5519997658187631310L;
	private EntityManager em;
	
	public OrderImpl() throws RemoteException {
		em = HibernateUltil.getInstance().getEntityManager();
	}

	@Override
	public boolean addOrder(Order od) throws RemoteException {
		boolean result = false;

		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();

			em.persist(od);

			tr.commit();
			result = true;
		}catch (RuntimeException e) {
			tr.rollback();
			throw e;
		}

		return result;
	}

	@Override
	public void updateOrdersByTotal() throws RemoteException {
		EntityTransaction tr = em.getTransaction();

		List<Order> orders = em.createQuery("select od from Order od", Order.class).getResultList();
		for(Order od : orders) {
			try {
				tr.begin();
				od.setTotal(od.getTotal());
				em.merge(od);
				tr.commit();

			}catch (RuntimeException e) {
				tr.rollback();
				throw e;
			}
		}
		
	}

}
