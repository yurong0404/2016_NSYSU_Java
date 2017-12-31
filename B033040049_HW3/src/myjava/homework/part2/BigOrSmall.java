package myjava.homework.part2;
import java.util.*;

public class BigOrSmall {
	public static void main(String[] args)
	{
		Scanner scn = new Scanner(System.in);
		
		String temp;
		do
		{
			RaceKind.BIG=0;
			RaceKind.SMALL=0;
			System.out.println("Which game you want? (You can input big or small to play,or input exit to quit.)");
			ArrayList<Race> race =new ArrayList<Race>();
			temp=scn.next();
			if(temp.equals("big"))
			{
				RaceKind.BIG=1;
				race.add(new Race(RaceKind.BIG));
				race.get(race.size()-1).start();
			}
			else if(temp.equals("small"))
			{
				RaceKind.SMALL=2;
				race.add(new Race(RaceKind.SMALL));
				race.get(race.size()-1).start();
			}
			else if(temp.equals("exit"))
			{
				System.out.println("Game Over!!");
				
				scn.close();
				break;
			}
			else
			{
				System.out.println("You have error input.The game is failed!");
			}
		}while(temp!="big"&&temp!="small"&&temp!="exit");
	}
}
