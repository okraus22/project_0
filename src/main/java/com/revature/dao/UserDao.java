package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.util.connectionUtility;
import com.revature.models.Account;
import com.revature.models.Role;

public class UserDao implements IUserDao
{

	public boolean insert(User u)
	{

		String sql = "INSERT INTO users (user_id, username, password, role) values (?, ?, ?, ?) RETURNING users.user_id";

		try (Connection conn = connectionUtility.getConnection())
		{
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, u.getId());

			stmt.setString(2, u.getUsername());

			stmt.setString(3, u.getPassword());

			stmt.setObject(4, u.getRole(), Types.OTHER);

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

	public User findById(int id)
	{
		User temp = new User();

		String sql = "SELECT * FROM users WHERE user_id = ?";
		// String sql2 = "SELECT * FROM user_acount_junct WHERE user_id = ?";

		AccountDao a = new AccountDao();
		List<Account> accounts;
		accounts = a.findByOwner(id);

		try (Connection conn = connectionUtility.getConnection())
		{
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, id);

			ResultSet rs;

			if ((rs = stmt.executeQuery()) != null)
			{
				rs.next();

				temp.setId(rs.getInt("user_id"));
				temp.setUsername(rs.getString("username"));
				temp.setPassword(rs.getString("password"));
				temp.setRole(Role.valueOf(rs.getString("role")));
				temp.setAccounts(accounts);
			}

		} catch (SQLException e)
		{

			e.printStackTrace();
		}

		return temp;
	}

	public User findByUsername(String username)
	{
		User temp = new User();

		String sql = "SELECT * FROM users WHERE username = ?";
		// String sql2 = "SELECT * FROM user_acount_junct WHERE user_id = ?";

		AccountDao a = new AccountDao();
		List<Account> accounts;

		try (Connection conn = connectionUtility.getConnection())
		{
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, username);

			ResultSet rs;

			if ((rs = stmt.executeQuery()) != null)
			{
				rs.next();

				temp.setId(rs.getInt("user_id"));
				temp.setUsername(rs.getString("username"));
				temp.setPassword(rs.getString("password"));
				temp.setRole(Role.valueOf(rs.getString("role")));
				accounts = a.findByOwner(temp.getId());

				temp.setAccounts(accounts);
			}

		} catch (SQLException e)
		{

			e.printStackTrace();
		}

		return temp;
	}

	public List<User> findAll()
	{
		List<User> temp = new ArrayList<User>();

		User u;
		int id;
		String username, password;
		Role role;

		String sql = "SELECT * FROM users";
		// String sql2 = "SELECT * FROM user_acount_junct WHERE user_id = ?";

		AccountDao a = new AccountDao();
		List<Account> accounts;

		try (Connection conn = connectionUtility.getConnection())
		{
			Statement stmt = conn.createStatement();

			ResultSet rs;

			if ((rs = stmt.executeQuery(sql)) != null)
			{
				while (rs.next())
				{

					id = rs.getInt("user_id");
					username = rs.getString("username");
					password = rs.getString("password");
					role = Role.valueOf(rs.getString("role"));
					accounts = a.findByOwner(id);

					u = new User(id, username, password, role, accounts);

					temp.add(u);
				}

			}

		} catch (SQLException e)
		{

			e.printStackTrace();
		}

		return temp;
	}

	public int getCount()
	{

		int temp = -1;

		String sql = "SELECT count(user_id) FROM users";

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

	public boolean update(User u)
	{

		String sql = "Update users SET username = ?, password = ?, role = ? WHERE user_id = ? RETURNING username";

		try (Connection conn = connectionUtility.getConnection())
		{

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.setObject(3, u.getRole(), Types.OTHER);
			stmt.setInt(4, u.getId());

			if (stmt.execute())
			{
				return true;
			}

		} catch (SQLException e)
		{
			e.printStackTrace();

		}

		return false;
	}

	public boolean delete(int id)
	{
		String sql = "DELETE FROM users WHERE user_id = ? RETURNING true";

		try (Connection conn = connectionUtility.getConnection())
		{

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			if (stmt.execute())
			{
				return true;
			}

		} catch (SQLException e)
		{
			e.printStackTrace();

		}

		return false;
	}

}
