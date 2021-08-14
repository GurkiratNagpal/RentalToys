package com.telstra.RentalToys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertProduct {
	
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/product";
		String user = "gurkirat";
		String pass = "gurkirat";
		String insCom = "INSERT INTO prod VALUES(2, 'Pen', 10)";
		int i=0;
		Connection cn =null;
		Statement st = null;
		try {
			cn = DriverManager.getConnection(url, user, pass);
			st = cn.createStatement();
			i=st.executeUpdate(insCom);
			
			System.out.println(i);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
