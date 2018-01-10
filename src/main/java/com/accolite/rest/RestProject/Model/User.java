package com.accolite.rest.RestProject.Model;

public class User {
	int id;
	String userName;
	int age;
	String city;
	
	public User(int id, String userName, int age, String city) {
		super();
		this.id = id;
		this.userName = userName;
		this.age = age;
		this.city = city;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
}

