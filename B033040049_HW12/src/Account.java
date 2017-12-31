
public class Account {

	String name;
	int balance;
	
	public Account(String name,int balance)
	{
		this.name = name;
		this.balance = balance;
	}
	public int getBalance()
	{
		return balance;
	}
	public void setBalance(int newbalance)
	{
		this.balance = newbalance;
	}
}
