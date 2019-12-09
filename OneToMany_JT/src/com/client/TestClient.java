package com.client;

import java.util.ArrayList;

import oracle.net.aso.a;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.Answer;
import com.demo.Question;

public class TestClient {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Question q1 = new Question();
		q1.setQname("What is Java");
		
		Question q2 = new Question();
		q2.setQname("What is Servlet");
		
		Answer a1 = new Answer();
		a1.setAnswername("Prog lang");
		a1.setPostedBy("ABC");
		a1.setQuestion(q1);
		
		Answer a2 = new Answer();
		a2.setAnswername("Pltform");
		a2.setPostedBy("PQR");
		a2.setQuestion(q1);
		
		Answer a3 = new Answer();
		a3.setAnswername("Interface");
		a3.setPostedBy("MNO");
		a3.setQuestion(q2);
		
		Answer a4 = new Answer();
		a4.setAnswername("API");
		a4.setPostedBy("XYZ");
		a4.setQuestion(q2);
		
		ArrayList<Answer> list1 = new ArrayList<Answer>();
		list1.add(a1);
		list1.add(a2);
		q1.setAnswer(list1);
		
		ArrayList<Answer> list2 = new ArrayList<Answer>();
		list2.add(a3);
		list2.add(a4);
		q2.setAnswer(list2);
		
		session.persist(q1);
		session.persist(q2);
		
	//	System.out.println(a1.getPostedBy());
		
		session.getTransaction().commit();
		System.out.println("Done...");
		session.close();
		sessionFactory.close();
	}
}
