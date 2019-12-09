package com.dao;

import java.util.List;

import com.model.Deptarment;
import com.model.Employee;

public interface EmployeeDao {

	public void addCategory(Deptarment d);
	
	public List<Deptarment> getAllRecords();
	
	public List<Employee> AllRecordsAccessory();
}
