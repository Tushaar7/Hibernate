package com.demo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class ClienTest {

	public static void main(String[] args) {
		
		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		
		// To get list of all records from database
		
		Query query = session.createQuery("from Student");
		List<Student> list = query.list();
		
		System.out.println("All records from database: ");
		
		for(Student s : list){
			System.out.println(s);
		}
		
		System.out.println();
		
		System.out.println("Number of records is: "+list.size()); //Give total number of records in database
		
		System.out.println();
		
		//To Get Specific (Single) result from database
		
		Query query1 = session.createQuery("from Student where id = 1");
		Student stud = (Student)query1.uniqueResult();
		System.out.println("Detail of Id 1 is: "+stud);
		
		session.getTransaction().commit();
		session.close();
		sessionfactory.close();
	}
}
