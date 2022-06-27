package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionUtility
{
	private static Connection conn = null;
	
	private connectionUtility() { }
	
	public static Connection getConnection()
	{
		
		try
		{
			if (conn != null && !conn.isClosed()) {
				//System.out.println("Using a previously made connection");
				return conn;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		
		String url = System.getenv("DB_URL");
		String username = System.getenv("DB_USERNAME");
		String password = System.getenv("DB_PASSWORD");
		String schema = System.getenv("DB_SCHEMA");
		
		try {
			conn = DriverManager.getConnection(url, username, password);
			//System.out.println("Established Connection to Database!");
			//conn.setSchema("Project0");
			System.out.println(conn.getSchema());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//System.out.println("Cannot establish connection");
			e.printStackTrace();
		}
		
		
		
		return conn;
	}
	
}
	
	
