package myjava.homework.part2;

public class Race {
	private int bigsmall;
	public Race(int a)
	{
		this.bigsmall=a;
	}
	public void start()
	{
		String[] suit={"_Club","_Dimond","_Heart","_Spade"};
		String[] rank={"_2","_3","_4","_5","_6","_7","_8","_9","_10","_J","_Q","_K","_A"};
		int p1_suit,p1_rank,com_suit,com_rank;
		int win=0;
		do
		{
			p1_rank=(int)(Math.random()*13);
			com_rank=(int)(Math.random()*13);
			p1_suit=(int)(Math.random()*4);
			com_suit=(int)(Math.random()*4);
		}while(p1_rank==com_rank&&p1_suit==com_suit);
		System.out.println("Your hand is "+suit[p1_suit]+rank[p1_rank]);
		System.out.println("Com's hand is "+suit[com_suit]+rank[com_rank]);
		if(bigsmall==1)
		{
			if(p1_rank>com_rank)
			{
				win=1;
			}
			else if(p1_rank<com_rank)
			{
				win=0;
			}
			else if(p1_rank==com_rank)
			{
				if(p1_suit>com_suit)
				{
					win=1;
				}
				else if(p1_suit<com_suit)
				{
					win=0;
				}
				else if(p1_suit==com_suit)
				{
					System.out.println("Something wrong!");
				}
			}
		}
		else if(bigsmall==2)
		{
			if(p1_rank>com_rank)
			{
				win=0;
			}
			else if(p1_rank<com_rank)
			{
				win=1;
			}
			else if(p1_rank==com_rank)
			{
				if(p1_suit>com_suit)
				{
					win=0;
				}
				else if(p1_suit<com_suit)
				{
					win=1;
				}
				else if(p1_suit==com_suit)
				{
					System.out.println("Something wrong!");
				}
			}
		}
		if(win==1)
		{
			if(bigsmall==1)
				System.out.println(suit[p1_suit]+rank[p1_rank]+" is bigger than "+suit[com_suit]+rank[com_rank]);
			else if(bigsmall==2)
				System.out.println(suit[p1_suit]+rank[p1_rank]+" is smaller than "+suit[com_suit]+rank[com_rank]);
			System.out.println("So, you win!");
		}
		else if(win==0)
		{
			if(bigsmall==1)
				System.out.println(suit[p1_suit]+rank[p1_rank]+" is smaller than "+suit[com_suit]+rank[com_rank]);
			else if(bigsmall==2)
				System.out.println(suit[p1_suit]+rank[p1_rank]+" is bigger than "+suit[com_suit]+rank[com_rank]);
			System.out.println("So, Com win!");
		}
		System.out.print("\n");
	}
}
