
public class Transaction {

	private int num;	//���X�P
	private int amount;	//�s�کδ��ڪ����B
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
