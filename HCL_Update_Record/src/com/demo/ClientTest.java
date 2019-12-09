package com.demo;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class ClientTest {

	public static void main(String[] args) {
		
		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		
		//Update a Specific field
		Query query = session.createQuery("update Student set city =:c where id=:i");
		query.setParameter("c", "ENG");
		query.setParameter("i", 2);
		query.executeUpdate();
		
		session.getTransaction().commit();
		session.close();
		sessionfactory.close();
	}
}
