package myjava.homework.part1;

public class StudentInformation {
	private String id;
	private String name;
	private int score;
	
	public StudentInformation()
	{
		id="";
		name="";
		score=0;
	}
	public StudentInformation(String id,String name,int score)
	{
		this.id=id;
		this.name=name;
		this.score=score;
	}
	public void setID(String a)
	{
		id=a ;
	}
	public void setName(String a)
	{
		name=a;
	}
	public void setScore(int a)
	{
		score=a;
	}
	public String getID()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public int getScore()
	{
		return score;
	}
	public void setData(String a,String b,int c)
	{
		id=a;
		name=b;
		score=c;
	}
	public void show_data()
	{
		System.out.println("Student id : "+getID());
		System.out.println("Student name : "+getName());
		if(getScore()>=60)
		{
			System.out.println("Student "+getName()+" pass this project");
		}
		else
		{
			System.out.println("Student "+getName()+" fail this project");
		}
		System.out.print("\n");
	}
}
