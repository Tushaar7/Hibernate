package com.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="adress300")
public class Address {
	
	@Id
	@GeneratedValue
	@Column(name="address_id")
	private int id;
	
	@Column(name="address_place",length=10)
	private String place;
	
	@OneToOne(targetEntity=Student.class, cascade=CascadeType.ALL)
	@JoinColumn(name="stud_id",referencedColumnName="student_id")
	private Student student;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
















/*
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
 
@Entity
@Table(name="Address1")
public class Address {
	@Id
	@Column(name="addrid")
	private  int addressId;
 
	@Column(name="place",length=10)
	private String place;
 
	@OneToOne(targetEntity=Student.class,cascade=CascadeType.ALL)
	@JoinColumn(name="stu_id",referencedColumnName="sid")
	private  Student  parent;
 
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Student getParent() {
		return parent;
	}
	public void setParent(Student parent) {
		this.parent = parent;
	}
 
}


*/








