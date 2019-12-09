package com.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EmployeeInfo e = new EmployeeInfo();
		e.setId(14);
		e.setName("Maxx");
		e.setEmail("Max@live.com");
		e.setAddress("RSA");
		e.setTelephone("0987654321");
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		
		session.close();
		sf.close();
	}
}
