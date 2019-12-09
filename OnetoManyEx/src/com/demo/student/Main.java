package com.demo.student;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction t = null;
		
		try{
			t = session.beginTransaction();
			
			Set<Phone> phoneNumbers = new HashSet<Phone>();
			phoneNumbers.add(new Phone("house","1234567890"));
			phoneNumbers.add(new Phone("mobile","0987654321"));
			
			Student s = new Student("Eswar",phoneNumbers);
			session.save(s);
			t.commit();
			
		}catch(HibernateException e){
			t.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
}
