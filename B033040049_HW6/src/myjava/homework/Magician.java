package myjava.homework;
import java.util.*;

public class Magician extends Human implements Skill{
	

	public Magician()
	{
		setHp(50);
		setAttack(20);
		System.out.println("Role's Hp : "+getHp());
	}
	public int action()		//let user choose the skill to attack monster
	{
		Scanner scn = new Scanner(System.in);
		String choice;
		while(true)			//input until input correctly
		{
			System.out.println("choose (1)defend (2)fire (3)treatment :");
			choice = scn.nextLine();
			if(choice.equals("1"))
			{
				return 1;
			}
			else if(choice.equals("2"))
			{
				return skill_act();
			}
			else if(choice.equals("3"))
			{
				return skill_act1();
			}
			else
			{
				System.out.println("Input error,try again");
			}
		}
	}
	
	public int skill_act()
	{
		return 2;
	}
	public int skill_act1()
	{
		return 3;
	}
}
