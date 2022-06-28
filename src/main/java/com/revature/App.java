package com.revature;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.AccountDao;
import com.revature.dao.UserDao;
import com.revature.models.Account;
import com.revature.models.Role;
import com.revature.models.User;

import com.revature.util.connectionUtility;

public class App
{
	public static void main(String[] args)
	{
		User u;
		//System.out.println(u);
		
		Account a = new Account();
		a.setBalance(16689.65);
		System.out.println(a);
		
		AccountDao aDao = new AccountDao();
		aDao.insert(a, 2603001);
		
		UserDao uDao = new UserDao();
		//dao.insert(u);
		u = uDao.findById(2603001);
		System.out.println(u);
		
		
		
		//connectionUtility.getConnection();
		
	}
}
