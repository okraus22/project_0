package com.revature.service;

import java.security.PublicKey;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.revature.dao.IUserDao;
import com.revature.dao.UserDao;
import com.revature.models.Role;
import com.revature.models.User;

public class UserService
{
	public static IUserDao uDao = new UserDao();
	
	static Logger logger = Logger.getLogger(UserService.class);
	
	public static User register(String username, String password)
	{
		
		 User temp = new User(username,password);
		
		
		if(uDao.insert(temp))
		{
			System.out.println("Succesfully Registered New User\nYour User ID is: " + temp.getId());
			logger.info("succesful registration");
			return temp;
		}
		System.out.println("User Registration Failed");
		logger.info("failed registration");
		throw new RuntimeException();
	}
	
	public static User register(String username, String password, Role role)
	{
		
		User temp = new User(username,password, role);
		
		
		if(uDao.insert(temp))
		{
			System.out.println("Succesfully Registered New User\nYour User ID is: " + temp.getId());
			logger.info("succesful registration");
			return temp;
		}
		System.out.println("User Registration Failed");
		logger.info("failed registration");
		throw new RuntimeException();
	}
	
	public static User login(String username, String password)
	{
		User temp = uDao.findByUsername(username);
		
		if(temp.getPassword().equals(password))
		{
			System.out.println("Login Succesful");
			logger.info("Succseful login");
			return temp;
		}
		
		System.out.println("Username and Password Do Not Match");
		logger.info("failed login attempt");
		throw new RuntimeException();
		//return null;
	}
	
	
	
	public static List<User> getAllUsers()
	{
		List<User> temp = uDao.findAll();
		if(Objects.isNull(temp))
		{
			System.out.println("Failed to Retrieve All Users");
			logger.info("Failed to Retrieve All Users");
			throw new RuntimeException();
		}
		
		logger.info("retrieved all user data");
		return temp;
	}
	
	public static User checkUser(int id)
	{
		User temp;
		temp = uDao.findById(id);
		
		if(!Objects.isNull(temp))
		{
			logger.info("returned user data");
		}
		else
		{
			System.out.println("Failed to Retrieve User");
			logger.info("retrieve user data failed");
			throw new RuntimeException();
		}
		
		return temp;
	}
	
	public static User checkUser(String username)
	{
		User temp;
		temp = uDao.findByUsername(username);
		
		if(!Objects.isNull(temp))
		{
			logger.info("returned user data");
		}
		else
		{
			System.out.println("Failed to Retrieve User");
			logger.info("retrieve user data failed");
			throw new RuntimeException();
		}
		
		return temp;
	}
	
	
	
	
	
	
	
}
