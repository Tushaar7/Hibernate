package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dao.EmployeeDao;
import com.model.Deptarment;
import com.model.Employee;

public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao empoyeedao;

	@Transactional
	public void addCategory(Deptarment d) {
		empoyeedao.addCategory(d);
		
	}

	@Transactional
	public List<Deptarment> getAllRecords() {
		
		return empoyeedao.getAllRecords();
	}

	@Transactional
	public List<Employee> AllRecordsAccessory() {
		
		return empoyeedao.AllRecordsAccessory();
	}
	
}
