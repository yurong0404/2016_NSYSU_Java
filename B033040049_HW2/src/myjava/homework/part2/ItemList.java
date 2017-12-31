package myjava.homework.part2;
import java.util.*;

public class ItemList {
	private ArrayList<String> id = new ArrayList<String>();
	
	private int tempInt;
	public void addItem(String name)
	{
		id.add(name);
	}
	public void remove(String name)
	{
		tempInt=id.indexOf(name);
		if(tempInt==-1)
			System.out.println("Data not found");
		else
			id.remove(tempInt);
	}
	public void printList()
	{
		for(int i=1;i<=id.size();i++)
		{
			System.out.println(i+" : "+id.get(i-1));
		}
	}
}
