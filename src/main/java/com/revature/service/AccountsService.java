package com.revature.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDao;
import com.revature.dao.IAccountDao;
import com.revature.models.Account;

public class AccountsService
{

	public IAccountDao aDao = new AccountDao();
	
	Logger logger = Logger.getLogger(AccountsService.class);
	
	public List<Account> getUserAccounts(int userId)
	{
		//TODO
		
		return null;
	}
	
	public boolean withdraw(int userId, int accountId, double amount)
	{
		//TODO 
		
		return false;
	}
	
	public boolean deposit(int userId, int accountId, double amount)
	{
		//TODO 
		
		return false;
	}
	
	public boolean transfer(int userId, int fromAccount, int toAccount, double amount)
	{
		//TODO 
		
		return false;
	}
	
	public int openAccount(int userId)
	{
		//TODO
		
		return 0;
	}
	
	public Account checkAccount(int accountId)
	{
		//TODO 
		
		return null;
	}
	
	public boolean setAccountOpen(int accountId, boolean shouldBeOpen)
	{
		//TODO
		
		return false;
	}
	
	
}
