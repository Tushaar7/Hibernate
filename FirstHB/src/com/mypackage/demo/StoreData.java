package com.mypackage.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StoreData {
	
	public static void main(String[] args) {
		
		//create configuration object
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");	//populates the data of the configuration file
		
		//create session factory object
		SessionFactory factory = cfg.buildSessionFactory();
		
		//create session object
		Session session = factory.openSession();
		
		//create transaction object
		Transaction t = session.beginTransaction();
		
		Employee e1 = new Employee();
		e1.setId(115);
		e1.setFirstName("Adam");
		e1.setLastName("Gill");
		
		session.persist(e1); //parsisting the object
		
		t.commit();
		session.close();
		
		System.out.println("Successfully Saved...");
		
	}

}
