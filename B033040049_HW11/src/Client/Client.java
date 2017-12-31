package Client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.TimeZone;

public class Client {
	
	public static void main(String args[])
	{
		Scanner scn = new Scanner(System.in);
		try
		{
			Socket skt = new Socket("127.0.0.1",6666);
			ObjectOutputStream out = new ObjectOutputStream(skt.getOutputStream());
			System.out.println("Input your name :");
			String name = scn.nextLine();
			out.writeUTF("Server: "+name+" in");
			out.flush();
			ObjectInputStream in = new ObjectInputStream(skt.getInputStream());
			
			Thread read = new Thread(new Runnable()
			{
				public void run()
				{
							
					String msg;
					try
					{
						while(true)
						{
							msg = in.readUTF();
							String s[];
							s = msg.split(": ");
							if(s[1].equals(name+" out!"))
								break;
							System.out.println(msg);
						}
					}
					catch(Exception e)
					{
						
					}
				}
			});
			read.start();
			
			while(true)
			{
				String msg = scn.nextLine();
				out.writeUTF(name+": "+msg);
				out.flush();
				if(msg.equals("bye"))
				{
					break;
				}
			}
		}
		catch(Exception e)
		{
			
		}
	}
}
