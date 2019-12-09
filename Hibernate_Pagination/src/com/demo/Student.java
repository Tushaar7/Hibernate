package com.demo;

public class Student implements java.io.Serializable {

	private Integer id;
	private String name;
	private String city;

	public Student() {
	}

	public Student(String name, String city) {
		this.name = name;
		this.city = city;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", city=" + city + "]";
	}
}
