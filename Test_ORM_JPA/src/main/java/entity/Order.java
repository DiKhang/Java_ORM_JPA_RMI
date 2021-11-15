package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long orderID;
	private String employeeName;
	private LocalDate orderDate;
	private LocalDate shippedDate;
	private double total;
	
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> listItem = new ArrayList<OrderDetail>();

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(LocalDate shippedDate) {
		this.shippedDate = shippedDate;
	}

	public double getTotal() {
		double rs = 0.0;
		
		for (OrderDetail oderDetail : listItem) {
			rs += oderDetail.getTotelLine();
		}
		
		return rs;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<OrderDetail> getListItem() {
		return listItem;
	}

	public void setListItem(List<OrderDetail> listItem) {
		this.listItem = listItem;
		this.setTotal(this.getTotal());
	}

	public Order(Long orderID, String employeeName, LocalDate orderDate, LocalDate shippedDate, double total) {
		super();
		this.orderID = orderID;
		this.employeeName = employeeName;
		this.orderDate = orderDate;
		this.shippedDate = shippedDate;
		this.total = total;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", employeeName=" + employeeName + ", orderDate=" + orderDate
				+ ", shippedDate=" + shippedDate + ", total=" + total + "]";
	}
	
}
