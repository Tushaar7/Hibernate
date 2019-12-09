package com.demo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ClientTest {

	public static void main(String[] args) {

		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		
		/*Query query = session.getNamedQuery("Student.byid");
		query.setInteger(0, 5);
		*/
		
		Query query = session.getNamedQuery("Student.byName");
		query.setString(0, "Fread");

		List<Student> list = query.list();
		
		for(Student s : list)
			System.out.println(s.getName());
		
		session.getTransaction().commit();
		sessionfactory.close();
		session.close();
	}
}
