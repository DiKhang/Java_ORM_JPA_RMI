package entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class OrderDetail_PK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6530976625982936834L;
	
	private Long book;
	private Long order;
	
	public Long getBook() {
		return book;
	}
	public void setBook(Long book) {
		this.book = book;
	}
	public Long getOrder() {
		return order;
	}
	public void setOrder(Long order) {
		this.order = order;
	}
	public OrderDetail_PK(Long book, Long order) {
		this.book = book;
		this.order = order;
	}
	public OrderDetail_PK() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetail_PK other = (OrderDetail_PK) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OrderDetail_PK [book=" + book + ", order=" + order + "]";
	}
	
	
}
