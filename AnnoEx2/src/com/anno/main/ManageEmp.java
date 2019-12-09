package com.anno.main;
import com.hib.demo.*;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.hib.demo.Employee;

public class ManageEmp {
	
	private static SessionFactory factory;
	
	public static void main(String[] args) {
		
		try{
			factory = new AnnotationConfiguration().configure().addAnnotatedClass(com.hib.demo.Employee.class).buildSessionFactory();	
		
		}catch(Throwable ex){
			System.err.println("Failed to create sessionFactory object" + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		ManageEmp ME = new ManageEmp();
		
		// Add records in db
		Integer empID1 = ME.addEmployee("Rock","Johnson",100);
		Integer empID2 = ME.addEmployee("John","Paul",200);
		Integer empID3 = ME.addEmployee("Ricky","Ponting",300);
		
		//List of all emp
		ME.listEmployees();
		
		//Update emp record
		ME.updateEmployee(empID1, 8000);
		
		//Delete emp record
		ME.deleteEmployee(empID2);
		
		// list of emp
		ME.listEmployees();
	
	}
	
	
	// Method to CREATE emp100 db
	
	public Integer addEmployee(String fname, String lname, int sal){
		
		Session session = factory.openSession();
		
		Transaction t = null;
		
		Integer employeeID = null;
		
		try{
			
			t = session.beginTransaction();
			
			Employee employee = new Employee();
			employee.setFirstName(fname);
			employee.setLastName(lname);
			employee.setSalary(sal);
			
			employeeID = (Integer)session.save(employee);
			t.commit();
			
		}catch(HibernateException e){
			if(t!=null) 
			t.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return employeeID;
	}
	
	// Method to Read all the records
	
	public void listEmployees(){
		Session session = factory.openSession();
		Transaction t = null;
		
		try{
			t = session.beginTransaction();
			List employees = session.createQuery("from Employee").list();
			for(Iterator iterator = employees.iterator();iterator.hasNext();){
				
				Employee employee = (Employee)iterator.next();
				
				System.out.println("First Name: " + employee.getFirstName() );
				System.out.println("Last Name: "+employee.getLastName());
				System.out.println("Salary: "+employee.getSalary());
				
			}
			t.commit();
		}catch (HibernateException e) {
				// TODO: handle exception
				if(t!=null)
					t.rollback();
					e.printStackTrace();				
			}finally{
				session.close();
			}
	}
	
	
	
	// Method to Update salary for an employee
	
	public void updateEmployee(Integer EmployeeID, int salary){
		
		Session session = factory.openSession();
		Transaction t = null;
		try{
			t = session.beginTransaction();
			Employee employee = (Employee)session.get(Employee.class,EmployeeID);
			employee.setSalary(salary);
			session.update(employee);
			t.commit();
		}catch(HibernateException e){
			if(t != null)
				t.rollback();
				e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	// Method to delete an employee from records
	
	public void deleteEmployee(Integer EmployeeID){
		Session session = factory.openSession();
		Transaction t = null;
		
		try{
			t=session.beginTransaction();
			Employee employee = (Employee)session.get(Employee.class, EmployeeID);
			session.delete(employee);
			t.commit();
		}catch(HibernateException e){
			if(t!=null)
				t.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
}
