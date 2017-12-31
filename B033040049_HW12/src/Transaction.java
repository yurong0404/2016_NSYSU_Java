
public class Transaction {

	private int num;	//號碼牌
	private int amount;	//存款或提款的金額
	private String tranType;
	private Account account;
	
	public Transaction(int num,Account account,int amount,String tranType)
	{
		this.num = num;
		this.account = account;
		this.amount = amount;
		this.tranType = tranType;
	}
	public String getTrantype()
	{
		return this.tranType;
	}
	public Account getAccount()
	{
		return this.account;
	}
	public int getAmount()
	{
		return this.amount;
	}
	public int getNum()
	{
		return this.num;
	}
	
}
