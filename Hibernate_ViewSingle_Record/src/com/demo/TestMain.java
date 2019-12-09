package com.demo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class TestMain {

	public static void main(String[] args) {
		
		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		
		EmpTbl emp = (EmpTbl)session.get(EmpTbl.class, 10);
		System.out.println("Your Record is: "+emp);

		session.beginTransaction().commit();
		session.close();
		sessionfactory.close();
		}
}
