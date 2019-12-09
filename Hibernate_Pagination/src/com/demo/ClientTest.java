package com.demo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class ClientTest {

	public static void main(String[] args) {
		
		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		
		Query query1 = session.createQuery("from Student");
		
		// Pagination
		
		query1.setFirstResult(4);
		query1.setMaxResults(5);
		
		List<Student> list1 = query1.list();
		
		for(Student s1 : list1)
			System.out.println(s1);
	
		System.out.println("--------------------------------------");
		
		//We want to fetch a specific column from database using select query
		//Here we going to fetch name column from database
		//so change List<Student> to List<String> bcoz name having String data type
		
		Query query2 = session.createQuery("select name from Student");
		query2.setFirstResult(0);
		query2.setMaxResults(5);
		
		List<String> list2 = query2.list();
		
		for(String s2 : list2)
			System.out.println(s2);
		
		session.getTransaction().commit();
		session.close();
		sessionfactory.close();

	}
}
