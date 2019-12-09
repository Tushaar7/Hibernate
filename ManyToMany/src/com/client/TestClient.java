package com.client;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.Author;
import com.demo.Book;

public class TestClient {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Set<Author> a1 = new HashSet<Author>();
		Set<Author> a2 = new HashSet<Author>();
		Set<Author> a3 = new HashSet<Author>();
		
		Set<Book> b1 = new HashSet<Book>();
		Set<Book> b2 = new HashSet<Book>();
		
		Author author1 = new Author();
		author1.setAname("Alex");
		a1.add(author1);
		
		Author author2 = new Author();
		author2.setAname("Brown");
		a2.add(author1);
		a3.add(author2);
		
		a2.add(author1);
		a2.add(author2);
		a3.add(author2);
		
		Book book1 = new Book();
		book1.setBname("Core Java");

		Book book2 = new Book();
		book2.setBname("Advance Java");

		Book book3 = new Book();
		book3.setBname("Framework of Java");
		
		b1.add(book1);
		b1.add(book2);
		b2.add(book2);
		b2.add(book3);
		
		author1.setBooks(b1);
		author2.setBooks(b2);
		book1.setAuthors(a1);
		book2.setAuthors(a2);
		book3.setAuthors(a3);

		session.save(author1);
		session.save(author2);
		
		session.getTransaction().commit();
		System.out.println("Done...");
		session.close();
		sessionFactory.close();
	}
}
