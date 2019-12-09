package com.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="stud100")
public class Student {

	@Id
	@Column(name="student_id")
	@GeneratedValue
	private int id;
	
	@Column(name="student_name")
	private String name;
	
	//@Column(name="student_address")
	@OneToOne(cascade=CascadeType.ALL)
	private Address studentaddress;

	public Student(String name, Address studentaddress) {
		super();
		this.name = name;
		this.studentaddress = studentaddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getStudentaddress() {
		return studentaddress;
	}

	public void setStudentaddress(Address studentaddress) {
		this.studentaddress = studentaddress;
	}
}
