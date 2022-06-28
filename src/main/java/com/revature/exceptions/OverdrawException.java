package com.revature.exceptions;

public class OverdrawException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OverdrawException(String message)
	{
		super(message);
	}
}
