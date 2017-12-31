package myjava.homework;

public class Permutation extends fourStarGame{
	public void checkOfWin()
	{
		boolean win = true;
		for(int i=0;i<4;i++)
		{
			//once the UserNum is not equal to WinNums, go lose
			if(getUserNums().get(i)!=getWinNums().get(i))
			{
				win = false;
				break;
			}
		}
		
		//check if win
		if(win==true)
			System.out.println("**You Win!");
		else
			System.out.println("**You Lose!");
	}
}
