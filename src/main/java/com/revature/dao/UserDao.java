package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import com.revature.models.User;
import com.revature.util.connectionUtility;

public class UserDao implements IUserDao
{

	public boolean insert(User u)
	{
		// TODO Auto-generated method stub
		//Connection conn = connectionUtility.getConnection();
		
		String sql = "INSERT INTO users (user_id, username, password, role) values (?, ?, ?, ?) RETURNING users.user_id";
		
		try (Connection conn = connectionUtility.getConnection())
		{
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			
			stmt.setInt(1, u.getId());
			
			stmt.setString(2, u.getUsername());
			
			stmt.setString(3, u.getPassword());
			
			stmt.setObject(4, u.getRole(),Types.OTHER);
			
			//ResultSet rs;
			
			if((stmt.executeQuery()) != null)
			{
				return true;
			}
			
		}catch (SQLException e) 
		{
			
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		
		return false;
	}

	public User findById(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public User findByUsername(String username)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findAll()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public int getCount()
	{
		
		int temp = -1;
		
		String sql = "SELECT count(user_id) FROM users";
		
		try(Connection conn = connectionUtility.getConnection())
		{
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			rs.next();
			temp = rs.getInt(1);
			
		} catch (SQLException e)
		{
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		return temp;
	}

	public boolean update(User u)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(int id)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
