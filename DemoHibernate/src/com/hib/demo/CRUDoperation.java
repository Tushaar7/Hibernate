package com.hib.demo;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CRUDoperation {
	public static void main(String[] args) {
		
		
		System.out.println("1. Inertion: ");
		System.out.println("2. Display: ");
		System.out.println("3. Update: ");
		System.out.println("4. Deletion: ");
		
		System.out.println("Enter Your Choice: ");
		Scanner sc = new Scanner(System.in);
		int ch=sc.nextInt();
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction t = session.beginTransaction();
		
		Hydra h = new Hydra(); 
		
		switch (ch) {
		case 1:
			String n,c;
			System.out.println("Enter Name: ");
			n=sc.next();
			h.setName(n);
			
			System.out.println("Enter City: ");
			c=sc.next();
			h.setCity(c);
			
			session.save(h);
			t.commit();
			System.out.println("Insertion Done");
			sf.close();
			session.close();

			break;
			

		default:
			break;
		}
		
	}

}
