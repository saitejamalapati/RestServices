package com.accolite.rest.RestProject.Service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.accolite.rest.RestProject.Model.User;

public class UserService {
	
	public UserService() {
		super();
	}
	
	public String testfunc() {
		return "This is mock drill\n";
	}
	
	public List<User> getUsers() {
		List<User> userList = new ArrayList<User>();
		try {
			MyConnection conVar = MyConnection.getConnection();
			String query = "select * from user";
			Statement stmt = conVar.getCon().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int id, age;
				String username,city;
				id = rs.getInt("ID");
				username = rs.getString("username");
				age = rs.getInt("age");
				city = rs.getString("city");
				User temp = new User(id,username,age,city);
				userList.add(temp);
			}
			conVar.getCon().close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	public User getUserByID(int ID) {
		User temp = null;
		try {
			MyConnection conVar = MyConnection.getConnection();
			String query = "select * from user where ID="+ID;
			Statement stmt = conVar.getCon().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				int id, age;
				String username,city;
				id = rs.getInt("ID");
				username = rs.getString("username");
				age = rs.getInt("age");
				city = rs.getString("city");
				temp = new User(id,username,age,city);
			}
			conVar.getCon().close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	    return temp;
	}
	
	public User addUser(User user) {
		try {
			MyConnection conVar = MyConnection.getConnection();
			String query = "insert into user(username,age,city) values('" + user.getUserName() + "'," + user.getAge() + ",'" + user.getCity() + "')";
			Statement stmt = conVar.getCon().createStatement();
			stmt.executeUpdate(query);
			query = "select * from user where username='"+user.getUserName()+"'";
			ResultSet rs = stmt.executeQuery(query);
			user.setId(rs.getInt("ID"));
			conVar.getCon().close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	    return user;
	}
	
	public User updateUser(int id, User user) {
		User temp = getUserByID(id);
		if(temp!=null) {
			temp.setCity(user.getCity());
			temp.setAge(user.getAge());
			try {
				MyConnection conVar = MyConnection.getConnection();
				String query = "update user SET age="+temp.getAge()+",city='"+temp.getCity()+"' where ID="+temp.getId();
				Statement stmt = conVar.getCon().createStatement();
				stmt.executeUpdate(query);
				conVar.getCon().close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return temp;
		}
		return null;
	}
	
	public User deleteUser(int id) {
		User temp = getUserByID(id);
		if(temp == null)
			return null;
		try {
			MyConnection conVar = MyConnection.getConnection();
			String query = "delete from user where ID="+id;
			Statement stmt = conVar.getCon().createStatement();
			stmt.executeUpdate(query);
			conVar.getCon().close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
}
