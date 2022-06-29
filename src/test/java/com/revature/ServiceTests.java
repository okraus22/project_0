package com.revature;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.AccountDao;
import com.revature.dao.UserDao;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.service.AccountsService;
import com.revature.service.UserService;

public class ServiceTests
{
	private UserDao mockUDao;
	private AccountDao mockADao;
	
	
	private User dummyUser;
	private Account dummyAccount;
	
	
	@Before
	public void setup() {
		
		// Let's mock the class that's being injected
		mockUDao = mock(UserDao.class);
		mockADao = mock(AccountDao.class);
		
		UserService.uDao = mockUDao;
		AccountsService.aDao = mockADao;
		
		
		// Let's also set up a stub user for passing in and stuff
		dummyUser = new User();
		dummyAccount = new Account(10.56,1);
		dummyUser.setAccounts(new LinkedList<Account>());
		
	}
	
	@After
	public void teardown() {
		
		dummyUser = null;
		dummyAccount = null;
		mockADao = null;
		mockUDao = null;
	}
	
	
	@Test
	public void testReturnAllAccount()
	{
		
		List<Account> testAccounts = new ArrayList<>();
		testAccounts.add(dummyAccount);
		
		when(mockADao.findAll()).thenReturn(testAccounts);
		
		assertEquals(testAccounts, AccountsService.getAllAccounts());
	}
}
