package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OrderDetails")
@IdClass(OrderDetail_PK.class)
public class OrderDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9074660877935362559L;

	@Id
	@ManyToOne
	@JoinColumn(name = "bookID")
	private Book book;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "orderID")
	private Order order;
	
	private int quantity;
	private double price;
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotelLine() {
		return price * quantity;
	}
	public OrderDetail(Book book, Order order, int quantity, double price) {
		super();
		this.book = book;
		this.order = order;
		this.quantity = quantity;
		this.price = price;
	}
	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OderDetail [book=" + book + ", order=" + order + ", quantity=" + quantity + ", price=" + price + "]";
	}
}
