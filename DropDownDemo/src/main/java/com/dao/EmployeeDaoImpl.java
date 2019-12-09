package com.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.model.Deptarment;
import com.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	SessionFactory hibernateSessionFactory;
	
	public void addCategory(Deptarment d) {
		hibernateSessionFactory.getCurrentSession().save(d);
		
	}

	public List<Deptarment> getAllRecords() {
		
		return hibernateSessionFactory.openSession()
				.createQuery("from Deptarment").list();
	}

	public List<Employee> AllRecordsAccessory() {
		
		return hibernateSessionFactory.openSession()
				.createQuery("from Employee").list();
	}

}
