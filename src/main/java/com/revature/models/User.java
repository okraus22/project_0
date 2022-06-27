package com.revature.models;

import java.io.Serializable;
import java.util.List;

import com.revature.dao.UserDao;

public class User implements Serializable
{
	private int id;
	private String username;
	private String password;
	private Role role;
	private List<Account> accounts;
	
	public User()
	{
		
	}

	public User(int id, String username, String password, Role role, List<Account> accounts)
	{
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.accounts = accounts;
	}

	public User(String username, String password)
	{
		super();
		this.username = username;
		this.password = password;
		
		id = generateId();
		role = Role.customer;
		
		System.out.println(id);
	}

	public User(String username, String password, Role role)
	{
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		
		id = generateId();
	}
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

	public List<Account> getAccounts()
	{
		return accounts;
	}

	public void setAccounts(List<Account> accounts)
	{
		this.accounts = accounts;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", accounts="
				+ accounts + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (accounts == null)
		{
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		if (id != other.id)
			return false;
		if (password == null)
		{
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
			return false;
		if (username == null)
		{
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	private int generateId()
	{
		UserDao u = new UserDao();
		int id;
		
		int hash = username.hashCode();
		hash /=1000;
		hash*=1000;
		
		id = hash + u.getCount();
		
		
		return id;
	}
	
}
