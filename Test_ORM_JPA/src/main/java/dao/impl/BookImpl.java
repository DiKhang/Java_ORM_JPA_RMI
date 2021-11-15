package dao.impl;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.BookDao;
import entity.Book;
import util.HibernateUltil;

public class BookImpl extends UnicastRemoteObject implements BookDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1252335730030884096L;
	private EntityManager em;
	
	public BookImpl() throws RemoteException {
		em = HibernateUltil.getInstance().getEntityManager();
	}
	@Override
	public boolean addBook(Book book) throws RemoteException {
		boolean rs = false;
		
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(book);
			tr.commit();
			rs = true;
		} catch (RuntimeException e) {
			tr.rollback();
			throw e;
		}
		return rs;
	}

	@Override
	public Book getBookByTitle(String title) throws RemoteException {
		Book book = null;
		
		List<Book> list = em.createNamedQuery("getBookByTitle", Book.class)
				.setParameter("x", title)
				//.getSingleResult(); //-> Chỉ dùng khi cột title là unique 
				.getResultList();
		
		if(list.size() > 0)
			book = list.get(0);
		
		return book;
	}

//	select b.id, num = sum(quantity) 
//	from Books b inner join OrderDetails odd on b.id = odd.bookID
//	group by b.id
	@Override
	public Map<Book, Integer> getBookStatistics() throws RemoteException {
		Map<Book, Integer> map = new HashMap<Book, Integer>();
		
		String sqlString = "select b.id, num = sum(quantity) " + 
				"from Books b inner join OrderDetails odd on b.id = odd.bookID " + 
				"group by b.id";
		List<?> temp = em.createNativeQuery(sqlString).getResultList();
		for(Object o : temp) {
			Object[] rs = (Object[]) o;
			
			Long id = ((BigInteger)rs[0]).longValue();
			Book book = em.find(Book.class, id );
			map.put(book, (Integer) rs[1]);
		}
		
		return map;
	}

}
