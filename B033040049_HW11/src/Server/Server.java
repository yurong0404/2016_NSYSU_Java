package Server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.Vector;



public class Server {
	static Vector output = new Vector();
	Connection con;
	Statement stmt;
	ObjectOutputStream out;
	static String getTime()
	{
		SimpleDateFormat nowdate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

		//==GMT標準時間往後加八小時
		nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));

		//==取得目前時間
		String sdate = nowdate.format(new java.util.Date());
		return sdate;
	}
	
	public void go()
	{
		try
		{
			
			con = DriverManager.getConnection("jdbc:sqlite:java_db");
			stmt = con.createStatement();
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS chat"
					+ "(time String,sender String,msg String)");
			ServerSocket server = new ServerSocket(6666);
			while(true)
			{	
				Socket clientSocket = server.accept();
				out = new ObjectOutputStream(clientSocket.getOutputStream());
				output.add(out);
				
				Thread t = new Thread(new Process(clientSocket)); 
				t.start();
				
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public class Process implements Runnable
	{
		ObjectInputStream in;
		public Process(Socket clientSocket) throws IOException
		{
			in = new ObjectInputStream(clientSocket.getInputStream());
		}
		public void run()
		{
			try
			{
			    String msg;
			    Boolean history=true;
			    while(true)
			    {   
			    	msg = in.readUTF();
			    	/*if(history)
			    	{
			    		history();
			    		history=false;
			    	}*/
			    	
			    	Iterator it = output.iterator();
			    	String s[];
			    	s = msg.split(": ");
			    	if(s[1].equals("bye"))
			    	{
			    		msg = "Server: "+s[0]+" out!";
			    		msg = "["+getTime()+"] "+msg;
			    		System.out.println(msg);
				    	while(it.hasNext())
				    	{
				    		ObjectOutputStream out = (ObjectOutputStream)it.next();
				    		//System.out.println("write :"+msg);
				    		out.writeUTF(msg);
				    		out.flush();
				    	}
			    		break;
			    	}
			    	
			    	msg = "["+getTime()+"] "+msg;
			    	System.out.println(msg);
			    	insert(msg);
			    	
			    	while(it.hasNext())
			    	{
			    		ObjectOutputStream out = (ObjectOutputStream)it.next();
			    		//System.out.println("write :"+msg);
			    		out.writeUTF(msg);
			    		out.flush();
			    	}
			    }
			}
			catch(Exception e)
			{

			}
		}
	}
	
	public void insert(String msg)
	{
		try
		{
			String s[];
			s = msg.split("] ");
			String s2[];
			s2 = s[1].split(":");
			stmt.executeUpdate("INSERT INTO chat(time,sender,msg) VALUES('"+s[0]+"]','"+s2[0]+"','"+s2[1]+"');");
			//System.out.println("insert complete");
		}
		catch(Exception e)
		{
			System.out.println("something wrong");
		}
	}
	
	public void history()
	{
		ResultSet rs ;
		try
		{
			System.out.println("History");
			rs = stmt.executeQuery("SELECT *FROM chat JSON");
			/*out.writeUTF("-----History Start-----");
			out.flush();*/
			Iterator it = output.iterator();
			ObjectOutputStream out = (ObjectOutputStream)it.next();
			while(rs.next())
			{
				System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3));
				out.writeUTF(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
				out.flush();
			}
			/*out.writeUTF("-----History End-----");
			out.flush();*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		new Server().go();
		
		
	}
}
