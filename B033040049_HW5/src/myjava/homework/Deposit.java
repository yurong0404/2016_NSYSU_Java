package myjava.homework;// Represents a deposit ATM transaction

public class Deposit extends Transaction{
	private int amount;
	private Keypad keypad;
	
	public Deposit(int a,Screen b,BankDatabase c,Keypad d)
	{
		super(a,b,c);		//constructor of parent
		keypad = d;
	}
	public void execute()
	{
		getScreen().displayMessage("How much do you want to deposit: ");
		this.amount = keypad.getInput();		//key the money you want to deposit
		getBankDatabase().debit(getAccountNumber(),amount);
		getScreen().displayMessageLine("Insufficient funds in your account");
		getScreen().displayMessage("\n");
	}

}
