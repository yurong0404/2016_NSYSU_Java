package myjava.homework;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Server {

	public static void main(String[] args)
	{
		ServerSocket server;
		Socket clientSocket;
		
		try
		{
			server = new ServerSocket(6666);
			System.out.println("Ready to accept connection...");
			clientSocket = server.accept();
			System.out.println("Client has connected");
			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
			Connection con;
			String account,password;
			
			//====================連接database===============================
			while(true)
			{
				try
				{
					account ="";
					password="";
					account = in.readUTF();
					password = in.readUTF();
					con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/java_db", account,password);
					out.writeBoolean(true);
					out.flush();
					break;
				}
				catch(Exception e)
				{
					out.writeBoolean(false);
					out.flush();
				}
			}
			//=====================從client接收query 並且回傳結果給client===================
			String query;
			System.out.println("Ready to catch SQL query...");
			while(true)
			{
				query = in.readUTF();
				System.out.println("[SQL query] :"+query);
				//如果從client收到end指令就跳出迴圈結束程式
				if(query.equals("end"))
					break;
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				//這裡在把column標題傳給client
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
				{
					out.writeUTF(rs.getMetaData().getColumnName(i));
					if(i!=rs.getMetaData().getColumnCount())
						out.writeUTF(",");	//每隔中間要傳逗點  很重要
				}
				out.writeUTF("\r\n");
				
				//這裡在傳column的內容
				while(rs.next())
				{
					for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
					{
						out.writeUTF(rs.getString(i));
						if(i!=rs.getMetaData().getColumnCount())
							out.writeUTF(",");
					}
					out.writeUTF("\r\n");
				}
				out.writeUTF("end");
				out.flush();
			}
				
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
