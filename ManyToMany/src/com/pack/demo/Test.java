package com.pack.demo;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
		Set<Author> a = new HashSet<Author>();
		Set<Author> b = new HashSet<Author>();
		Set<Author> c = new HashSet<Author>();
		
		Author author1 = new Author();
		author1.setAuthorName("James Page");
		a.add(author1);
		
		Author author2 = new Author();
		author2.setAuthorName("John Doe");
		b.add(author2);
		
		Author author3 = new Author();
		author3.setAuthorName("Adam Gill");
		c.add(author3);
		
		Book book1 = new Book();
		book1.setBookName("Core Java");
		
		Book book2 = new Book();
		book2.setBookName("Advance Java");
		
		Book book3 = new Book();
		book3.setBookName("Framework of Java");
		
		book1.setAuthors(a);
		book2.setAuthors(b);
		book3.setAuthors(c);
		
		session.save(book1);
		session.save(book2);
		session.save(book3);
		
		t.commit();
		session.close();
		sf.close();
		
		System.out.println("Done...");
	}
}
