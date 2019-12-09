package com.hib.demo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UdateHQL {
	public static void main(String[] args) {
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
		Query query = session.createQuery("update com.hib.demo.Emp set name = :m where name = :n");
		
		query.setParameter("m", "Roman");
		query.setParameter("n", "Gill");
		
		query.executeUpdate();
		
		t.commit();
		session.close();
		sf.close();
		
	}
}
