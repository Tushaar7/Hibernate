package com.client;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.model.Emp;

public class HqclDemo {
	public static void main(String[] args) {
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
	/*	// Get All Records From Emp POJO class
		
		Criteria criteria = session.createCriteria(Emp.class);
		
		List<Emp> list = criteria.list();
		
		for(Emp e : list){
			System.out.println(" ID: "+e.getId() + " Name: "+e.getName() + " Addr: " + e.getAddr());
		}
	*/	
	
		/*
		// Accending order
		
		Criteria criteria = session.createCriteria(Emp.class).addOrder(Order.asc("name"));
		
		List<Emp> list = criteria.list();
		
		for(Emp e : list){
			System.out.println(" ID: "+e.getId() + " Name: "+e.getName() + " Addr: " + e.getAddr());
		}
		*/
		
		/*
		// Descending order
		
			Criteria criteria = session.createCriteria(Emp.class).addOrder(Order.desc("name"));
				
			List<Emp> list = criteria.list();
				
			for(Emp e : list){
				System.out.println(" ID: "+e.getId() + " Name: "+e.getName() + " Addr: " + e.getAddr());
			}
		*/
		
/*		
		// Restriction Query
		// id is equal to 10
		
		Criteria criteria = session.createCriteria(Emp.class).add(Restrictions.eq("id", 10));
		
		List<Emp> list = criteria.list();
		
		for(Emp e : list){
			System.out.println(" ID: "+e.getId() + " Name: "+e.getName() + " Addr: " + e.getAddr());
		}
		
*/		
	/*	
		Criteria criteria = session.createCriteria(Emp.class).add(Restrictions.lt("id",10));
		List<Emp> list = criteria.list();
		for(Emp e : list){
			System.out.println(" ID: "+e.getId() + " Name: "+e.getName() + " Addr: " + e.getAddr());
		}
	*/
		
/*
		Criteria criteria = session.createCriteria(Emp.class).add(Restrictions.le("id",10));
		List<Emp> list = criteria.list();
		for(Emp e : list){
			System.out.println(" ID: "+e.getId() + " Name: "+e.getName() + " Addr: " + e.getAddr());
		}
		
*/
	/*	
		Criteria criteria = session.createCriteria(Emp.class).add(Restrictions.gt("id",10));
		List<Emp> list = criteria.list();
		for(Emp e : list){
			System.out.println(" ID: "+e.getId() + " Name: "+e.getName() + " Addr: " + e.getAddr());
		}
	*/
		
		/*
		Criteria criteria = session.createCriteria(Emp.class).add(Restrictions.ge("id",10));
		List<Emp> list = criteria.list();
		for(Emp e : list){
			System.out.println(" ID: "+e.getId() + " Name: "+e.getName() + " Addr: " + e.getAddr());
		}
		*/
		
		/*
		Criteria criteria = session.createCriteria(Emp.class).add(Restrictions.like("name", "A%"));
		
		List<Emp> list = criteria.list();
		for(Emp e : list){
			System.out.println(" ID: "+e.getId() + " Name: "+e.getName() + " Addr: " + e.getAddr());
		}
	*/
		
/*
		Criteria criteria = session.createCriteria(Emp.class).add(Restrictions.between("id",1,10));
		
		List<Emp> list = criteria.list();
		for(Emp e : list){
			System.out.println(" ID: "+e.getId() + " Name: "+e.getName() + " Addr: " + e.getAddr());
		}
		
*/
	/*
		Criteria criteria = session.createCriteria(Emp.class).add(Restrictions.isNull("id"));
		
		List<Emp> list = criteria.list();
		for(Emp e : list){
			System.out.println(" ID: "+e.getId() + " Name: "+e.getName() + " Addr: " + e.getAddr());
		}
*/	
		/*
		Criteria criteria = session.createCriteria(Emp.class).add(Restrictions.isNotNull("id"));
		
		List<Emp> list = criteria.list();
		for(Emp e : list){
			System.out.println(" ID: "+e.getId() + " Name: "+e.getName() + " Addr: " + e.getAddr());
		}
*/		
		
	/*	
		// Pagination
		Criteria criteria = session.createCriteria(Emp.class);
		criteria.setMaxResults(7);
		criteria.setFirstResult(5);

		List list = criteria.list();
		System.out.println(list);
		

		List<Emp> list = criteria.list();
		for(Emp e : list){
			System.out.println(" ID: "+e.getId() + " Name: "+e.getName() + " Addr: " + e.getAddr());
		}
		
*/		
		
		/*
		//Projection
		
		// To get total row count
		
		Criteria criteria = session.createCriteria(Emp.class);
		criteria.setProjection(Projections.rowCount());
		
		List list = criteria.list();	
		System.out.println(list);
		*/

		/*
		// To get avg of property
		
		Criteria criteria = session.createCriteria(Emp.class);
		criteria.setProjection(Projections.avg("id"));
		List list = criteria.list();	
		System.out.println(list);
*/		
	/*	
		// To get distinct count of property
		
		Criteria criteria = session.createCriteria(Emp.class);
		criteria.setProjection(Projections.countDistinct("id"));
		List list = criteria.list();	
		System.out.println(list);
		
		// To get maximum of a property. 
		
		criteria.setProjection(Projections.max("salary")); 
		
		// To get minimum of a property.
		
		criteria.setProjection(Projections.min("salary")); 
		
		// To get sum of a property. 
		
		criteria.setProjection(Projections.sum("salary"));

*/		
		
		
		
		
		
		 
				
		
		
		
		
		
		
		
		
		t.commit();
		session.close();
		sf.close();
		
	}

}
