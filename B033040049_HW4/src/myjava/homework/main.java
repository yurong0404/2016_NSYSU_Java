package myjava.homework;
import java.util.*;

public class main {
	public static void main(String[] args)
	{
		String doWat;
		Scanner scn = new Scanner(System.in);
		ArrayList<Permutation> gameP= new ArrayList<Permutation>();
		ArrayList<Combination> gameC= new ArrayList<Combination>();
		
		
		//input repeat
		do
		{
			System.out.println("Choose your Four Star Game type : (1:Permutation 2:Combination 3:Exit)");
			doWat = scn.next();
			if(doWat.equals("1"))
			{
				gameP.add(new Permutation());
				gameP.get(gameP.size()-1).generateWinNums();		//run generateWinNums function
				System.out.print("Win Numbers : ");
				for(int i=0;i<4;i++)
					System.out.print(gameP.get(gameP.size()-1).getWinNums().get(i)+" ");//print out the win numbers
				System.out.print("\n");
				System.out.print("Input four user numbers : ");
				gameP.get(gameP.size()-1).generateUserNums();		//run generateUserNums function
				gameP.get(gameP.size()-1).checkOfWin();				//check if win
			}
			else if(doWat.equals("2"))								//do the almost same things above
			{
				gameC.add(new Combination());
				gameC.get(gameC.size()-1).generateWinNums();
				System.out.print("Win Numbers : ");
				for(int i=0; i<4; i++)
					System.out.print(gameC.get(gameC.size()-1).getWinNums().get(i)+" ");
				System.out.print("\n");
				System.out.print("Input four user numbers : ");
				gameC.get(gameC.size()-1).generateUserNums();
				gameC.get(gameC.size()-1).checkOfWin();
			}
			else if(doWat.equals("3"))				//exit
			{
				scn.close();
				System.out.println("Game Over!!");
			}
			else								//if not 1,2 or3 ,wrong input
			{
				System.out.println("Wrong input,try again!");
			}
		}while(!doWat.equals("3"));
	}
}
