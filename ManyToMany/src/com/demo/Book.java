package com.demo;

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
@Table(name="Book")
public class Book {

	@Id
	@GeneratedValue
	@Column(name="book_id")
	private int bid;
	
	@Column(name="book_name")
	private String bname;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="author_book", joinColumns=@JoinColumn(name="book_id"),inverseJoinColumns=@JoinColumn(name="author_id"))
	private Set<Author> authors;

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
}
