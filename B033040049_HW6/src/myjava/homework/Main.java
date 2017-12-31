package myjava.homework;
import java.util.*;


public class Main {

	public static void main(String[] args) 
	{
		int monster_hp,monster_dmg;
		String choice;
		Scanner scn = new Scanner(System.in);
		
		while(true)			//game repeatedly
		{
			Human role;
			monster_hp = (int)(Math.random()*151+150);
			System.out.println("Monster's HP : "+monster_hp);
			while(true)		//input repeatedly until input correctly
			{
				System.out.println("Choose your character(by default Sword) (1)Sword (2)Magician");
				choice = scn.nextLine();
				if(choice.equals("1"))		//if choose Sword
				{
					role = new Sword();
					int x;				//used to receive role.action()
					while(role.getHp()>0 && monster_hp>0)	//if no one dies,keep fighting.
					{
						x=role.action();
						if(x==1)		//attack
						{
							monster_hp = monster_hp - role.getAttack();
							System.out.println("Role's damage:"+role.getAttack());
							monster_dmg = (int)(Math.random()*40);
							System.out.println("Monster's damage:"+monster_dmg);
							role.setHp(role.getHp()-monster_dmg);
						}
						else if(x==2)		//defend
						{
							System.out.println("Defend all the damage");
							monster_dmg = (int)(Math.random()*40);
							System.out.println("Monster's damage:"+monster_dmg);
						}
						else if(x==3)		//attack*2
						{
							role.setAttack(role.getAttack()*2);
							System.out.println("Now,your damage:"+role.getAttack());
							monster_dmg = (int)(Math.random()*40);
							System.out.println("Monster's damage:"+monster_dmg);
							role.setHp(role.getHp()-monster_dmg);
						}
						else if(x==4)		//powerful attack
						{
							int full_attack;
							full_attack = (int)(Math.random()*350+100);
							System.out.println("Power!!Role's damage :"+full_attack);
							monster_hp = monster_hp - full_attack;
							role.setAttack(0);
							role.setHp(1);
						}
						else
							System.out.println("Something wrong");
						if(role.getHp()<0)
							role.setHp(0);
						if(monster_hp<0)
							monster_hp = 0;
						System.out.println("Role's Hp:"+role.getHp());
						System.out.println("Monster's Hp:"+monster_hp);
					}//end of while(both hp>0)
					
					
					if(role.getHp()==0&&monster_hp==0)
						System.out.println("Deuce");
					else if(role.getHp()==0)
						System.out.println("Lose");
					else if(monster_hp==0)
						System.out.println("Win");
					System.out.println("\n");
					break; //break the while until input correctly
				}
				
				else if(choice.equals("2"))		//if choose Magician,(code 80% like Sword)
				{
					role = new Magician();
					int x;
					while(role.getHp()>0 && monster_hp>0)
					{
						x=role.action();
						if(x==1)
						{
							System.out.println("Defend all the damage");
							monster_dmg = (int)(Math.random()*40);
							System.out.println("Monster's damage:"+monster_dmg);
						}
						else if(x==2)
						{
							monster_hp = monster_hp - (int)((double)role.getAttack()*3.5);
							System.out.println("Role's damage:"+(int)((double)role.getAttack()*3.5));
							monster_dmg = (int)(Math.random()*40);
							System.out.println("Monster's damage:"+monster_dmg);
							role.setHp(role.getHp()-monster_dmg);
						}
						else if(x==3)
						{
							int treatment;
							treatment = (int)(Math.random()*30+1);
							System.out.println("HP increase :"+treatment);
							monster_dmg = (int)(Math.random()*40);
							System.out.println("Monster's damage:"+monster_dmg);
							role.setHp(role.getHp()-monster_dmg+treatment);
						}
						else
							System.out.println("Something wrong");
						if(role.getHp()<0)
							role.setHp(0);
						if(monster_hp<0)
							monster_hp = 0;
						System.out.println("Role's Hp:"+role.getHp());
						System.out.println("Monster's Hp:"+monster_hp);
					}
					if(role.getHp()==0&&monster_hp==0)
						System.out.println("Deuce");
					else if(role.getHp()==0)
						System.out.println("Lose");
					else if(monster_hp==0)
						System.out.println("Win");
					System.out.println("\n");
					break;
				}
				else
				{
					System.out.println("Input error,try again!");
				}
			}
			
			
		}
	}
}
