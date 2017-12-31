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
			
			//=========輸入IP和PORT連接到server===============
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
			
			//=========輸入資料庫帳號密碼給Server讓server連上資料庫===============
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
				ifConnent = in.readBoolean();//如果server有連上資料庫會回傳true
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
			
			//==============傳SQL指令給server執行====================
			while(true)
			{
				System.out.println("Enter your SQL query");
				String query;
				query = scn.nextLine();
				out.writeUTF(query);
				out.flush();
				if(query.equals("end"))
					break;
				//建CSV檔案
				//false代表如果檔案存在 就直接複寫  , true則是加寫
				FileWriter writer = new FileWriter("result.csv",false);
				writer.append("'");//因為......
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
