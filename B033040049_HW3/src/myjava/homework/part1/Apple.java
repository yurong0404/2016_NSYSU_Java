package myjava.homework.part1;

public class Apple {
	
	private String variety;
	private int price;
	private double weight;
	Apple(int a,double b)
	{
		setPrice(a);
		setWeight(b);
	}
	Apple(String a,int b,double c)
	{
		setVariety(a);
		setPrice(b);
		setWeight(c);
	}
	public int getPrice()
	{
		return price;
	}
	protected void setPrice(int a)
	{
		this.price = a;
	}
	public double getWeight()
	{
		return this.weight;
	}
	public void setWeight(double a)
	{
		this.weight = a;
	}
	protected void setVariety(String a)
	{
		this.variety= a;
	}
	public String getVariety()
	{
		return this.variety;
	}
}
