package com.hib.demo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HQLMixDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
		// Getting Details of emp tbales whos name is Roman
		
		Query<Emp> e = session.createQuery(" from com.hib.demo.Emp where name = :n ");
		e.setParameter("n", "Roman");
		System.out.println("Detail: "+e.getResultList().get(0));
		
		// To get max id
		
		Query<Emp> e1 = session.createQuery("select max(id) as m from com.hib.demo.Emp");
		System.out.println("Max id: "+e1.getSingleResult());
		
		// Nested Query
		
		/*Query<Emp> e2 = session.createQuery("from com.hib.demo.Emp where" + "name=(select max(id)from com.hib.demo.Emp)");
		Emp i = e2.getResultList().get(0);
		System.out.println("ID: "+i);
		*/
		
		//
		Query<Long> e3 = session.createQuery("select count(*) from com.hib.demo.Emp" );
		long cnt = e3.getSingleResult();
		System.out.println("Total Records: "+cnt);
		
		
		
		
		t.commit();
		sf.close();
		session.close();
		
		

	}

}
