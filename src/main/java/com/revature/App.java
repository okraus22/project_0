package com.revature;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.dao.UserDao;
import com.revature.models.Account;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.service.AccountsService;
import com.revature.service.UserService;
import com.revature.util.connectionUtility;

@SuppressWarnings("unused")
public class App
{
	Scanner scan;

	public static void main(String[] args)
	{
		App app = new App();

		app.run();

	}

	public void run()
	{
		scan = new Scanner(System.in);

		User u = login();
		Account a;

		boolean shouldLoop = true;
		int input;
		Double dubInput;
		String sInput;

		while (shouldLoop)
		{
			System.out.println("What Would You Like to Do?");

			System.out.println("View Your Status: 1\nApply to Open an Account: 2");
			if (!u.getAccounts().isEmpty())
			{
				System.out.println("Withdraw From One of Your Accounts: 3");

				System.out.println("Deposit to One of Your Accounts: 4");

				System.out.println("Tranfer From One of Your Accounts to Another Account: 5");
			}

			if (u.getRole() == Role.employee || u.getRole() == Role.admin)
			{
				System.out.println(
						"Veiw A Users Info: 6\nView All Users Info: 7\nView An Account: 8\nView All Accounts: 9");
			}

			if (u.getRole() == Role.admin)
			{
				System.out.println("Approve, Deny, Or Cancel an Account: 10");
				System.out.println("Withdraw From Any Account: 11");

				System.out.println("Deposit to Any Account: 12");

				System.out.println("Tranfer From Any Account to Another Account: 13");
			}

			try
			{
				input = scan.nextInt();

			} catch (InputMismatchException e)
			{
				System.out.println("Input a number");
				continue;
			}

			switch (input)
			{
			case 1:
				System.out.println(u);

				break;
			case 2:

				System.out.println("Your New Account Number Is: " + AccountsService.openAccount(u.getId()));
				u.setAccounts(AccountsService.getUserAccounts(u.getId()));

				break;
			case 3:
				System.out.println(
						"Please Input the Number Corrosponding to the Account You Would Like To Withdraw From Or -1 to cancel");
				for (int i = 0; i < u.getAccounts().size(); i++)
				{
					System.out.println(u.getAccounts().get(i) + ": " + i);

				}
				while (true)
				{
					try
					{
						input = scan.nextInt();

					} catch (InputMismatchException e)
					{
						System.out.println("Input a number");
						continue;
					}
					if (input >= -1 && input < u.getAccounts().size())
						break;
					System.out.println("Please Input the Number Corrosponding to the Account You Would Like To Withdraw From Or -1 to cancel");
				}
				if(input == -1)
					break;
				System.out.println("Please Enter the amount you would like to withdraw");
				while (true)
				{
					try
					{
						dubInput = scan.nextDouble();

					} catch (InputMismatchException e)
					{
						System.out.println("Input a number");
						continue;
					}
						break;
				}
				try
				{
					AccountsService.withdraw(u.getId(), u.getAccounts().get(input).getId(), dubInput);
				} catch (Exception e)
				{
					// TODO: handle exception
				}
				
				break;
			case 4:
				System.out.println(
						"Please Input the Number Corrosponding to the Account You Would Like To Deposit To Or -1 to cancel");
				for (int i = 0; i < u.getAccounts().size(); i++)
				{
					System.out.println(u.getAccounts().get(i) + ": " + i);

				}
				while (true)
				{
					try
					{
						input = scan.nextInt();

					} catch (InputMismatchException e)
					{
						System.out.println("Input a number");
						continue;
					}
					if (input >= -1 && input < u.getAccounts().size())
						break;
					System.out.println("Please Input the Number Corrosponding to the Account You Would Like To Deposit To Or -1 to cancel");
				}
				if(input == -1)
					break;
				System.out.println("Please Enter the amount you would like to deposit");
				while (true)
				{
					try
					{
						dubInput = scan.nextDouble();

					} catch (InputMismatchException e)
					{
						System.out.println("Input a number");
						continue;
					}
						break;
				}
				try
				{
					AccountsService.deposit(u.getId(), u.getAccounts().get(input).getId(), dubInput);
				} catch (Exception e)
				{
					// TODO: handle exception
				}
				
				break;
			case 5:
				System.out.println(
						"Please Input the Number Corrosponding to the Account You Would Like To Transfer From Or -1 to cancel");
				for (int i = 0; i < u.getAccounts().size(); i++)
				{
					System.out.println(u.getAccounts().get(i) + ": " + i);

				}
				while (true)
				{
					try
					{
						input = scan.nextInt();

					} catch (InputMismatchException e)
					{
						System.out.println("Input a number");
						continue;
					}
					if (input >= -1 && input < u.getAccounts().size())
						break;
					System.out.println("Please Input the Number Corrosponding to the Account You Would Like To Transfer From Or -1 to cancel");
				}
				if(input == -1)
					break;
				System.out.println("Please Enter the amount you would like to Transfer");
				while (true)
				{
					try
					{
						dubInput = scan.nextDouble();

					} catch (InputMismatchException e)
					{
						System.out.println("Input a number");
						continue;
					}
						break;
				}
				
				int input2;
				
				System.out.println(
						"Please Input the Account number of the Account You Would Like To Transfer into Or -1 to cancel");
				
				while (true)
				{
					try
					{
						input2 = scan.nextInt();

					} catch (InputMismatchException e)
					{
						System.out.println("Input a number");
						continue;
					}
					break;
				}
				if(input == -1)
					break;
				try
				{
					AccountsService.transfer(u.getId(), u.getAccounts().get(input).getId(), input2, dubInput);
				} catch (Exception e)
				{
					// TODO: handle exception
				}
				
				break;
			case 6:
				if(!(u.getRole()==Role.employee ||u.getRole() == Role.admin))
				{
					break;
				}
				System.out.println("Input the username of the User you would like to view");
				sInput = scan.next();
				System.out.println(UserService.checkUser(sInput));
				break;
			case 7:
				if(!(u.getRole()==Role.employee ||u.getRole() == Role.admin))
				{
					break;
				}
				List<User> userList = UserService.getAllUsers();
				for (User user : userList)
				{
					System.out.println(user);
				}
				break;
			case 8:
				if(!(u.getRole()==Role.employee ||u.getRole() == Role.admin))
				{
					break;
				}
				System.out.println("Input the id of the account you would like to view");
				input = scan.nextInt();
				System.out.println(AccountsService.checkAccount(input));
				break;
			case 9:
				if(!(u.getRole()==Role.employee ||u.getRole() == Role.admin))
				{
					break;
				}
				List<Account> accountList = AccountsService.getAllAccounts();
				for (Account account : accountList)
				{
					System.out.println(account);
				}
				break;
			case 10:
				if(u.getRole() != Role.admin)
				{
					break;
				}
				System.out.println(
						"Please Input the Number Corrosponding to the Account You Would Like To Modify To Or -1 to cancel");
				accountList = AccountsService.getAllAccounts();
				for (int i = 0; i < accountList.size(); i++)
				{
					System.out.println(accountList.get(i) + ": " + i);

				}
				while (true)
				{
					try
					{
						input = scan.nextInt();

					} catch (InputMismatchException e)
					{
						System.out.println("Input a number");
						continue;
					}
					if (input >= -1 && input < u.getAccounts().size())
						break;
					System.out.println("Please Input the Number Corrosponding to the Account You Would Like To Modify To Or -1 to cancel");
				}
				if(input == -1)
					break;
				System.out.println("Would You like To approve or deny the account?\napprove: 1\ndeny: 2");
				input2 = scan.nextInt();
				if(input2 == 1)
				{
					AccountsService.setAccountOpen(input, true);
				}
				else
				{
					AccountsService.setAccountOpen(input, false);
				}
				
				break;
			case 11:
				if(u.getRole() != Role.admin)
				{
					break;
				}
				accountList = AccountsService.getAllAccounts();
				System.out.println(
						"Please Input the Number Corrosponding to the Account You Would Like To Withdraw From Or -1 to cancel");
				for (int i = 0; i < accountList.size(); i++)
				{
					System.out.println(accountList.get(i) + ": " + i);

				}
				while (true)
				{
					try
					{
						input = scan.nextInt();

					} catch (InputMismatchException e)
					{
						System.out.println("Input a number");
						continue;
					}
					if (input >= -1 && input <accountList.size())
						break;
					System.out.println("Please Input the Number Corrosponding to the Account You Would Like To Withdraw From Or -1 to cancel");
				}
				if(input == -1)
					break;
				System.out.println("Please Enter the amount you would like to withdraw");
				while (true)
				{
					try
					{
						dubInput = scan.nextDouble();

					} catch (InputMismatchException e)
					{
						System.out.println("Input a number");
						continue;
					}
						break;
				}
				try
				{
					AccountsService.withdraw(u.getId(), accountList.get(input).getId(), dubInput);
				} catch (Exception e)
				{
					// TODO: handle exception
				}
				
				break;
			case 12:
				if(u.getRole() != Role.admin)
				{
					break;
				}
				accountList = AccountsService.getAllAccounts();
				System.out.println(
						"Please Input the Number Corrosponding to the Account You Would Like To Deposit To Or -1 to cancel");
				for (int i = 0; i < accountList.size(); i++)
				{
					System.out.println(accountList.get(i) + ": " + i);

				}
				while (true)
				{
					try
					{
						input = scan.nextInt();

					} catch (InputMismatchException e)
					{
						System.out.println("Input a number");
						continue;
					}
					if (input >= -1 && input < accountList.size())
						break;
					System.out.println("Please Input the Number Corrosponding to the Account You Would Like To Deposit To Or -1 to cancel");
				}
				if(input == -1)
					break;
				System.out.println("Please Enter the amount you would like to deposit");
				while (true)
				{
					try
					{
						dubInput = scan.nextDouble();

					} catch (InputMismatchException e)
					{
						System.out.println("Input a number");
						continue;
					}
						break;
				}
				try
				{
					AccountsService.deposit(u.getId(), accountList.get(input).getId(), dubInput);
				} catch (Exception e)
				{
					// TODO: handle exception
				}
				
				break;
			case 13:
				if(u.getRole() != Role.admin)
				{
					break;
				}
				accountList = AccountsService.getAllAccounts();
				
				System.out.println(
						"Please Input the Number Corrosponding to the Account You Would Like To Transfer From Or -1 to cancel");
				for (int i = 0; i < accountList.size(); i++)
				{
					System.out.println(accountList.get(i) + ": " + i);

				}
				while (true)
				{
					try
					{
						input = scan.nextInt();

					} catch (InputMismatchException e)
					{
						System.out.println("Input a number");
						continue;
					}
					if (input >= -1 && input < accountList.size())
						break;
					System.out.println("Please Input the Number Corrosponding to the Account You Would Like To Transfer From Or -1 to cancel");
				}
				if(input == -1)
					break;
				System.out.println("Please Enter the amount you would like to Transfer");
				while (true)
				{
					try
					{
						dubInput = scan.nextDouble();

					} catch (InputMismatchException e)
					{
						System.out.println("Input a number");
						continue;
					}
						break;
				}
				
				
				System.out.println(
						"Please Input the Number Corrosponding to the Account You Would Like To Transfer From Or -1 to cancel");
				for (int i = 0; i < accountList.size(); i++)
				{
					System.out.println(accountList.get(i) + ": " + i);

				}
				while (true)
				{
					try
					{
						input2 = scan.nextInt();

					} catch (InputMismatchException e)
					{
						System.out.println("Input a number");
						continue;
					}
					if (input2 >= -1 && input2 < accountList.size())
						break;
					System.out.println("Please Input the Number Corrosponding to the Account You Would Like To Transfer From Or -1 to cancel");
				}
				if(input == -1)
					break;
				try
				{
					AccountsService.transfer(u.getId(), accountList.get(input).getId(), accountList.get(input2).getId(), dubInput);
				} catch (Exception e)
				{
					// TODO: handle exception
				}
				
				break;

			case -1:
				scan.close();
				System.exit(0);
				break;

			default:
				System.out.println("Please Input a Valid Option");
				break;
			}
		}

	}

	public User login()
	{
		User u = null;

		System.out.println("Would You like to Login or Register For an Account?");

		boolean shouldLoop = true;
		int input;

		while (shouldLoop)
		{
			System.out.println("Login: 1 \nRegister For an Account: 2 \nQuit the Program: -1");
			try
			{
				input = scan.nextInt();

			} catch (InputMismatchException e)
			{
				System.out.println("Input a Number");
				continue;
			}

			switch (input)
			{
			case 1:
				System.out.println("Please Enter Your Username");

				String username = scan.next();

				System.out.println("Please Enter Your Password");

				String password = scan.next();

				try
				{
					u = UserService.login(username, password);
					shouldLoop = false;

				} catch (Exception e)
				{

				}

				break;
			case 2:
				System.out.println("Please Enter a Username");

				username = scan.next();

				System.out.println("Please Enter a Password");

				password = scan.next();

				try
				{
					u = UserService.register(username, password);
					shouldLoop = false;

				} catch (Exception e)
				{

				}

				break;
			case -1:
				scan.close();
				System.exit(0);
				break;

			default:
				System.out.println("Please Input a Valid Option");
				break;
			}

		}

		return u;
	}

}
