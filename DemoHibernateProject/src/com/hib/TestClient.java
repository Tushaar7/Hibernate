package com.hib;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestClient {
	
	public static void main(String[] args) {
		
		EmployeeDetails employee = new EmployeeDetails();
		employee.setId(14);
		employee.setEmail("Ajax@live.com:");
		employee.setAddress("America");
		employee.setTelephone("0987654321");
		
		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.save(employee);
		session.getTransaction().commit();
		
		session.close();
		sessionfactory.close();
		
	}

}
