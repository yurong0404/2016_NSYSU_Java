package myjava.homework;
//Represents a bank account

public class Account {
	
	private int accountNumber;
	private int pin;
	private int totalBalance;
	
	public Account(int a,int b,int c)
	{
		this.accountNumber = a;
		this.pin = b;
		this.totalBalance = c;
	}
	
	public Boolean validatePIN(int a)	//check if PIN is correct
	{
		if(this.pin==a)
			return true;
		else
			return false;
	}
	public int getAccountNumber()
	{
		return this.accountNumber;
	}
	public int getTotalBalance()
	{
		return this.totalBalance;
	}
	public void credit(int a)		//withdraw money
	{
		this.totalBalance = this.totalBalance-a;
	}
	public void debit(int a)		//deposit money
	{
		this.totalBalance = this.totalBalance+a;
	}
}
