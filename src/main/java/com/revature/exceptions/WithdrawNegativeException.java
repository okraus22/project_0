package com.revature.exceptions;

public class WithdrawNegativeException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WithdrawNegativeException(String message)
	{
		super(message);
	}
}
