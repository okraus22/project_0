package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.util.connectionUtility;

public class AccountDao implements IAccountDao
{

	@Override
	public boolean insert(Account a)
	{
		String sql = "INSERT INTO account (account_id, balance, is_open) values (?, ?, ?) RETURNING account.account_id";

		try (Connection conn = connectionUtility.getConnection())
		{
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, a.getId());

			stmt.setDouble(2, a.getBalance());

			stmt.setBoolean(3, a.isOpen());

			// ResultSet rs;

			if ((stmt.executeQuery()) != null)
			{
				return true;
			}

		} catch (SQLException e)
		{

			e.printStackTrace();
			
		}

		return false;
	}

	@Override
	public Account findById(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findAll()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount()
	{
		int temp = -1;

		String sql = "SELECT count(account_id) FROM account";

		try (Connection conn = connectionUtility.getConnection())
		{

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			rs.next();
			temp = rs.getInt(1);

		} catch (SQLException e)
		{
			e.printStackTrace();
			
		}

		return temp;
	}

	@Override
	public boolean update(Account u)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Account> findByOwner(int accOwnerId)
	{
		List<Account> temp = new ArrayList<>();
		
		String sql = "SELECT * FROM user_acount_junct WHERE user_id = ?";
		
		try(Connection conn = connectionUtility.getConnection())
		{
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs != null)
			{
				while(rs.next())
				{
					int id =rs.getInt("account_id");
					double balance = rs.getDouble("balance");
					boolean isOpen =rs.getBoolean("is_open");
					
					Account acc = new Account(balance,id,isOpen);
					
					temp.add(acc);
				}
			}
			
			
		} catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		
		return temp;
	}

	@Override
	public boolean delete(int id)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
