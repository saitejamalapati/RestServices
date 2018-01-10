package com.accolite.rest.RestProject.Model;

public class Post {
	int id;
	int userID;
	String data;
	
	public Post(int id, int userID, String data) {
		super();
		this.id = id;
		this.userID = userID;
		this.data = data;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
}
