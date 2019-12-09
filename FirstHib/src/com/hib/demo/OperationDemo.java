package com.hib.demo;

import java.util.List;
import java.util.Scanner;

import javax.enterprise.inject.Default;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class OperationDemo {
	public static void main(String[] args) {
		
		int ch = 0;
		
		while(ch!=5){
		System.out.println("Your Choice is: ");
		System.out.println("1. Insert:");
		System.out.println("2. Dispay:");
		System.out.println("3. Update:");
		System.out.println("4. Delete:");
		System.out.println("5. Exit:");

		System.out.println("Enter Your Choice: ");
		
		Scanner sc = new Scanner(System.in);
		ch = sc.nextInt();
						
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction t = session.beginTransaction();
		
		Emp e = new Emp();
		
		switch(ch){
		case 1:
			String n,a; 
			System.out.println("Enter name: ");
			n = sc.next();
			e.setName(n);
			
			System.out.println("Enter Addr: ");
			a = sc.next();
			e.setAddr(a);
			
			session.save(e);

			t.commit();
			session.close();
			sf.close();
			
			System.out.println("Insertion Done...");
			break;
								
		case 2:
			Criteria criteria=session.createCriteria(Emp.class);
			List<Emp> ee = criteria.list();
			
			for(Emp ee1:ee){
				System.out.println("ID: "+ee1.getId());
				System.out.println("Name: "+ee1.getName());
				System.out.println("Addr: "+ee1.getAddr());
			}
			break;
			
		case 3:
			System.out.println("Enter Id for Updation...");
			int id = sc.nextInt();
			Emp e2 = (Emp) session.load(Emp.class, id);
			
			System.out.println("Enter name: ");
			String nm = sc.next();
			e2.setName(nm);
			
			System.out.println("Enter Addr: ");
			String ad = sc.next();
			e2.setAddr(ad);
			
			session.update(e2);

			System.out.println("Records: "+session);
			
			t.commit();
			session.close();
			sf.close();


			System.out.println("Record updated");
			break;			
					
		case 4:
			System.out.println("Delete the data...");
			System.out.println("Enter the id: ");
			int del = sc.nextInt();
			Emp e3 = (Emp) session.load(Emp.class, del);
			session.delete(e3);			
			
			System.out.println("Records: "+session);
			
			t.commit();
			session.close();
			sf.close();
			
			System.out.println("Data Deleted...");			
			break;	
			
		case 5:
			System.out.println("Operation Done...");
			session.close();
			break;

			default:
			System.out.println("Invalid Input...");
			}
		}
	}
}
