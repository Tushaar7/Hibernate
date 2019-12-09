package com.hib.demo;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StoreData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction t = session.beginTransaction();
		
		Question question1 = new Question();
		question1.setQname("What is Java?");
		
		
		Question question2 = new Question();
		question2.setQname("What is Servlet?");
		
		
		Answer ans1 = new Answer();
		ans1.setAnswername("Java is Prog lang");
		ans1.setPostedBy("Jacks");
		ans1.setQuestion(question1);
		
		Answer ans2 = new Answer();
		ans2.setAnswername("Java is Platform");
		ans2.setPostedBy("MAC");
		ans2.setQuestion(question1);
		
		Answer ans3 = new Answer();
		ans3.setAnswername("Servlet is an Interface");
		ans3.setPostedBy("Rock");
		ans3.setQuestion(question2);
		
		Answer ans4 = new Answer();
		ans4.setAnswername("Servlet is an API");
		ans4.setPostedBy("John");
		ans4.setQuestion(question2);
		
		ArrayList<Answer> list1 = new ArrayList<Answer>();
		list1.add(ans1);
		list1.add(ans2);
		question1.setAnswers(list1);
		
		ArrayList<Answer> list2 = new ArrayList<Answer>();
		list1.add(ans3);
		list1.add(ans4);
		question2.setAnswers(list2);
		
		
		session.persist(question1);
		session.persist(question2);
		
		t.commit();
		System.out.println("OneToMany Done...");
		session.close();
		System.out.println("Success");
	}
}
