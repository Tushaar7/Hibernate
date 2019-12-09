package com.demo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class TestMain {

	public static void main(String[] args) {
		
		EmployeeDetails employee = new EmployeeDetails();
		employee.setId(20);
		employee.setName("Tom");
		employee.setAddress("AUS");
		employee.setEmail("tom@yahoo.com");
		employee.setTelephone("1234509876");
		
		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		session.save(employee);
		session.getTransaction().commit();
		sessionfactory.close();
		session.close();
	}
}
