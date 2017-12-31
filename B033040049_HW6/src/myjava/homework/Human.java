package myjava.homework;

public abstract class Human {
	private int hp;
	private int attack;
	
	public int getHp()
	{
		return this.hp;
	}
	public void setHp(int a)
	{
		this.hp = a;
	}
	public int getAttack()
	{
		return this.attack;
	}
	public void setAttack(int a)
	{
		this.attack = a;
	}
	public abstract int action();
}
