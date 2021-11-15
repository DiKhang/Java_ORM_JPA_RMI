package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import entity.Order;

public interface OrderDao extends Remote{

	public boolean addOrder(Order od) throws RemoteException;	
	public void updateOrdersByTotal() throws RemoteException;
}
