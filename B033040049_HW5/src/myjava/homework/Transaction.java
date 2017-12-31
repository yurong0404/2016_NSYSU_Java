package myjava.homework;// Abstract superclass Transaction represents an ATM transaction

public abstract class Transaction {
	
	private int accountNumber;
	private Screen screen;
	private BankDatabase bank;
	
	public Transaction(int a,Screen b,BankDatabase c)
	{
		this.accountNumber = a;
		this.screen = b;
		this.bank = c;
	}
	public int getAccountNumber()
	{
		return this.accountNumber;
	}
	public Screen getScreen()
	{
		return this.screen;
	}
	public BankDatabase getBankDatabase()
	{
		return this.bank;
	}
	public abstract void execute();
}
