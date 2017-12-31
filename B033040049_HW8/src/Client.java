import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args)
	{
		//拿走商品的數量
		int take_a,take_b;
		take_a = Integer.parseInt(args[0]);
		take_b = Integer.parseInt(args[1]);
		String IP = args[2];
		int port = Integer.parseInt(args[3]);
		
		System.out.println("\nIP : "+IP+" Port : "+String.valueOf(port));
		System.out.println("Resource requirement : <A :"+String.valueOf(take_a)+" B : "+String.valueOf(take_b)+" >");
		System.out.println("Connecting...");
		
		try
		{
			Socket skt;
			//與Server連線
			skt = new Socket(IP,port);
			//建立與Server傳輸資料的物件
			ObjectOutputStream out = new ObjectOutputStream(skt.getOutputStream());
			//傳需要的商品數量給Server
			out.writeInt(take_a);
			out.writeInt(take_b);
			//沒有這個function 資料傳不出去  這是幹嘛的??
			out.flush();
			System.out.println("\nService finished");
			skt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
