package com.revature.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.IUserDao;
import com.revature.dao.UserDao;
import com.revature.models.Account;
import com.revature.models.Role;
import com.revature.models.User;

public class UserService
{
	public IUserDao uDao = new UserDao();
	
	Logger logger = Logger.getLogger(UserService.class);
	
	public User register(String username, String password)
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
	
	public User register(String username, String password, Role role)
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
	
	public User login(String username, String password)
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
		return null;
	}
	
	
	
	public List<User> getAllUsers()
	{
		List<User> temp = uDao.findAll();
		if(temp == null)
		{
			System.out.println("Failed to Retrieve All Users");
			logger.info("Failed to Retrieve All Users");
		}
		
		logger.info("retrieved all user data");
		return temp;
	}
	
	public User checkUser(int id)
	{
		//TODO
		
		return null;
	}
	
	public User checkUser(String username)
	{
		//TODO
		
		return null;
	}
	
	
	
	
	
	
	
}
