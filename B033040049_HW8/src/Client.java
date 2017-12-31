import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args)
	{
		//�����ӫ~���ƶq
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
			//�PServer�s�u
			skt = new Socket(IP,port);
			//�إ߻PServer�ǿ��ƪ�����
			ObjectOutputStream out = new ObjectOutputStream(skt.getOutputStream());
			//�ǻݭn���ӫ~�ƶq��Server
			out.writeInt(take_a);
			out.writeInt(take_b);
			//�S���o��function ��ƶǤ��X�h  �o�O�F����??
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
