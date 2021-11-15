package app;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.time.LocalDateTime;

import dao.BookDao;
import dao.OrderDao;
import dao.OrderDetailDao;
import dao.impl.BookImpl;
import dao.impl.OrderDetailImpl;
import dao.impl.OrderImpl;

public class App {

	public static void main(String[] args) {
		SecurityManager securityManager = System.getSecurityManager();
		if (securityManager == null) {
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			BookDao bookDao = new BookImpl();
			OrderDao orderDao = new OrderImpl();
			OrderDetailDao orderDetailDao = new OrderDetailImpl();
			
			String url = "rmi://DESKTOP-JUE6O6V:";
			int port = 9062;
			
			LocateRegistry.createRegistry(port);
			
			Naming.bind(url + port + "/bookDao", bookDao);
			Naming.bind(url + port + "/orderDao", orderDao);
			Naming.bind(url + port + "/orderDatailDao", orderDetailDao);
			
			System.out.println(url + port + "/bookDao");
			System.out.println(url + port + "/orderDao");
			System.out.println(url + port + "/orderDatailDao");
			System.out.println("Server stated at " + LocalDateTime.now());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
