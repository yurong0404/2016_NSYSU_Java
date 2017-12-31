package myjava.homework.part1;

import java.util.Scanner;
public class Controller {
	public static void main(String args[])
	{
		int do_what,stdNum=0,tempInt;	//do_what要執行的指令編號   stdNum學生資料數量
		String temp;		// 用來讀輸入的字串
		StudentInformation[] std = new StudentInformation[20];	//宣告class的陣列 
		Scanner scn = new Scanner(System.in);
		do
		{
			System.out.println("Type 1 : Add a student's data(student ID,student name and score)");
			System.out.println("Type 2 : Show student's data.");
			System.out.println("Type 3 : Show all students' data.");
			System.out.println("Type 4 : Leave.");
			temp=scn.next();
			//===============防白目=======================
			try
			{
				do_what=Integer.parseInt(temp);
			}
			catch(Exception e)
			{
				do_what=0;
			}
			//================新增學生資訊==================
			if(do_what==1)
			{
				System.out.println("Add new student's data :");
				stdNum++;
				std[stdNum]=new StudentInformation();
				System.out.print("Student id:");
				temp=scn.next();			//讀入學生學號
				std[stdNum].setID(temp);	//設定學號
				System.out.print("Student name:");
				temp=scn.next();			//讀入學生名字
				std[stdNum].setName(temp);	//設定名字
				do								//防輸入錯誤
				{
					System.out.print("Student score:");
					temp=scn.next();			//讀入學生成績
					try
					{
						tempInt=Integer.parseInt(temp);
					}
					catch(Exception e)
					{
						System.out.println("Input error,try again");
						tempInt=-1;
					}
				}while(tempInt==-1);
				std[stdNum].setScore(tempInt);	//設定成績
				System.out.println("This is student "+stdNum);
			}
			//======================查學生資訊====================
			else if(do_what==2)
			{
				do
				{
					System.out.println("To show which student's information");
					temp=scn.next();
					try
					{
						tempInt=Integer.parseInt(temp);
					}
					catch(Exception e)
					{
						System.out.println("Input error,try again");
						tempInt=-1;
					}
				}while(tempInt==-1);
				
				
				
				if(tempInt<=stdNum&&tempInt>0)
				{
					System.out.println("student id:"+std[tempInt].getID());
					System.out.println("student name:"+std[tempInt].getName());
					if(std[tempInt].getScore()>=60)
						System.out.println("Student "+std[tempInt].getName()+" pass this project");
					else
						System.out.println("Student "+std[tempInt].getName()+" fail this project");
					System.out.println("This is student "+tempInt);
				}
				else
					System.out.println("Data not found.");
			}
			//===================顯示所有學生資訊===========================
			else if(do_what==3)
			{
				int pass=0,not_pass=0;
				System.out.println("====Students' data=====");
				for(int i=1;i<=stdNum;i++)
				{
					System.out.println("Number : "+i);
					std[i].show_data();
					if(std[i].getScore()>=60)
						pass++;
					else
						not_pass++;
				}
				System.out.println("=======================");
				System.out.println("Pass : "+pass);
				System.out.println("No pass : "+not_pass);
			}
			//=================結束程式======================
			else if(do_what==4)
			{
				scn.close();
				System.out.println("Bye!");
				break;
			}
			else
			{
				System.out.println("Input error,try again.");
			}
			System.out.print("\n");
		}while(do_what!=4);
	}
}
