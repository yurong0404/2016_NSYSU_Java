import java.io.*;
import java.net.*;

public class Server {

	public static void main(String[] args)
	{
		int num_a,num_b,client_num=0;
		num_a = Integer.parseInt(args[0]);
		num_b = Integer.parseInt(args[1]);
		
		
		ServerSocket server;
		Socket clientSocket;
		
		System.out.println("\nPort : "+args[2]);
		System.out.println("Resource: "+"< A : "+args[0]+" B : "+args[1]+" >");
		
		System.out.println("Listening");
		try
		{
			//�إ�Server
			server = new ServerSocket(Integer.parseInt(args[2]));
			while(true)
			{
				System.out.print("\n");
				//����client���s�u
				clientSocket = server.accept();
				client_num++;
				
				//�إ߱����Ӧ�client�ݸ�ƪ�����
				ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
				int take_a,take_b;
				//Ū���ݭn���ӫ~�Ʈ�
				take_a = in.readInt();
				take_b = in.readInt();
				
				//�p�G�ݨD�ӫ~�ƶq�j��{�����ӫ~�ƶq   �N������
				if(take_a>num_a ||take_b>num_b)
				{
					System.out.println("[Client_"+client_num+"] : "+"resource insufficient! Error...");
					take_a=0;
					take_b=0;
				}
				//�����ӫ~
				else
				{
					System.out.println("[Client_"+client_num+"] : �����ӫ~-> A: "+take_a+" B: "+take_b);
					num_a = num_a-take_a;
					num_b = num_b-take_b;
				}
				System.out.println("	�ӫ~�Ӽ�-> A: "+num_a+" B: "+num_b);
				
				//�ɳf����  �ɨ�쥻���ƶq
				System.out.println("�ɳf->A: "+take_a+" B: "+take_b);
				num_a = num_a + take_a;
				num_b = num_b + take_b;
				System.out.println("	�ӫ~�Ӽ�-> A: "+num_a+" B: "+num_b);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}

}
