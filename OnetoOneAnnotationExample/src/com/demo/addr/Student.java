package com.demo.addr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="students")

public class Student {
	
	@Id
	@Column(name="sid")
	private int studentId;
	
	@Column(name="sname",length=10)
	private String studentName;
	
	@Column(name="gpr",length=10)
	private String gpr;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getGpr() {
		return gpr;
	}

	public void setGpr(String gpr) {
		this.gpr = gpr;
	}
	
	
	
	
	

}
