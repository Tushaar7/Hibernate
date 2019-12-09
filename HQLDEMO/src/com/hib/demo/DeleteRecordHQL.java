package com.hib.demo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DeleteRecordHQL {
	public static void main(String[] args) {
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
		Query query = session.createQuery("delete com.hib.demo.Emp where id = :d");
		query.setParameter("d",6);
		query.executeUpdate();
		
		System.out.println("Deletion Done");
		
		t.commit();
		session.close();
		sf.close();
		
	}

}
