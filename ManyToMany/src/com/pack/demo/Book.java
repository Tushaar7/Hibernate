package com.pack.demo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book {
	
	@Id
	@GeneratedValue
	@Column(name="bookid")
	private Long bookId;
	
	@Column(name="bookname")
	private String bookName;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="authorbook", joinColumns=@JoinColumn(name="bookId"), inverseJoinColumns=@JoinColumn(name="authorId"))
	@JoinColumn(name="authorid")
	private Set<Author> authors;

	public Long getBookId() {
		return bookId;
	}
	
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	
	public String getBookName() {
		return bookName;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public Set<Author> getAuthors() {
		return authors;
	}
	
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
}
