package myjava.homework.part1;

public class ARGSomething {
		int avg(int...a)
		{
			int sum=0;
			for(int i=0;i<a.length;i++)
			{
				sum=sum+a[i];
			}
			return sum=sum/a.length;
		}
		
		double avg(double...a)
		{
			double sum=0;
			for(int i=0;i<a.length;i++)
			{
				sum=sum+a[i];
			}
			return sum=sum/a.length;
		}
		
		static double avg(String...a)
		{
			System.out.print("The number of these words is ");
			double sum=0;
			for(int i=0;i<a.length;i++)
			{
				sum=sum+a[i].length();
			}
			return sum/a.length;
		}
		static int avg(String a)
		{
			System.out.print("The number of this word is ");
			return a.length();
		}
		
		static double avg(Apple...apple)
		{
			System.out.print("The weight per dollar is ");
			double sumPrice=0,sumWeight=0;
			for(int i=0;i<apple.length;i++)
			{
				sumPrice=sumPrice+apple[i].getPrice();
				sumWeight=sumWeight+apple[i].getWeight();
			}
			return sumWeight/sumPrice;
		}
	
	public static void main(String[] args) {
		// You cannot modify anything in the main function!!!!
		ARGSomething t=new ARGSomething();
		System.out.println(t.avg(10,20));
		System.out.println(t.avg(20,60,120));
		System.out.println(t.avg(10,20,30,40));
		
		System.out.println(t.avg(0.1,0.2));
		System.out.println(t.avg(20,60.0,120));
		System.out.println(t.avg(10,20,new Integer(30),40.0));
		
		System.out.println(avg("Apple"));
		System.out.println(avg("Apple","Cat"));
		System.out.println(avg("Apple","Cat",new String("Bee")));
		
		System.out.println(avg(new Apple(10,59.5)));
		System.out.println(avg(new Apple(10,59.5),new Apple("Washington",30,80.3)));
		Apple green=new Apple("Granny Smith",30,88);
		System.out.println(avg(new Apple(10,59.5),new Apple("Washington",30,80.3),green));
	}

}
