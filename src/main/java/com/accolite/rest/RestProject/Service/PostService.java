package com.accolite.rest.RestProject.Service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.accolite.rest.RestProject.Model.Post;

public class PostService {
	
	List<Post> postList = new ArrayList<Post>();
	
	public PostService() {
		super();
	}
	
	public List<Post> getPosts(int userID) {
		List<Post> postList = new ArrayList<Post>();
		try {
			MyConnection conVar = MyConnection.getConnection();
			String query = "select * from post where userID="+userID;
			Statement stmt = conVar.getCon().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int id, userid;
				String data;
				id = rs.getInt("ID");
				data = rs.getString("data");
				userid = rs.getInt("userID");
				Post temp = new Post(id,userid,data);
				postList.add(temp);
			}
			conVar.getCon().close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return postList;
	}
	
	public Post getPostByID(int ID) {
		Post temp = null;
		try {
			MyConnection conVar = MyConnection.getConnection();
			String query = "select * from post where ID="+ID;
			Statement stmt = conVar.getCon().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				int id, userid;
				String data;
				id = rs.getInt("ID");
				data = rs.getString("data");
				userid = rs.getInt("userID");
				temp = new Post(id,userid,data);
			}
			conVar.getCon().close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	    return temp;
	}
	
	public void addPost(Post post, int userID) {
		try {
			MyConnection conVar = MyConnection.getConnection();
			String query = "insert into post(userID,data) values(" + userID + ",'" + post.getData() + "')";
			Statement stmt = conVar.getCon().createStatement();
			stmt.executeUpdate(query);
			conVar.getCon().close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Post updatePost(int id, Post post) {
		Post temp = getPostByID(id);
		if(temp!=null) {
			temp.setData(post.getData());
			try {
				MyConnection conVar = MyConnection.getConnection();
				String query = "update user SET data='"+temp.getData()+"' where ID="+temp.getId();
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
	
	public Post deletePost(int id) {
		Post temp = getPostByID(id);
		if(temp == null)
			return null;
		try {
			MyConnection conVar = MyConnection.getConnection();
			String query = "delete from post where ID="+id;
			Statement stmt = conVar.getCon().createStatement();
			stmt.executeUpdate(query);
			conVar.getCon().close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
}
