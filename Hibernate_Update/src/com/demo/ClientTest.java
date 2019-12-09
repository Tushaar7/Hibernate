package com.demo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class ClientTest {

	public static void main(String[] args) {
		
		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		EmployeeDetails employee = (EmployeeDetails)session.get(EmployeeDetails.class, 10);
		employee.setAddress("India");
		session.update(employee);
		session.getTransaction().commit();
		session.close();
		sessionfactory.close();
	}
}
