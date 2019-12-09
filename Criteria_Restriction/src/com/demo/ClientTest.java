package com.demo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class ClientTest {

	public static void main(String[] args) {
	
		SessionFactory sessionfactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Student.class); // To view All records

		List<Student> list1 = criteria.list();
		
		System.out.println("All Records From Database are: ");
		
		for(Student l : list1)
		System.out.println(l);
		
		System.out.println("---------------------------------------------------");
		
		
		criteria.add(Restrictions.gt("marks", 66f)); // To get records having marks more than 66
		
		List<Student> list2 = criteria.list();
		
		System.out.println("Marks Greater Than 66 are: ");
		
		for(Student l : list2)
		System.out.println(l);
		
		System.out.println("-----------------------------------------------------");
		
		criteria.add(Restrictions.like("name", "A%")); // To get records having name starting with A

		List<Student> list3 = criteria.list();
		
		System.out.println("Name Starting with A are: ");
		
		for(Student l : list3)
		System.out.println(l);
		
		System.out.println("-----------------------------------------------------");
		
		criteria.add(Restrictions.isNotNull("marks"));	// To check given property is not null
		
		List<Student> list4 = criteria.list();
		
		System.out.println("Not Null Properties is:");

		for(Student l : list4)
		System.out.println(l);
		
		System.out.println("-----------------------------------------------------");
		
		//Pagination using Criteria
		
		Criteria criteria1 = session.createCriteria(Student.class);
		
		criteria1.setFirstResult(5);
		criteria1.setMaxResults(6);
		
		List<Student> list5 = criteria1.list();
		
		System.out.println("Pagination using Criteria: ");
		
		for(Student l: list5)
		System.out.println(l);
		
		System.out.println("-----------------------------------------------------");
		
		//Sorting Results
		
		criteria1.addOrder(Order.desc("id"));
		
		List<Student> list6 = criteria1.list();
		
		System.out.println("Result in decending order: ");
		
		for(Student l: list6)
		System.out.println(l);
		
		System.out.println("-----------------------------------------------------");
		
		session.getTransaction().commit();
		session.close();
		sessionfactory.close();
	}
}
