/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 2, Problem 2: Hypercube.java
 * Student Name: Khushal Patel 
 * Student CSE account: york18
 * Student ID number: 214037618
 **********************************************************/

package A2sol;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class Hypercube
{
	private Stack<Corner> walk;
	private int size;

	public Hypercube(int n) throws Exception
	{
	    if (n < 0) 
	    {
	    	throw new Exception("Please enter a positive integer");
	    } 
	    else 
	    {
	        this.size = n;
	        this.walk = new Stack<Corner>();
	    }
	}

	/*
	 * Represents a corner of the hypercube
	 */
	public static class Corner 
	{
	    private String coordinates;
	    
	    public Corner()
	    {
	        this.coordinates = new String(""); 
	    }
	}
	
	/**
	 *  
	 *  Prints one possible walk along the hypercube on n dimensions recursively.
	 */
	public void recursiveWalk()
	{
		int len = this.size;
		Corner pre = new Corner();
		recursiveWalk(pre.coordinates, len);
	}
	
	// append reverse of order n binary numbers to prefix string, and print
    public static void reverseRecursiveWalk(String prefix, int n)
    {
        if (n == 0) 
        {
        	System.out.println(prefix);
       	}
        else 
        {
            recursiveWalk(prefix + "1", n - 1);
            reverseRecursiveWalk(prefix + "0", n - 1);
        }
    }  

    // append order n binary numbers to end of prefix string, and print
    public static void recursiveWalk(String prefix, int n)
    {
        if (n == 0)
        {
        	System.out.println(prefix);
        }
        else
        {
            recursiveWalk(prefix + "0", n - 1);
            reverseRecursiveWalk(prefix + "1", n - 1);
        }
    } 
    
    /**
	 *  
	 *  Prints one possible walk along the hypercube on n dimensions iteratively.
	 */
    public void iterativeWalk()
    {
    	int len = this.size;
    	iterativeWalk(this, len);
    	for(Corner c : this.walk)
        {
        	System.out.println(c.coordinates);
        }
    }
    
    /*
     * Gives a walk along hypercube using only single queue.
     */
    private static void iterativeWalk(Hypercube h, int n)
    {
        Queue<Integer> queue = new LinkedList<Integer>();
		int num = 0;
		queue.add(0);
		for (int i = 0; i < n; i++)
		{
			int size = queue.size();
            for (int j = size - 1; j >= 0; j--)
            {
            	int k = -1;
            	if(k != j) 
            	{
            		Iterator<Integer> it = queue.iterator();
            		for(; k!= j; k++) // this loop iterates till k == j
            		{
            			num = it.next(); // keep getting the next element 
            		}
            	}
            	queue.add(num + size); // add the next elemet and size and then add it to the queue
            }
        }
		/*
		 * This for each loop conversts all the integer to its binary representation and adds it to 
		 * hypercube's corner
		 */
        for(int i : queue)
        {
        	Corner c = new Corner();
        	c.coordinates = "";
        	/*
        	 * Creates a string with length n and and adds 0's to it
        	 * For eg., if n = 3, string would be "000"
        	 */
			for(int k = 0; k < n; k++)
			{
				c.coordinates = c.coordinates + "0";
			}
			Corner cAdd = new Corner();
			cAdd.coordinates = (c.coordinates + Integer.toBinaryString(i)).substring(Integer.toBinaryString(i).length());
			h.walk.push(cAdd);
        }
    }
	
	
	
	public static void main(String[] args) throws Exception
	{
		Hypercube h1 = new Hypercube(3);
		System.out.println("Walking through a hypercube of 3D using recursiveWalk:");
		h1.recursiveWalk();
		
		System.out.println("\n");
		
		Hypercube h2 = new Hypercube(4);
		System.out.println("Walking through a hypercube of 4D using iterativeWalk:");
		h2.iterativeWalk();
		
	}

}
