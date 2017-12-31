package myjava.homework;// Represents the bank account information database

public class BankDatabase {
	
	private Account[] accounts; // array of Accounts
	
    // no-argument BankDatabase constructor initializes accounts
	public BankDatabase () {
		accounts = new Account[2];  // just 2 accounts for testing
		accounts[0] = new Account(12345, 54321, 1000);
		accounts[1] = new Account(67890, 98765, 100);
	}
	
	public Account[] getAccount()
	{
		return this.accounts;
	}
	
	public int getTotalBalance(int userAccountNumber)
	{
		for(Account a : accounts)
		{
			if(userAccountNumber==a.getAccountNumber())
			{
				return a.getTotalBalance();
			}
		}
		return 0;
	}
	
	public void credit(int userAccountNumber,int amount)
	{
		for(Account a : accounts)
		{
			if(userAccountNumber==a.getAccountNumber())
			{
				a.credit(amount);		//call the Account.credit() function
				break;
			}
		}
	}
	
	public void debit(int userAccountNumber,int amount)
	{
		for(Account a : accounts)
		{
			if(userAccountNumber==a.getAccountNumber())	
			{
				a.debit(amount);		//call the Account.debit() function
				break;
			}
		}
	}
}
