package myjava.homework;// Represents a withdrawal ATM transaction

public class Withdrawal extends Transaction{
	private int amount;
	private Keypad keypad;
	
	public Withdrawal(int a,Screen b,BankDatabase c,Keypad d)
	{
		super(a,b,c);
		this.keypad = d;
	}
	public void execute()
	{
		getScreen().displayMessage("How much do you want to withdraw: ");
		this.amount = keypad.getInput();
		getBankDatabase().credit(getAccountNumber(),amount);
		getScreen().displayMessage("Success!");
		getScreen().displayMessage("\n");
	}

}
