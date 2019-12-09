package com.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestMain {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Address address1 = new Address("US Road","US","ABC","1234");
		Address address2 = new Address("RUS Road","RUS","PQR","5678");
		
		Student student1 = new Student("Fread", address1);
		Student student2 = new Student("Alex", address2);
		
		session.save(student1);
		session.save(student2);
		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
}
