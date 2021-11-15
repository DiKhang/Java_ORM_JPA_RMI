package app;

import java.rmi.Naming;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import dao.BookDao;
import dao.OrderDao;
import dao.OrderDetailDao;
import entity.Book;
import entity.Order;
import entity.OrderDetail;

public class App {

	public static void main(String[] args) {
		SecurityManager securityManager = System.getSecurityManager();
		if(securityManager == null) {
			
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
			
		}
		
		try {
			
			BookDao bookDao = (BookDao) Naming.lookup("rmi://DESKTOP-JUE6O6V:9062/bookDao");
			OrderDao orderDao =  (OrderDao) Naming.lookup("rmi://DESKTOP-JUE6O6V:9062/orderDao");
			OrderDetailDao orderDetailDao = (OrderDetailDao) Naming.lookup("rmi://DESKTOP-JUE6O6V:9062/orderDatailDao");
			
			Set<String> authors1 = new HashSet<String>();
			authors1.add("W. Frank Ableson");
			authors1.add("Robi Sen");
			Book book1 = new Book(1011l, "Android in Action, Second Edition", "1935182722", 592, LocalDate.of(2011, 1, 14), "PUBLISH", authors1);
			
			Set<String> authors2 = new HashSet<String>();
			authors2.add("Satnam Alag");
			Book book2 = new Book(1022l, "Collective Intelligence in Action", "1933988312", 425, LocalDate.of(2008, 10, 1), "PUBLISH", authors2);
			
			Set<String> authors3 = new HashSet<String>();
			authors3.add("Jeremy Anderson");
			authors3.add("Bernerd Allmon");
			Book book3 = new Book(10313l, "Flex on Java", "1933988797", 265, LocalDate.of(2010, 10, 15), "PUBLISH", authors3);
			
			Order order1 = new Order(1111l, "Alexander Hunold", LocalDate.now().plusDays(5), null, 0);
			Order order2 = new Order(2112l, "Nancy Greenberg", LocalDate.now().plusDays(10), null, 0);
			
			OrderDetail orderDetail1 = new OrderDetail(book1, order1, 1, 12.5);
			OrderDetail orderDetail2 = new OrderDetail(book3, order1, 10, 14);
			
			OrderDetail orderDetail3 = new OrderDetail(book1, order2, 5, 12.5);
			OrderDetail orderDetail4 = new OrderDetail(book2, order2, 15, 5);
			OrderDetail orderDetail5 = new OrderDetail(book3, order2, 10, 14);
			
			
			
			  bookDao.addBook(book1); bookDao.addBook(book2); bookDao.addBook(book3);
			  
			  orderDao.addOrder(order1); orderDao.addOrder(order2);
			  
			  orderDetailDao.addOrderDetail(orderDetail1);
			  orderDetailDao.addOrderDetail(orderDetail2);
			  orderDetailDao.addOrderDetail(orderDetail3);
			  orderDetailDao.addOrderDetail(orderDetail4);
			  orderDetailDao.addOrderDetail(orderDetail5);
			 
			
			//Kiểm thử câu 2b
			  Book bx = bookDao.getBookByTitle("ads"); //No entity found for query
			  System.out.println(bx);
			
			
			  Book by = bookDao.getBookByTitle("Android in Action, Second Edition");
			  System.out.println(by);
			 
			//Kiểm thử câu 2c
			bookDao.getBookStatistics().forEach((book, quantity) -> {
				System.out.println(book);
				System.out.println("Total: " + quantity);
				System.out.println("========");
			});
			
			//Kiểm thử câu 2d
			 orderDao.updateOrdersByTotal(); 
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
