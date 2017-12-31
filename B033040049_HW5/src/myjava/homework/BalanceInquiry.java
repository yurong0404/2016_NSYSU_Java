package myjava.homework;
// Represents a balance inquiry ATM transaction

public class BalanceInquiry extends Transaction{

	public BalanceInquiry(int a,Screen b,BankDatabase c)
	{
		super(a,b,c);		//constructor of parent
	}
	public void execute()
	{
		getScreen().displayMessage("Balance Unformation:\n");
		getScreen().displayMessage("Total balance:");
		getScreen().displayMessage(String.valueOf(getBankDatabase().getTotalBalance(getAccountNumber())));
		getScreen().displayMessage("\n");
	}
	
}
