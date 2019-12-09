package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="emp111")
public class Employee {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name="did")
	private Deptarment dept;
	
	
	
}
