package com.accolite.rest.RestProject.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public List<User> getUsers() throws ClassNotFoundException, SQLException {
		List<User> userList = new ArrayList<User>();
		try {
			Connection con = MyConnection.getConnection();
			String query = "select * from user";
			Statement stmt = con.createStatement();
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
		}catch(Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	public User getUserByID(int ID) throws ClassNotFoundException, SQLException {
		User temp = null;
		try {
			Connection con = MyConnection.getConnection();
			String query = "select * from user where ID="+ID;
			Statement stmt = con.createStatement();
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
		}catch(Exception e) {
			e.printStackTrace();
		}
	    return temp;
	}
	
	public User addUser(User user) throws ClassNotFoundException, SQLException {
		try {
			Connection con = MyConnection.getConnection();
			String query = "insert into user(username,age,city) values('" + user.getUserName() + "'," + user.getAge() + ",'" + user.getCity() + "')";
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			query = "select * from user where username='"+user.getUserName()+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				user.setId(rs.getInt("ID"));
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	    return user;
	}
	
	public User updateUser(int id, User user) throws ClassNotFoundException, SQLException {
		User temp = getUserByID(id);
		if(temp!=null) {
			temp.setCity(user.getCity());
			temp.setAge(user.getAge());
			try {
				Connection con = MyConnection.getConnection();
				String query = "update user SET age="+temp.getAge()+",city='"+temp.getCity()+"' where ID="+temp.getId();
				Statement stmt = con.createStatement();
				stmt.executeUpdate(query);
			}catch(Exception e) {
				e.printStackTrace();
			}
			return temp;
		}
		return null;
	}
	
	public User deleteUser(int id) throws ClassNotFoundException, SQLException {
		User temp = getUserByID(id);
		if(temp == null)
			return null;
		try {
			Connection con = MyConnection.getConnection();
			String query = "delete from user where ID="+id;
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
}
