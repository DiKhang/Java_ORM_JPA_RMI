package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

import entity.Book;

public interface BookDao extends Remote{

	public boolean addBook(Book book) throws RemoteException;
	public Book getBookByTitle(String title) throws RemoteException;
	public Map<Book, Integer> getBookStatistics() throws RemoteException;
}
