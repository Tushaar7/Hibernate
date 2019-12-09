package com.demo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;

public class ClientTest {

	public static void main(String[] args) {
		
		SessionFactory sessiofactory = new Configuration().configure().buildSessionFactory();
		Session session = sessiofactory.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Student.class);
		
		criteria.setProjection(Projections.rowCount());	// To get total row count
		List list1 = criteria.list();
		System.out.println("Total row is: "+list1);
		
		System.out.println("-----------------------------------------------------");
		
		criteria.setProjection(Projections.avg("marks"));	//To get average of property
		List list2 = criteria.list();
		System.out.println("Avg is: "+list2);
		
		System.out.println("-----------------------------------------------------");
		
		criteria.setProjection(Projections.countDistinct("name"));	//To get distinct count of property
		List list3 = criteria.list();
		System.out.println("Distinct count: "+list3);
		
		System.out.println("-----------------------------------------------------");
		
		criteria.setProjection(Projections.max("marks"));	//To get maximum of property
		List list4 = criteria.list();
		System.out.println("Maximun marks: "+list4);
		
		System.out.println("-----------------------------------------------------");
		
		criteria.setProjection(Projections.min("marks"));	//To get minimum of property
		List list5 = criteria.list();
		System.out.println("minimun marks: "+list5);
		
		System.out.println("-----------------------------------------------------");

		criteria.setProjection(Projections.sum("marks"));	//To get sum of property
		List list6 = criteria.list();
		System.out.println("Sum of marks is: "+list6);
		
		System.out.println("-----------------------------------------------------");

		session.getTransaction().commit();
		session.close();
		sessiofactory.close();
	}
}
