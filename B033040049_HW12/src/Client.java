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
		//��J���X�ӫȤ�
		System.out.println("Please type the number of customers");
		while(true)
		{
			//���եضÿ�J
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
		//�C��thread���B�z�@�ӫȤ�
		for(int i=1;i<=number;i++)
		{
			Thread t = new Thread(new Process()); 
			//�п�J�Ȥ᪺�W�r
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
			//������ﶵ
			String[] tranType = {"withdraw","deposit"};
			
			try
			{
				for(int i=1;i<=5;i++)
				{
					
					//�s�u��Server
					Socket client = new Socket("127.0.0.1",53049);
					BufferedWriter buf = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
					//�H���ͦ�������ﶵ
					int r = random.nextInt(2);
					tran = tranType[r];
					//�H���ͦ�������B
					amount = random.nextInt(200)+100;
					//�ϨC�@������i�H���j�@�q�ɶ��e�X
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
