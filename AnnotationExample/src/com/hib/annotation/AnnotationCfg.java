package com.hib.annotation;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.hib.demo.EmployeeDemo;

public class AnnotationCfg {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Session session = new AnnotationConfiguration().configure().buildSessionFactory().openSession();
		
		Transaction t = session.beginTransaction();
		
		EmployeeDemo e1 = new EmployeeDemo();
		e1.setId(1);
		e1.setFirstName("Fread");
		e1.setLastName("John");
		
		EmployeeDemo e2 = new EmployeeDemo();
		e2.setId(2);
		e2.setFirstName("Adam");
		e2.setLastName("Gill");
		
		session.persist(e1);
		session.persist(e2);
		
		t.commit();
		session.close();
		System.out.println("Successfully saved...");
		
		
		
		
		

	}

}
