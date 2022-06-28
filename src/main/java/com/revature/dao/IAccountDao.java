package com.revature.dao;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;

public interface IAccountDao
{
	boolean insert(Account a, int userId); 

	// Read: return 1 or return all
	Account findById(int id); // Returns user object associated with this id in DB

	List<Account> findAll(); // Return a list of all user objects in the DB;

	int getCount();
	
	int getNumUsers();
	
	// Update
	boolean update(Account u); // Updates a user in the DB and return true if successful and false if not
	
	boolean addUser(int accountId, int userId);
	
	List<Account> findByOwner(int accOwnerId);

	// Delete
	boolean delete(int id); // Delete the user associated with the ID;
}
