package com.revature.service;

import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDao;
import com.revature.dao.IAccountDao;
import com.revature.exceptions.OverdrawException;
import com.revature.exceptions.WithdrawNegativeException;
import com.revature.models.Account;

public class AccountsService
{

	public static IAccountDao aDao = new AccountDao();

	static Logger logger = Logger.getLogger(AccountsService.class);

	public static List<Account> getUserAccounts(int userId)
	{
		List<Account> temp;

		temp = aDao.findByOwner(userId);

		if (Objects.isNull(temp))
		{
			System.out.println("Failed to Retrieve the Accounts");
			logger.info("attempt to retrieve a useres accounts failed");
			throw new RuntimeException();
		}

		return temp;
	}

	public static boolean withdraw(int userId, int accountId, double amount)
	{
		if (amount < 0)
		{
			System.out.println("Tried to Withdraw a Negative Amount of Money");
			logger.info("Tried to Withdraw a Negative Amount of Money");
			throw new WithdrawNegativeException(null);
		}

		Account a = aDao.findById(accountId);
		if (Objects.isNull(a))
		{
			System.out.println("Could Not Find Account to Withdraw");
			logger.info("Could Not Find Account to Withdraw");
			throw new RuntimeException();
		}

		if (a.getBalance() < amount)
		{
			System.out.println("Tried to Withdraw More Money Than Is In Account");
			logger.info("Tried to Withdraw More Money Than Is In Account");
			throw new OverdrawException(null);
		}
		
		a.setBalance(a.getBalance() - amount);

		if (!aDao.update(a))
		{
			System.out.println("Tried to Update Account and Failed");
			logger.info("Tried to Update Account and Failed");
			throw new RuntimeException();
		}

		return true;
	}

	public static boolean deposit(int userId, int accountId, double amount)
	{
		if (amount < 0)
		{
			System.out.println("Tried to Deposit a Negative Amount of Money");
			logger.info("Tried to Deposit a Negative Amount of Money");
			throw new WithdrawNegativeException(null);
		}

		Account a = aDao.findById(accountId);
		if (Objects.isNull(a))
		{
			System.out.println("Could Not Find Account to Depost");
			logger.info("Could Not Find Account to Depost");
			throw new RuntimeException();
		}
		
		a.setBalance(a.getBalance() + amount);

		if (!aDao.update(a))
		{
			System.out.println("Tried to Update Account and Failed");
			logger.info("Tried to Update Account and Failed");
			throw new RuntimeException();
		}

		return true;
	}

	public static boolean transfer(int userId, int fromAccount, int toAccount, double amount)
	{
		if (amount < 0)
		{
			System.out.println("Tried to Deposit a Negative Amount of Money");
			logger.info("Tried to Deposit a Negative Amount of Money");
			throw new WithdrawNegativeException(null);
		}

		Account a1 = aDao.findById(fromAccount);
		
		
		if (Objects.isNull(a1))
		{
			System.out.println("Could Not Find Account to Withdraw From");
			logger.info("Could Not Find Account to Withdraw From");
			throw new RuntimeException();
		}
		
		Account a2 = aDao.findById(toAccount);
		
		if (Objects.isNull(a2))
		{
			System.out.println("Could Not Find Account to Deposit To");
			logger.info("Could Not Find Account to Deposit To");
			throw new RuntimeException();
		}
		
		a1.setBalance(a1.getBalance() - amount);
		
		a2.setBalance(a2.getBalance() + amount);

		if (!aDao.update(a1))
		{
			System.out.println("Tried to Update Account and Failed");
			logger.info("Tried to Update Account and Failed");
			throw new RuntimeException();
		}
		
		if (!aDao.update(a2))
		{
			System.out.println("Tried to Update Account and Failed");
			logger.info("Tried to Update Account and Failed");
			throw new RuntimeException();
		}

		return true;
	}

	public static int openAccount(int userId)
	{
		int temp;

		Account a = new Account();

		if (!aDao.insert(a, userId))
		{
			System.out.println("Failed to Create the Account");
			logger.info("attempt to create an account failed");
			throw new RuntimeException();
		}
		temp = a.getId();

		return temp;
	}

	public static Account checkAccount(int accountId)
	{
		Account temp;

		temp = aDao.findById(accountId);
		
		if (Objects.isNull(temp))
		{
			System.out.println("Failed to Find the Account");
			logger.info("attempt to Find an account failed");
			throw new RuntimeException();
		}

		return temp;
	}

	public static boolean setAccountOpen(int accountId, boolean shouldBeOpen)
	{
		Account a;
		
		a = aDao.findById(accountId);
		if(Objects.isNull(a))
		{
			System.out.println("Failed to Find the Account");
			logger.info("attempt to Find an account failed");
			throw new RuntimeException();
		}
		
		a.setOpen(shouldBeOpen);
		
		if(!aDao.update(a))
		{
			System.out.println("Tried to Update Account and Failed");
			logger.info("Tried to Update Account and Failed");
			throw new RuntimeException();
		}
		
		
		return true;
	}

}
