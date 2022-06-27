package com.revature.models;

import java.io.Serializable;
import java.util.Objects;

import com.revature.dao.AccountDao;

public class Account implements Serializable
{
	private double balance = 0;
	private int id;
	private boolean isOpen;
	
	
	public Account()
	{
		balance = 0;
		id = ( int)Math.random()*10000;
		id *= 1000;
		AccountDao a = new AccountDao();
		id += a.getCount();
		
	}


	@Override
	public String toString()
	{
		return "Account [balance=" + balance + ", id=" + id + ", isOpen=" + isOpen + "]";
	}


	public double getBalance()
	{
		return balance;
	}


	public void setBalance(double balance)
	{
		this.balance = balance;
	}


	public int getId()
	{
		return id;
	}


	public void setId(int id)
	{
		this.id = id;
	}


	public boolean isOpen()
	{
		return isOpen;
	}


	public void setOpen(boolean isOpen)
	{
		this.isOpen = isOpen;
	}


	@Override
	public int hashCode()
	{
		return Objects.hash(balance, id, isOpen);
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
		Account other = (Account) obj;
		return Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance) && id == other.id
				&& isOpen == other.isOpen;
	}


	public Account(double balance, int id, boolean isOpen)
	{
		super();
		this.balance = balance;
		this.id = id;
		this.isOpen = isOpen;
	}


	public Account(double balance, int id)
	{
		super();
		this.balance = balance;
		this.id = id;
	}
	
}
