package myjava.homework;
import java.util.*;

public class fourStarGame {
	private ArrayList<Integer> userNums = new ArrayList<Integer>();
	private ArrayList<Integer> winNums = new ArrayList<Integer>();
	public fourStarGame()
	{
		
	}
	public void generateUserNums()
	{
		String temp;
		String[] strArray;
		Scanner scn2 = new Scanner(System.in);
		boolean inputCorrect=true;
		do
		{
			inputCorrect = true;
			temp = scn2.nextLine();
			strArray = temp.split(" ");		//it will get 4 numbers if you input correctly
			
			if(strArray.length==4)			//if the input is not 4-part, the input is wrong
			{
				try
				{
					for(int i=0; i<4; i++)
					{
						//check if the numbers are in the correct range
						if(!(Integer.parseInt(strArray[i])>-1&&Integer.parseInt(strArray[i])<10))
						{
							inputCorrect = false;
							System.out.println("Wrong input,try again!!");
							break;
						}
					}
					
					//check if there are same numbers in UserNums 
					if(inputCorrect==true)
					{
						for(int i=0; i<strArray.length; i++)
						{
							for(int x=i+1; x<strArray.length; x++)
							{
								if(strArray[i].equals(strArray[x]))
								{
									inputCorrect = false;
									break;
								}
							}
							if(inputCorrect==false)
							{
								System.out.println("Wrong input,try again!!");
								break;
							}
						}
					}
					
					//if the range is correct and strArray.length is 4, add the numbers into ArrayList.
					if(inputCorrect==true)
					{
						for(int i=0; i<4; i++)
							userNums.add(Integer.parseInt(strArray[i]));
						break;
					}
				}
				catch(Exception e)
				{
					System.out.println("Wrong input,try again!!");
				}
			}
			else	//if the input is not 4 parts
				System.out.println("Wrong input,try again!!");
		}while(true);
	}
	public void generateWinNums()
	{
		Integer temp;
		for(int i=0;i<4;i++)
		{
			while(true)
			{
				boolean equal=false;
				temp = (int)(Math.random()*10);
				winNums.add(temp);
				for(int x=0;x<winNums.size()-1;x++)
				{
					//if the new random number is as same as the old number, label the equal as true
					if(temp==winNums.get(x))
					{
						equal=true;
						winNums.remove(winNums.size()-1);
						break;
					}
				}
				//if the new random number is not as same as the old number, break
				//or random again
				if(!equal)
					break;
			}
		}
	}
	
	//used to get UserNums
	public ArrayList<Integer> getUserNums()
	{
		return this.userNums;
	}
	
	//used to get WinNums
	public ArrayList<Integer> getWinNums()
	{
		return this.winNums;
	}

}
