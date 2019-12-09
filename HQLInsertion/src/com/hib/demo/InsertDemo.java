package com.hib.demo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class InsertDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
		/*String hql ="insert into emp (id,name,addr)"
		        + " select s.id, s.name, s.addr from empdemo s";
		
*/
		String hql = "insert into emp (id,name,addr)"
		        + " select id, name, addr from empdemo";
		
		Query query = session.createQuery(hql);

		int rowsAffected = query.executeUpdate();
		if (rowsAffected > 0) {
		    System.out.println(rowsAffected + "(s) were inserted");
		}
		
		t.commit();
		session.close();
		sf.close();

	}
}
