package myjava.homework;

import java.sql.*;
import java.util.*;



public class SQLProject {
	public static void main(String[] args) 
	{
		Connection con;
		Scanner scn =new Scanner(System.in);
		String account,password;
		try
		{
			System.out.println("Please enter useraccount:"); 
			account = scn.nextLine();
			System.out.println("Please enter password:");
			password = scn.nextLine();
			//回傳資料庫連線物件
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_db?useSSL=false",account,password);
			//建立statement物件
			Statement stmt = con.createStatement();
			System.out.println("Connection establish");
			
//===========================Query 1=================================
			
			//執行SQL指令  將結果回傳到ResultSet物件
			ResultSet rs = stmt.executeQuery("SELECT id, birthmonth FROM students WHERE sex = 'M' ORDER BY birthmonth ASC");
			System.out.println("\nQuery 1 result:\n");
			System.out.printf("%13s%13s\n\n","id","birthmonth");
			//將查詢結果印出
			while(rs.next())//next() cursor跳到下一個row
			{
				//getString(n) 在rs該row的第n個column取String
				System.out.printf("%13s",rs.getString(1));
				System.out.printf("%13s",rs.getString(2));
				System.out.print("\n");
			}
			
//===========================Query 2=================================
			rs = stmt.executeQuery("SELECT id, math, chinese FROM grades WHERE math >'90' AND chinese >'80'");
			System.out.println("\nQuery 2 result:\n");
			System.out.printf("%13s%13s%13s\n\n","id","math","chinese");
			while(rs.next())
			{
				System.out.printf("%13s",rs.getString(1));
				System.out.printf("%13s",rs.getString(2));
				System.out.printf("%13s",rs.getString(3));
				System.out.print("\n");
			}
//===========================Query 3=================================
			rs = stmt.executeQuery("SELECT MAX(chinese) AS 'c_max', MIN(math) AS 'm_min',AVG(english) AS 'e_avg' FROM grades");
			System.out.println("\nQuery 3 result:\n");
			System.out.printf("%13s%13s%13s\n\n","c_max","m_min","e_avg");
			rs.next();
			System.out.printf("%13s",rs.getString(1));
			System.out.printf("%13s",rs.getString(2));
			System.out.printf("%13s",rs.getString(3));
			System.out.print("\n");
			
//===========================Query 4=================================
			//order by english DESC  是english欄由大到小排列
			rs = stmt.executeQuery("SELECT students.id, sex, english FROM students"
					+ " INNER JOIN grades ON students.id = grades.id WHERE english >'50' ORDER BY english DESC");
			System.out.println("\nQuery 4 result:\n");
			System.out.printf("%13s%13s%13s\n\n","id","sex","english");
			while(rs.next())
			{
				System.out.printf("%13s",rs.getString(1));
				System.out.printf("%13s",rs.getString(2));
				System.out.printf("%13s",rs.getString(3));
				System.out.print("\n");
			}
			
			
			
//===========================Query 5=================================
			
			rs = stmt.executeQuery("SELECT id, (math+chinese+english)/3.0 FROM grades GROUP BY id");
			System.out.println("\nQuery 5 result:\n");
			System.out.printf("%13s%13s\n\n","id","grade_avg");
			while(rs.next())
			{
				System.out.printf("%13s",rs.getString(1));
				System.out.printf("%13s",rs.getString(2));
				System.out.print("\n");
			}
			
			
//===========================Query 6=================================
			//GROUP BY 可以把相同名稱的資料group在一起   HAVING 有點像WHERE
			rs = stmt.executeQuery("SELECT birthmonth, COUNT(birthmonth) FROM students "
					+ "GROUP BY birthmonth HAVING COUNT(birthmonth) >'1'");
			System.out.println("\nQuery 6 result:\n");
			System.out.printf("%13s%13s\n\n","birthmonth","count");
			while(rs.next())
			{
				System.out.printf("%13s",rs.getString(1));
				System.out.printf("%13s",rs.getString(2));
				System.out.print("\n");
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception :"+e.toString()); 
		}
		
		scn.close();
	}
}
