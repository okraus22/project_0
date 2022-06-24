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
		User u = new User(548004,"name", "passsword",Role.customer,accounts);
		UserDao dao = new UserDao();
		dao.insert(u);
		System.out.println(u);
		//connectionUtility.getConnection();
		
	}
}
