import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


public class Server {
	QueueMachine queueMachine = new QueueMachine();
	//�Ȧ�M�����N��
	String[] clerkAlphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	//clerkAlphabet���ޯ���
	int clerkNum = 0;
	//�x�s�C�ӦW�ٹ����쪺�b�����
	HashMap<String,Account> map = new HashMap<String,Account>();
	public void go()
	{
		int number;
		Scanner scn = new Scanner(System.in);
		System.out.println("Please type the number of clerks");
		//��J�ݭn�X�ӪA�ȤH��
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
		//queue�o��thread�|�@���ݾ���  �ǳƱ����Ȥ�ݶǶi�Ӫ����(���I����ATM���x)
		Thread queue = new Thread(new queue()); 
		queue.start();
		//���j�骺�C�@��thread���O�N��@��clerk
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
			//number�N��customer�����X�P
			int number = 0;
			try
			{
				//Server�ݪ�port�O53049
				ServerSocket server = new ServerSocket(53049);
				while(true)
				{
					//����client�s��
					Socket client = server.accept();
					BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
					//Ūcustomer���W�r
					String name = br.readLine();
					//Ū����ﶵ
					String tranType = br.readLine();
					//Ū�����B
					int amount = Integer.parseInt(br.readLine());
					number++;
					Account account;
					//�p�GHashMap�̭����ӦW�r�����
					if(map.containsKey(name))
					{
						account = map.get(name);
					}
					//�p�G�S���ӦW�r�����  �N���ӫȤ�ؤ@�ӱb�����
					else
					{
						map.put(name, new Account(name,5000));
						account = map.get(name);
					}
					//�b�ǦC�[�J�@���s�����
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
			//clerk�{��W�٥N��
			String clerk = clerkAlphabet[clerkNum];
			clerkNum++;
			Random random = new Random();
			while(true)
			{
				try
				{
					//clerk�@�}�l�����m�@���H���ɶ�     �קK�h��clerk�m�ܦP�@�ӥ��    �ӾɭPdeadlock
					Thread.sleep(random.nextInt(200)+100);
				}
				catch(Exception e)
				{
					
				}
				//�p�G����ǦC���O�Ū�
				if(!queueMachine.isEmpty())
				{
					//�q�ǦC���@�ӥ���X��
					Transaction t = queueMachine.get();
					System.out.println("Clerk "+clerk+"���o���"+"Transaction[run:"+t.getNum()+",account=Account["+"name:"+t.getAccount().name+","+"balance:"+t.getAccount().getBalance()+"],"+"amount:"+t.getAmount()+",tranType:"+t.getTrantype()+"]");
					try
					{
						//�B�z����ݭn���ɶ�
						Thread.sleep(300);
					}
					catch(Exception e)
					{
						
					}
					if(t.getTrantype().equals("withdraw"))
					{
						//�p�����᪺�b����B
						int dollar = t.getAccount().getBalance()-t.getAmount();
						//�N�b��l�B��s
						t.getAccount().setBalance(dollar);
						map.put(t.getAccount().name, t.getAccount());
					}
					else if(t.getTrantype().equals("deposit"))
					{
						int dollar = t.getAccount().getBalance()+t.getAmount();
						t.getAccount().setBalance(dollar);
						map.put(t.getAccount().name, t.getAccount());
					}
					System.out.println("Clerk "+clerk+"����"+t.getNum()+"������B�z�A�B�z��b��l�B�O"+t.getAccount().getBalance());
				}
			}
			
		}
	}
	public static void main(String args[])
	{
		new Server().go();
	}
}
