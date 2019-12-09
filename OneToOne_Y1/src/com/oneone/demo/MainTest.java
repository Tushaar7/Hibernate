package com.oneone.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Student student = new Student();
		student.setStudent_name("Cena");
		
		StudentDetail studentdetail = new StudentDetail();
		studentdetail.setStudent_mobile_number(1987654321);
		studentdetail.setStudent(student);
		
		SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		session.save(studentdetail);
		
		session.getTransaction().commit();
		session.close();
		sf.close();
		

	}

}
