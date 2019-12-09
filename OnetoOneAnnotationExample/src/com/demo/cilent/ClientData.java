package com.demo.cilent;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.demo.addr.Address;
import com.demo.addr.Student;

public class ClientData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
		Student s = new Student();
		s.setStudentId(101);
		s.setStudentName("Roman");
		s.setGpr("USA");
		
		Address ad = new Address();
		ad.setAddressId(1);
		ad.setPlace("UK");
		ad.setParent(s);
		
		session.save(ad);
		
		t.commit();
		session.close();
		System.out.println("One to One with annotation is done...");
		sf.close();
		
	}

}
