/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 2, Problem 3: AugmentedStack.java
 * Student Name: Khushal Patel 
 * Student CSE account: york18
 * Student ID number: 214037618
 **********************************************************/

package A2sol;

import java.util.NoSuchElementException;
import java.util.Stack;

public class AugmentedStack<Item extends Comparable<Item>>
{
	private Item min;
	private Stack<Item> minStack = new Stack<Item>(); //Used to keep all the min elements

	/**
	 * Initializes an empty stack.
	 */
	public AugmentedStack() 
	{}
	
	/**
	 * Returns the minimum item in the stack
	 * 
	 * @param the stack you want minimum element from
	 *  @return item the minimum item from the Stack
	 */
	public Item getMin(Stack<Item> S)
	{
		return this.min;
	}

	/**
	 * Returns true if this stack is empty.
	 *
	 * @return true if this stack is empty; false otherwise
	 */
	public boolean isEmpty(Stack<Item> S)
	{
		return S.empty();
	}
	
	/**
	 * Adds the item to this stack.
	 *
	 * @param item the item to add
	 * @param S Stack you want this item to add
	 *            
	 */
	public void push(Item item, Stack<Item> S) 
	{
		if(S.isEmpty())
		{
			min = item; // empty stack so the first element is the minimum
			minStack.push(item); // also add it to minStack
		}
		else
		{
			Item MinNow = this.min;
			if(MinNow.compareTo(item) == 0) // if element is equal to min element add the current element to minStack
			{
				minStack.push(item);
			}
			if(MinNow.compareTo(item) > 0) // if element to be added is less than currentMin
			{
				this.min = item;
				minStack.push(item); // also push it to minStack
			}
		}
		S.push(item);
	}

	/**
	 * Removes and returns the item most recently added to this stack.
	 *
	 * @return the item most recently added
	 * @param S Stack you want to remove an element from
	 * @throws NoSuchElementException
	 *             if this stack is empty
	 */
	public Object pop(Stack<Item> S) 
	{
		Item i = S.peek();
		if(i.equals(this.getMin(S))) // if element we are popping is the minimum element
		{
			minStack.pop(); // remove that element from the minStack which is obviously the first element of it.
			this.min = minStack.peek(); // now the top element of the private stack minStack is the minimum element.
		}
		return S.pop();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(Stack<Item> S) 
	{
		return S.toString();
	}


	/**
	 * Returns (but does not remove) the item most recently added to this stack.
	 *
	 * @return the item most recently added to this stack
	 * @param S the Stack you want first element of
	 * @throws NoSuchElementException
	 *             if this stack is empty
	 */
	public Item top(Stack<Item> S)
	{
		return S.peek();
	}
	
	public static void main(String[] args) 
	{
        AugmentedStack<String> augStack = new AugmentedStack<String>();
        Stack<String> s = new Stack<String>();
        Stack<String> s2 = new Stack<String>();
        
        augStack.push("D", s);
        augStack.push("B", s);
        augStack.push("C", s);
        augStack.push("A", s);
        augStack.pop(s); //poping "A" so that min element in the s is "B"
        String str = "B";
        System.out.println("Testing getMin Test 1...");
        if(augStack.getMin(s).equals(str))
        {
        	System.out.println("Passed");
        }
        else
        {
        	System.out.println("Failed\nYour minimum element is: " + augStack.getMin(s));
        }
        
        System.out.println("Testing getMin Test 2...");
        augStack.push("A", s2);
        augStack.push("A", s2);
        augStack.pop(s2);
        String str2 = "A";
        if(augStack.getMin(s2).equals(str2))
        {
        	System.out.println("Passed");
        }
        else
        {
        	System.out.println("Failed\nYour minimum element is: " + augStack.getMin(s2));
        }
        
    }
}
