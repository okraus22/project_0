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
	public boolean insert(Account a, int userId)
	{
		String sql = "INSERT INTO account (account_id, balance, is_open) values (?, ?, ?) returning account.account_id";
		String sql2 = "INSERT INTO user_acount_junct (user_id, account_id) values (?, ?) returning 5";

		try (Connection conn = connectionUtility.getConnection())
		{
			PreparedStatement stmt = conn.prepareStatement(sql);
			PreparedStatement stmt2 = conn.prepareStatement(sql2);

			stmt.setInt(1, a.getId());

			stmt.setDouble(2, a.getBalance());

			stmt.setBoolean(3, a.isOpen());

			stmt2.setInt(1, userId);
			stmt2.setInt(2, a.getId());

			// ResultSet rs;

			if (stmt.execute() && stmt2.execute())
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
		String sql2 = "Select * FROM account where account_id = ?";
		try (Connection conn = connectionUtility.getConnection())
		{

			PreparedStatement stmt = conn.prepareStatement(sql);
			PreparedStatement stmt2 = conn.prepareStatement(sql2);

			stmt.setInt(1, accOwnerId);

			ResultSet rs = stmt.executeQuery();
			ResultSet accountResult;

			if (rs != null)
			{
				while (rs.next())
				{
					int id = rs.getInt("account_id");

					stmt2.setInt(1, id);
					accountResult = stmt2.executeQuery();
					accountResult.next();
					double balance = accountResult.getDouble("balance");
					boolean isOpen = accountResult.getBoolean("is_open");

					Account acc = new Account(balance, id, isOpen);

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

	@Override
	public boolean addUser(int accountId, int userId)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getNumUsers()
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
