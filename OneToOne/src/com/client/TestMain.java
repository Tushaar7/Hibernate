package com.client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.model.Address;
import com.model.Student;

public class TestMain {

	
	public static void main(String[] args) {
	
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();
	session.beginTransaction();
	
	Student s = new Student();
	s.setSid(101);
	s.setName("Alex");
	s.setGrp("mpc");
	
	Address a = new Address();
	a.setId(1);
	a.setPlace("USA");
	a.setStudent(s);
	
	session.save(s);
	session.save(a);

	session.getTransaction().commit();
	session.close();
	sessionFactory.close();
}
}

	
	
	/*
	public  static  void main(String args[])
	{
 
		Configuration cfg=new Configuration();
	    cfg.configure("hibernate.cfg.xml");	        
 
	    SessionFactory factory = cfg.buildSessionFactory();
	    Session session = factory.openSession();
 
		Student  s = new Student();
		s.setStudentId(101);
		s.setStudentName("James");
		s.setGrp("mpc");
 
		Address  ad = new Address();
		ad.setAddressId(1);
		ad.setPlace("Carolina");
 
		ad.setParent(s);
		Transaction  tx = session.beginTransaction();
		session.save(ad);
		tx.commit();
		session.close();
		System.out.println("One to One with annotations is done..!!!!");
		factory.close();
	}
	
}	
*/	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
