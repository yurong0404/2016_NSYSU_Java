/**
 * 
 */
package myjava.homework;
import java.util.*;

public class Sword extends Human implements Skill{
	
	
	public Sword()
	{
		setHp(100);
		setAttack(30);
		System.out.println("Role's Hp : "+getHp());
	}
	public int action()		//let user choose the skill to attack monster
	{
		Scanner scn = new Scanner(System.in);
		String choice;
		while(true)		//input until input correctly
		{
			System.out.println("choose (1)attack (2)defend (3)attack*2 (4)powerful attack :");
			choice = scn.nextLine();
			if(choice.equals("1"))
			{
				return 1;
			}
			else if(choice.equals("2"))
			{
				return 2;
			}
			else if(choice.equals("3"))
			{
				return skill_act();
			}
			else if(choice.equals("4"))
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
		return 3;
	}
	public int skill_act1()
	{
		return 4;
	}
}
