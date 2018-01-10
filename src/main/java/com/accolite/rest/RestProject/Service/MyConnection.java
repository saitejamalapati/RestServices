package com.accolite.rest.RestProject.Service;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
	
	Connection con;
	private static volatile MyConnection conVar;
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
	private MyConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accobook","root","root");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static MyConnection getConnection() {
		if(conVar == null) {
			synchronized (MyConnection.class) {
				if(conVar == null) {
					conVar = new MyConnection();
				}
			}
		}
		try {
			if(conVar.con.isClosed()) {
				synchronized (MyConnection.class) {
					if(conVar.con.isClosed()) {
						conVar.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accobook","root","root");
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conVar;
	}
}

