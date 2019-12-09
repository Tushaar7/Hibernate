package com.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class testClient {

	public static void main(String[] args) {
		
		Employee employee = new Employee();
		employee.setId(14);
		employee.setEmail("Roman@live.com");
		employee.setName("Roman");
		employee.setAddress("America");
		employee.setTelephone("1234567890");
		
		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.save(employee);
		session.getTransaction().commit();
		
		sessionfactory.close();
		session.close();

	}

}
