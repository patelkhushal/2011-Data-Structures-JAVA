/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 2, Problem 1: Coins.java
 * Student Name: Khushal Patel 
 * Student CSE account: york18
 * Student ID number: 214037618
 **********************************************************/




package A2sol;

import java.util.Scanner;

public class Coins 
{

	private static int[] coins = {25, 10, 5, 1};
	private static int count = 1;
	private static int index = 0;
	private static String[] name = {"quarter", "dime", "nickel", "penny"};
	private static String[] namePlurals = {"quarters", "dimes", "nickels", "pennies"};

	public static void pay (int money) throws Exception
	{
		if(money <= 0)
		{
			throw new Exception("Enter a positive Integer!");
		}
		else
		{
			int counts[] = new int[coins.length];
		    pay(coins, counts, index, money);
		} 
	}

	private static void pay( int[] c, int[] counts, int startIndex, int totalAmount)
	{
		// firstly decide if we should proceed or not by track startIndex
		if (startIndex >= coins.length)// we have processed all coins
		{
			System.out.print("\t");
			System.out.print(count + ")");
			count++;
			// format the print out
			for (int i = 0; i < coins.length; i++)
			{
				if(counts[i] != 0) // Don't print 0
				{
					if(counts[i] == 1) // if only 1 coin required then use singular words
					{
						System.out.print(" " + counts[i] + " ");
						if(i == coins.length - 1 || counts[i + 1] == 0)
						{
							System.out.print(name[i]);
						}
						else
						{
							System.out.print(name[i] + ",");
						}
					}
					else // use plural words
					{
						System.out.print(" " + counts[i] + " ");
						if(i == coins.length - 1 || counts[i + 1] == 0)
						{
							System.out.print(namePlurals[i]);
						}
						else
						{
							System.out.print(namePlurals[i] + ",");
						}
					}
				}
			}
			// need a new line per case
			System.out.print("\n");
			return;
		}

		// other wise we need to proceed
		if (startIndex == coins.length - 1) 
		{
			if(totalAmount % coins[startIndex] == 0)//good combination
			{
			// set the counts of coins at start index
			counts[startIndex] = totalAmount / coins[startIndex];
			// proceed to recursive call
			pay(coins, counts, startIndex + 1, 0);// notice startIndex+1 and
													// remaining amount = 0
			}
		}
		else// we still have option to choose 0-N larger coins
		{
			for (int i = 0; i <= totalAmount / coins[startIndex]; i++)
			{
				// for every cycle in a loop, we choose an arbitrary number of
				// larger coins and proceed next
				counts[startIndex] = i;
				pay(coins, counts, startIndex + 1, totalAmount - coins[startIndex] * i);
				// notice we need to update the remaining amount
			}
		}
	}

	public static void main(String[] args) throws Exception
	{
	   System.out.println("Enter an amount in cents:");
	   Scanner s = new Scanner(System.in);
	   int m = s.nextInt();
	   System.out.println("This amount can be changed in the following ways:");
	   Coins.pay(m);
	   s.close();
	}
	
}
