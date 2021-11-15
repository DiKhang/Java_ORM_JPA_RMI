package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "Books")
@NamedNativeQueries({
	@NamedNativeQuery(name = "getBookByTitle", query = "Select * from Books where title = :x", resultClass = Book.class)
})
public class Book implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	private String title;
	private String idbn;
	private int pageCount;
	private LocalDate publishedDate;
	private String status;
	
	@ElementCollection
	@CollectionTable(name = "Authors", joinColumns = @JoinColumn(name = "bookID"))
	@LazyCollection(LazyCollectionOption.FALSE)
	@Column(name = "author", nullable = false)
	private Set<String> authors;
	
	@OneToMany(mappedBy = "book")
	private List<OrderDetail> item = new ArrayList<OrderDetail>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIdbn() {
		return idbn;
	}

	public void setIdbn(String idbn) {
		this.idbn = idbn;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public LocalDate getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<String> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<String> authors) {
		this.authors = authors;
	}

	public List<OrderDetail> getItem() {
		return item;
	}

	public void setItem(List<OrderDetail> item) {
		this.item = item;
	}

	public Book(Long id, String title, String idbn, int pageCount, LocalDate publishedDate, String status,
			Set<String> authors) {
		super();
		this.id = id;
		this.title = title;
		this.idbn = idbn;
		this.pageCount = pageCount;
		this.publishedDate = publishedDate;
		this.status = status;
		this.authors = authors;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", idbn=" + idbn + ", pageCount=" + pageCount
				+ ", publishedDate=" + publishedDate + ", status=" + status + ", authors=" + authors + "]";
	}
	
	
}
