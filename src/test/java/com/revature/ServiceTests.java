package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.rmi.server.RemoteStub;
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
	private User mockUser;
	
	private User dummyUser;
	private Account dummyAccount;
	
	
	@Before
	public void setup() {
		
		// Let's mock the class that's being injected
		mockUDao = mock(UserDao.class);
		mockADao = mock(AccountDao.class);
		mockUser = mock(User.class);
		
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
		mockUser = null;
	}
	
	/**/
	@Test
	public void testReturnAllAccount()
	{
		
		List<Account> testAccounts = new ArrayList<>();
		testAccounts.add(dummyAccount);
		
		when(mockADao.findAll()).thenReturn(testAccounts);
		
		assertEquals(testAccounts, AccountsService.getAllAccounts());
	}
	
	@Test(expected = RuntimeException.class)
	public void returnAllAccountFail() 
	{
		//List<Account> testAccounts = new ArrayList<>();
		//testAccounts.add(dummyAccount);
		
		when(mockADao.findAll()).thenReturn(null);
		AccountsService.getAllAccounts();
		
		//assertEquals(testAccounts, AccountsService.getAllAccounts());
	}
	
	@Test
	public void loginSucess()
	{
		dummyUser.setUsername("name");
		dummyUser.setPassword("Pass");
		when(mockUDao.findByUsername("name")).thenReturn(dummyUser);
		assertEquals( UserService.login("name", "Pass"),dummyUser);
	}
	@Test(expected = RuntimeException.class)
	public void loginFail()
	{
		dummyUser.setUsername("name");
		dummyUser.setPassword("Pass");
		when(mockUDao.findByUsername("name")).thenReturn(dummyUser);
		assertEquals( UserService.login("name", "ss"),dummyUser);
	}
	
	@Test
	public void getAllUserSuccess()
	{
		List<User> testUsers = new ArrayList<>();
		testUsers.add(dummyUser);
		
		when(mockUDao.findAll()).thenReturn(testUsers);
		assertEquals(UserService.getAllUsers(), testUsers); 
	}
	@Test(expected = RuntimeException.class)
	public void getAllUserFail()
	{
		List<User> testUsers = new ArrayList<>();
		testUsers.add(dummyUser);
		
		when(mockUDao.findAll()).thenReturn(null);
		UserService.getAllUsers(); 
	}
	
	@Test
	public void checkUserIdSuccess()
	{
		//List<User> testUsers = new ArrayList<>();
		//testUsers.add(dummyUser);
		
		when(mockUDao.findById(0)).thenReturn(dummyUser);
		assertEquals(UserService.checkUser(0), dummyUser); 
	}
	
	@Test(expected = RuntimeException.class)
	public void checkUserIdFail()
	{
		when(mockUDao.findById(0)).thenReturn(null);
		UserService.checkUser(0);
	}
	
	public void checkUsernameSuccess()
	{
		//List<User> testUsers = new ArrayList<>();
		//testUsers.add(dummyUser);
		dummyUser.setUsername("Test");
		when(mockUDao.findByUsername("Test")).thenReturn(dummyUser);
		assertEquals(UserService.checkUser("Test"), dummyUser); 
	}
	
	@Test(expected = RuntimeException.class)
	public void checkUsernameFail()
	{
		when(mockUDao.findByUsername("Test")).thenReturn(null);
		UserService.checkUser("Test");
	}
	
	@Test
	public void getUserAccountsSuccess()
	{
		List<Account> testAccounts = new ArrayList<>();
		testAccounts.add(dummyAccount);
		//dummyUser.setUsername("Test");
		when(mockADao.findByOwner(1)).thenReturn(testAccounts);
		
		assertEquals(AccountsService.getUserAccounts(1), testAccounts); 
	}
	
	@Test(expected = RuntimeException.class)
	public void getUserAccountsFail()
	{
		when(mockADao.findByOwner(1)).thenReturn(null);
		AccountsService.getUserAccounts(1);
	}
	
	@Test
	public void WithdrawSuccess()
	{
		int id = 2;
		int accountId =2;
		double amount = 56.78;
		dummyAccount.setBalance(100);
		
		
		//dummyUser.setUsername("Test");
		when(mockADao.findById(id)).thenReturn(dummyAccount);
		when(mockADao.update(dummyAccount)).thenReturn(true);
		
		assertTrue(AccountsService.withdraw(id, accountId, amount));
	}
	
	@Test(expected = RuntimeException.class)
	public void WithdrawNegativeFail()
	{
		int id = 2;
		int accountId =2;
		double amount = -56.78;
		dummyAccount.setBalance(100);
		
		//when(mockADao.findByOwner(1)).thenReturn(null);
		AccountsService.withdraw(id, accountId, amount);
	}
	
	@Test(expected = RuntimeException.class)
	public void WithdrawOverDrawFail()
	{
		int id = 2;
		int accountId =2;
		double amount = 56.78;
		dummyAccount.setBalance(55.78);
		
		when(mockADao.findById(id)).thenReturn(dummyAccount);
		when(mockADao.update(dummyAccount)).thenReturn(true);
		AccountsService.withdraw(id, accountId, amount);
	}
	
	@Test(expected = RuntimeException.class)
	public void WithdrawUpdateFail()
	{
		int id = 2;
		int accountId =2;
		double amount = 56.78;
		dummyAccount.setBalance(100);
		
		when(mockADao.findById(id)).thenReturn(dummyAccount);
		when(mockADao.update(dummyAccount)).thenReturn(false);
		AccountsService.withdraw(id, accountId, amount);
	}
	@Test(expected = RuntimeException.class)
	public void WithdrawFindAccountFail()
	{
		int id = 2;
		int accountId =2;
		double amount = 56.78;
		dummyAccount.setBalance(100);
		
		when(mockADao.findById(id)).thenReturn(null);
		when(mockADao.update(dummyAccount)).thenReturn(true);
		AccountsService.withdraw(id, accountId, amount);
	}
	
	@Test
	public void depostSuccess()
	{
		int id = 2;
		int accountId =2;
		double amount = 56.78;
		dummyAccount.setBalance(100);
		
		
		//dummyUser.setUsername("Test");
		when(mockADao.findById(id)).thenReturn(dummyAccount);
		when(mockADao.update(dummyAccount)).thenReturn(true);
		
		assertTrue(AccountsService.deposit(id, accountId, amount));
	}
	
	@Test(expected = RuntimeException.class)
	public void DepositNegativeFail()
	{
		int id = 2;
		int accountId =2;
		double amount = -56.78;
		dummyAccount.setBalance(100);
		
		//when(mockADao.findByOwner(1)).thenReturn(null);
		AccountsService.deposit(id, accountId, amount);
	}
	
	
	@Test(expected = RuntimeException.class)
	public void DepositUpdateFail()
	{
		int id = 2;
		int accountId =2;
		double amount = 56.78;
		dummyAccount.setBalance(100);
		
		when(mockADao.findById(id)).thenReturn(dummyAccount);
		when(mockADao.update(dummyAccount)).thenReturn(false);
		AccountsService.deposit(id, accountId, amount);
	}
	@Test(expected = RuntimeException.class)
	public void DepositFindAccountFail()
	{
		int id = 2;
		int accountId =2;
		double amount = 56.78;
		dummyAccount.setBalance(100);
		
		when(mockADao.findById(id)).thenReturn(null);
		when(mockADao.update(dummyAccount)).thenReturn(true);
		AccountsService.deposit(id, accountId, amount);
	}
	
	///////////////////////////////////////////////////////////
	//transferAccount
	/* */
	
	@Test
	public void TranserSuccess()
	{
		int id = 2;
		int accountId =2;
		int accountId2 = 3;
		double amount = 56.78;
		dummyAccount.setBalance(100);
		Account dummyAccount2 = new Account(54,1);
		dummyAccount2.setBalance(57);
		
		//dummyUser.setUsername("Test");
		when(mockADao.findById(accountId)).thenReturn(dummyAccount);
		when(mockADao.findById(accountId2)).thenReturn(dummyAccount2);
		when(mockADao.update(dummyAccount)).thenReturn(true);
		when(mockADao.update(dummyAccount2)).thenReturn(true);
		
		assertTrue(AccountsService.transfer(id, accountId,accountId2, amount));
		
	}
	
	
	@Test(expected = RuntimeException.class)
	public void TransferNegativeFail()
	{
		int id = 2;
		int accountId =2;
		int accountId2 = 3;
		double amount = -56.78;
		dummyAccount.setBalance(100);
		Account dummyAccount2 = new Account(54,1);
		dummyAccount2.setBalance(57);
		
		//when(mockADao.findByOwner(1)).thenReturn(null);
		AccountsService.transfer(id, accountId,accountId2, amount);
	}
	
	
	@Test(expected = RuntimeException.class)
	public void TransferUpdateFail()
	{
		int id = 2;
		int accountId =2;
		int accountId2 = 3;
		double amount = 56.78;
		dummyAccount.setBalance(100);
		Account dummyAccount2 = new Account(54,1);
		dummyAccount2.setBalance(57);
		
		when(mockADao.findById(accountId)).thenReturn(dummyAccount);
		when(mockADao.findById(accountId2)).thenReturn(dummyAccount2);
		when(mockADao.update(dummyAccount)).thenReturn(false);
		when(mockADao.update(dummyAccount2)).thenReturn(true);
		AccountsService.transfer(id, accountId,accountId2, amount);
	}
	
	@Test(expected = RuntimeException.class)
	public void TransferUpdateFail2()
	{
		int id = 2;
		int accountId =2;
		int accountId2 = 3;
		double amount = 56.78;
		dummyAccount.setBalance(100);
		Account dummyAccount2 = new Account(54,1);
		dummyAccount2.setBalance(57);
		
		when(mockADao.findById(accountId)).thenReturn(dummyAccount);
		when(mockADao.findById(accountId2)).thenReturn(dummyAccount2);
		when(mockADao.update(dummyAccount)).thenReturn(true);
		when(mockADao.update(dummyAccount2)).thenReturn(false);
		AccountsService.transfer(id, accountId,accountId2, amount);
	}
	
	@Test(expected = RuntimeException.class)
	public void TransferFindAccountFail()
	{
		int id = 2;
		int accountId =2;
		int accountId2 = 3;
		double amount = 56.78;
		dummyAccount.setBalance(100);
		Account dummyAccount2 = new Account(54,1);
		dummyAccount2.setBalance(57);
		
		when(mockADao.findById(accountId)).thenReturn(null);
		when(mockADao.findById(accountId2)).thenReturn(dummyAccount2);
		when(mockADao.update(dummyAccount)).thenReturn(true);
		when(mockADao.update(dummyAccount2)).thenReturn(true);
		
		AccountsService.transfer(id, accountId,accountId2, amount);
	}
	
	@Test(expected = RuntimeException.class)
	public void TransferFindAccount2Fail()
	{
		int id = 2;
		int accountId =2;
		int accountId2 = 3;
		double amount = 56.78;
		dummyAccount.setBalance(100);
		Account dummyAccount2 = new Account(54,1);
		dummyAccount2.setBalance(57);
		
		when(mockADao.findById(accountId)).thenReturn(dummyAccount);
		when(mockADao.findById(accountId2)).thenReturn(null);
		when(mockADao.update(dummyAccount)).thenReturn(true);
		when(mockADao.update(dummyAccount2)).thenReturn(true);
		
		AccountsService.transfer(id, accountId,accountId2, amount);
	}
	
	@Test
	public void CheckAccountSuccess()
	{
		int accountId = 2;
		when(mockADao.findById(accountId)).thenReturn(dummyAccount);
		
		assertEquals(AccountsService.checkAccount(accountId),dummyAccount);
	}
	
	@Test(expected = RuntimeException.class)
	public void CheckAccountFail()
	{
		int accountId = 2;
		when(mockADao.findById(accountId)).thenReturn(null);
		
		AccountsService.checkAccount(accountId);
	}
	
	@Test
	public void SetAccountOpenSuccess()
	{
		int accountId = 2;
		
		when(mockADao.findById(accountId)).thenReturn(dummyAccount);
		when(mockADao.update(dummyAccount)).thenReturn(true);
		
		
		assertTrue(AccountsService.setAccountOpen(accountId, false));
	}
	
	@Test(expected = RuntimeException.class)
	public void SetAccountOpenIDFail()
	{
		int accountId = 2;
		when(mockADao.findById(accountId)).thenReturn(null);
		when(mockADao.update(dummyAccount)).thenReturn(true);
		AccountsService.setAccountOpen(accountId, false);
	}
	@Test(expected = RuntimeException.class)
	public void SetAccountOpenUpdateFail()
	{
		int accountId = 2;
		when(mockADao.findById(accountId)).thenReturn(dummyAccount);
		when(mockADao.update(dummyAccount)).thenReturn(false);
		AccountsService.setAccountOpen(accountId, false);
	}
	
	/**/
}
