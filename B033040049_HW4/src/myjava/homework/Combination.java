package myjava.homework;

public class Combination extends fourStarGame{
	public void checkOfWin()
	{
		boolean win=false;
		//check all the UserNums have the same number in WinNums
		for(int i=0;i<4;i++)
		{
			win = false;
			
			//find if there is a WinNum as same as UserNum
			//check 4 times most
			for(int x=0;x<4;x++)
			{
				if(getUserNums().get(i)==getWinNums().get(x))
				{
					win = true;
					break;
				}
			}
			if(win==false)
				break;
		}
		
		//check if win
		if(win==true)
			System.out.println("**You Win!");
		else
			System.out.println("**You Lose!");
	}
}
