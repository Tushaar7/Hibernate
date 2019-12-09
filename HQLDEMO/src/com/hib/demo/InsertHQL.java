package com.hib.demo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class InsertHQL {
	public static void main(String[] args) {
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
		String hql = "insert into emp (id,name,addr)"
		        + " select s.id, s.name, s.addr from empdemo s";
		
		Query query = session.createQuery(hql);

		int rowsAffected = query.executeUpdate();
		if (rowsAffected > 0) {
		    System.out.println(rowsAffected + "(s) were inserted");
		}
		 	
//		String hql = "select name, addr from emp where id = 1";
	
/*		List<Emp> list = session.createQuery("from com.hib.demo.Emp").getResultList();
		//List<Emp> list = session.createQuery("from com.hib.demo.Emp").list();
		
		for(Emp e : list){
			System.out.println(e);
		}
*/				
		/*
		Query query = session.createQuery("insert into emp(name,addr)" + "select name,addr from empdemo");
		int result = query.executeUpdate();
		System.out.println("Records Inserted: "+result);
		*/
		
		/*Query query = session.createQuery("insert into emp(name,addr) select e.name,e.addr from empdemo e where e.id=? ");
	
		query.setParameter(0,3);
		
		int result = query.executeUpdate();
		
		System.out.println("Records Inserted: "+result);
		*/
		
		t.commit();
		session.close();
		sf.close();
	}
}
