package myjava.homework;

import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class Client {

	public static void main(String[] args)
	{
		Socket socket;
		Scanner scn =new Scanner(System.in);
		try
		{
			String address,port;
			
			//=========��JIP�MPORT�s����server===============
			while(true)
			{
				System.out.println("Address :");
				address = scn.nextLine();
				System.out.println("Port :");
				port = scn.nextLine();
				try
				{
					socket = new Socket(address,Integer.parseInt(port));
					break;
				}
				catch(Exception e)
				{
					System.out.println("Address or Port not correct,try again");
				}
			}
			
			//=========��J��Ʈw�b���K�X��Server��server�s�W��Ʈw===============
			String account,password;
			ObjectOutputStream out;
			ObjectInputStream in;
			out = new ObjectOutputStream(socket.getOutputStream());
			in =  new ObjectInputStream(socket.getInputStream());
			while(true)
			{
				account="";
				password="";
				System.out.println("HSQL Database account :");
				account = scn.nextLine();
				System.out.println("HSQL Database password :");
				password = scn.nextLine();
					
				
				out.writeUTF(account);
				out.writeUTF(password);
				out.flush();
				boolean ifConnent;
				ifConnent = in.readBoolean();//�p�Gserver���s�W��Ʈw�|�^��true
				if(ifConnent==true)
				{
					System.out.println("Connection to database success.");
					break;
				}
				else
				{
					System.out.println("Account or password not correct,try again.");
				}
			}
			
			//==============��SQL���O��server����====================
			while(true)
			{
				System.out.println("Enter your SQL query");
				String query;
				query = scn.nextLine();
				out.writeUTF(query);
				out.flush();
				if(query.equals("end"))
					break;
				//��CSV�ɮ�
				//false�N��p�G�ɮצs�b �N�����Ƽg  , true�h�O�[�g
				FileWriter writer = new FileWriter("result.csv",false);
				writer.append("'");//�]��......
				while(true)
				{
					String result = in.readUTF();
					if(result.equals("end"))
						break;
					writer.append(result);
				}
				writer.flush();
				writer.close();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		scn.close();
	}
}
