import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


public class Server {
	QueueMachine queueMachine = new QueueMachine();
	//銀行專員的代號
	String[] clerkAlphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	//clerkAlphabet的引索值
	int clerkNum = 0;
	//儲存每個名稱對應到的帳號資料
	HashMap<String,Account> map = new HashMap<String,Account>();
	public void go()
	{
		int number;
		Scanner scn = new Scanner(System.in);
		System.out.println("Please type the number of clerks");
		//輸入需要幾個服務人員
		while(true)
		{
			try
			{
				number = Integer.parseInt(scn.next());
				break;
			}
			catch(Exception e)
			{
				System.out.println("Input error,try again");
			}
		}
		//queue這個thread會一直待機著  準備接收客戶端傳進來的資料(有點類似ATM機台)
		Thread queue = new Thread(new queue()); 
		queue.start();
		//此迴圈的每一個thread分別代表一個clerk
		for(int i=1;i<=number;i++)
		{
			Thread t = new Thread(new Process()); 
			t.start();
		}
		scn.close();
	}
	public class queue implements Runnable
	{
		public void run()
		{
			//number代表customer的號碼牌
			int number = 0;
			try
			{
				//Server端的port是53049
				ServerSocket server = new ServerSocket(53049);
				while(true)
				{
					//等待client連接
					Socket client = server.accept();
					BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
					//讀customer的名字
					String name = br.readLine();
					//讀交易選項
					String tranType = br.readLine();
					//讀取金額
					int amount = Integer.parseInt(br.readLine());
					number++;
					Account account;
					//如果HashMap裡面有該名字的資料
					if(map.containsKey(name))
					{
						account = map.get(name);
					}
					//如果沒有該名字的資料  就幫該客戶建一個帳號資料
					else
					{
						map.put(name, new Account(name,5000));
						account = map.get(name);
					}
					//在序列加入一筆新的交易
					queueMachine.add(new Transaction(number,account,amount,tranType));
					System.out.println("Transaction[run:"+number+",account=Account["+"name:"+account.name+" ,"+"balance:"+account.getBalance()+"],"+"amount:"+amount+",tranType:"+tranType+"]");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public class Process implements Runnable
	{
		public void run()
		{
			//clerk認領名稱代號
			String clerk = clerkAlphabet[clerkNum];
			clerkNum++;
			Random random = new Random();
			while(true)
			{
				try
				{
					//clerk一開始先閒置一個隨機時間     避免多個clerk搶奪同一個交易    而導致deadlock
					Thread.sleep(random.nextInt(200)+100);
				}
				catch(Exception e)
				{
					
				}
				//如果交易序列不是空的
				if(!queueMachine.isEmpty())
				{
					//從序列拿一個交易出來
					Transaction t = queueMachine.get();
					System.out.println("Clerk "+clerk+"取得交易"+"Transaction[run:"+t.getNum()+",account=Account["+"name:"+t.getAccount().name+","+"balance:"+t.getAccount().getBalance()+"],"+"amount:"+t.getAmount()+",tranType:"+t.getTrantype()+"]");
					try
					{
						//處理交易需要的時間
						Thread.sleep(300);
					}
					catch(Exception e)
					{
						
					}
					if(t.getTrantype().equals("withdraw"))
					{
						//計算交易後的帳戶金額
						int dollar = t.getAccount().getBalance()-t.getAmount();
						//將帳戶餘額更新
						t.getAccount().setBalance(dollar);
						map.put(t.getAccount().name, t.getAccount());
					}
					else if(t.getTrantype().equals("deposit"))
					{
						int dollar = t.getAccount().getBalance()+t.getAmount();
						t.getAccount().setBalance(dollar);
						map.put(t.getAccount().name, t.getAccount());
					}
					System.out.println("Clerk "+clerk+"完成"+t.getNum()+"號交易處理，處理後帳戶餘額是"+t.getAccount().getBalance());
				}
			}
			
		}
	}
	public static void main(String args[])
	{
		new Server().go();
	}
}
