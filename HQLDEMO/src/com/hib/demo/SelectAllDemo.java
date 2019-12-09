package com.hib.demo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SelectAllDemo {
	public static void main(String[] args) {
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
		List<Emp> list = session.createQuery("from com.hib.demo.Emp").getResultList();
		//List<Emp> list = session.createQuery("from com.hib.demo.Emp").list();
		
		for(Emp e : list){
			System.out.println(e);
		}
		
		System.out.println("Values from Second Table...");
		
		List<Empdemo> list2 = session.createQuery("from com.hib.demo.Empdemo").list();
		for(Empdemo e1 : list2){
			System.out.println(e1);
		}

		
		/*String hql = "from com.hib.demo";
		Query query = session.createQuery(hql);
		List results = query.list();

		System.out.println(results);
		
*/
		
		t.commit();
		session.close();
		sf.close();
		
	}

}
