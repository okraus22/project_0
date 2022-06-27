package com.revature;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.UserDao;
import com.revature.models.Account;
import com.revature.models.Role;
import com.revature.models.User;

import com.revature.util.connectionUtility;

public class App
{
	public static void main(String[] args)
	{
		List<Account> accounts = new ArrayList<Account>();
		int id = "Admin".hashCode()/1000;
		id = id * 1000;
		
		User u = new User(id,"Admin", "Password",Role.admin,accounts);
		UserDao dao = new UserDao();
		System.out.println(u);
		
		
		//TODO test account dao stuff
		//connectionUtility.getConnection();
		
	}
}
