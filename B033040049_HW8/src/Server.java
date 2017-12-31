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
			//建立Server
			server = new ServerSocket(Integer.parseInt(args[2]));
			while(true)
			{
				System.out.print("\n");
				//等待client的連線
				clientSocket = server.accept();
				client_num++;
				
				//建立接收來自client端資料的物件
				ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
				int take_a,take_b;
				//讀取需要的商品數浪
				take_a = in.readInt();
				take_b = in.readInt();
				
				//如果需求商品數量大於現有的商品數量   就不給拿
				if(take_a>num_a ||take_b>num_b)
				{
					System.out.println("[Client_"+client_num+"] : "+"resource insufficient! Error...");
					take_a=0;
					take_b=0;
				}
				//拿走商品
				else
				{
					System.out.println("[Client_"+client_num+"] : 取走商品-> A: "+take_a+" B: "+take_b);
					num_a = num_a-take_a;
					num_b = num_b-take_b;
				}
				System.out.println("	商品個數-> A: "+num_a+" B: "+num_b);
				
				//補貨機制  補到原本的數量
				System.out.println("補貨->A: "+take_a+" B: "+take_b);
				num_a = num_a + take_a;
				num_b = num_b + take_b;
				System.out.println("	商品個數-> A: "+num_a+" B: "+num_b);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}

}
