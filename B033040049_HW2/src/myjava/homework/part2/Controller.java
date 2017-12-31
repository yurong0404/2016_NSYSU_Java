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
			//==============�s�WItem��ItemList===================
			if(doWhat==1)
			{
				System.out.print("Add item name :");
				temp=scn.next();
				list.addItem(temp);
			}
			//==============�qItemList����Item==================
			else if(doWhat==2)
			{
				System.out.print("Remove item name :");
				temp=scn.next();
				list.remove(temp);
			}
			//==============���ItemList========================
			else if(doWhat==3)
			{
				System.out.println("Show the list");
				list.printList();
			}
			//==============�����{��==============================
			else if(doWhat==4)
			{
				System.out.println("Bye!");
				break;
			}
			//==============��J���~==============================
			else
			{
				System.out.println("Input error,try again");
			}
			System.out.print("\n");
		}while(true);
		scn.close();
	}
}
