package myjava.homework.part2;
import java.util.Scanner;

public class Controller {
	
	public static void main(String args[])
	{
		ItemList list = new ItemList();
		int doWhat;
		String temp;
		Scanner scn=new Scanner(System.in);
		do
		{
			System.out.println("Type 1: Add item to list");
			System.out.println("Type 2: Remove item from list");
			System.out.println("Type 3: Show the list");
			System.out.println("Type 4: Leave");
			temp=scn.next();
			try
			{
				doWhat=Integer.parseInt(temp);
			}
			catch(Exception e)
			{
				doWhat=0;
			}
			//==============新增Item到ItemList===================
			if(doWhat==1)
			{
				System.out.print("Add item name :");
				temp=scn.next();
				list.addItem(temp);
			}
			//==============從ItemList移除Item==================
			else if(doWhat==2)
			{
				System.out.print("Remove item name :");
				temp=scn.next();
				list.remove(temp);
			}
			//==============顯示ItemList========================
			else if(doWhat==3)
			{
				System.out.println("Show the list");
				list.printList();
			}
			//==============結束程式==============================
			else if(doWhat==4)
			{
				System.out.println("Bye!");
				break;
			}
			//==============輸入錯誤==============================
			else
			{
				System.out.println("Input error,try again");
			}
			System.out.print("\n");
		}while(true);
		scn.close();
	}
}
