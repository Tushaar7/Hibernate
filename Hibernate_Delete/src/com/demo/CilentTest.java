package com.demo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class CilentTest {

	public static void main(String[] args) {
		
		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		EmpTbl employee = (EmpTbl)session.get(EmpTbl.class, 6);
		session.delete(employee);
		session.getTransaction().commit();
		session.close();
		sessionfactory.close();
	}
}
