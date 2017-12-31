package myjava.homework;

import java.util.Scanner;
import java.lang.Math;

public class Prime {
	public static void main(String args[])
	{
		Scanner scan = new Scanner(System.in);
		int do_what,num,is_prime;
		do
		{
			is_prime=1;
			System.out.println("1.Check whether it's prime number");
			System.out.println("2.Find prime numbers(2~N)");
			System.out.println("3.Leave");
			do_what=scan.nextInt();
			if(do_what==1)
			{
				System.out.println("Input the number:");
				num=scan.nextInt();
				if(num>=2)
				{
					for(int i=2;i<=Math.sqrt(num);i++)
					{
						if(num%i==0)
						{
							is_prime=0;
							break;
						}
					}
					if(is_prime==1)
						System.out.println(num+" is a prime.");
					else
						System.out.println(num+" is not a prime.");
				}
				else
					System.out.println("Input error : N must equal greater than 2");
			}
			else if(do_what==2)
			{
				int count=0;
				System.out.println("Input the number:");
				num=scan.nextInt();
				if(num>=2)
				{
					System.out.println("Show the prime numbers(2 ~ "+num+")");
					for(int i=2;i<=num;i++)
					{
						is_prime=1;
						for(int x=2;x<=Math.sqrt(i);x++)
						{
							if(i%x==0)
							{
								is_prime=0;
								break;
							}
						}
						if(is_prime==1)
						{
							System.out.print(i+"\t");
							count++;
							if(count%10==0)
								System.out.print("\n");
						}
					}
				}
				else
					System.out.println("Input error : N must equal greater than 2");
			}
			else if(do_what==3)
			{
				System.out.println("Bye!!");
				break;
			}
			else
			{
				System.out.println("Input Error,please try again.");
			}
			System.out.println("\n");
		}while(do_what!=3);
	}
}
