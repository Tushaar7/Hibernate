package com.client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class test {
	public static void main(String[] args) {
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction t= session.beginTransaction();
		
		Query query = session.createQuery("insert into emp(id,name,addr)" + "select id,name,addr from empdemo");
		
		int result = query.executeUpdate();
		
		System.out.println("Affected rows: "+result);
		
		
		t.commit();
		session.close();
		sf.close();
	}

}
