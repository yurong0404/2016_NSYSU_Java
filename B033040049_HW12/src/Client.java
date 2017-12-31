import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;


public class Client {
	String name;
	public void go()
	{
		int number;
		Scanner scn = new Scanner(System.in);
		//輸入有幾個客戶
		System.out.println("Please type the number of customers");
		while(true)
		{
			//防白目亂輸入
			try
			{
				number = Integer.parseInt(scn.nextLine());
				break;
			}
			catch(Exception e)
			{
				System.out.println("Input error,try again");
			}
		}
		//每個thread都處理一個客戶
		for(int i=1;i<=number;i++)
		{
			Thread t = new Thread(new Process()); 
			//請輸入客戶的名字
			System.out.println("please type customer's name");
			name = scn.nextLine();
			t.start();
		}
		scn.close();
	}
	public class Process implements Runnable
	{
		public void run()
		{
			Scanner scn = new Scanner(System.in);
			String tran;
			int amount;
			Random random=new Random();
			//交易的選項
			String[] tranType = {"withdraw","deposit"};
			
			try
			{
				for(int i=1;i<=5;i++)
				{
					
					//連線到Server
					Socket client = new Socket("127.0.0.1",53049);
					BufferedWriter buf = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
					//隨機生成交易的選項
					int r = random.nextInt(2);
					tran = tranType[r];
					//隨機生成交易金額
					amount = random.nextInt(200)+100;
					//使每一筆交易可以間隔一段時間送出
					Thread.sleep(random.nextInt(200)+100);
					buf.write(name+"\n");
					buf.write(tran+"\n");
					buf.write(Integer.toString(amount) + "\n");
					buf.flush();
					client.close();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public static void main(String args[])
	{
		new Client().go();
	}
}
