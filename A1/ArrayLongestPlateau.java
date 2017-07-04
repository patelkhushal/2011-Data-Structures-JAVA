/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 1, Problem 2: ArrayLongestPlateau.java
 * Student Name:   Khushal Patel
 * Student cse account:  york18
 * Student ID number:  214037618
 **********************************************************/

package A1;

/**
 * The purpose of this class is to find the longest plateau of an array of ints. 
 * 
 * The main method runs some tests.
 * 
 * @author andy
 * 
 */

public class ArrayLongestPlateau {

  	/**
	 * longestPlateau() returns the longest plateau of an array of ints.
	 * 
	 * @return an array int[3] of the form {value, start, len} representing the longest plateau of
	 *         ints[] as a length len contiguous subarray starting at index start with common
	 *         element values value.
	 * 
	 *         For example, on the input array [2, 3, 3, 3, 3, 6, 6, 1, 1, 1], it returns [6, 5, 2],
	 *         indicating the longest plateau of this array is the subarray [6, 6]; it starts at
	 *         index 5 and has length 2.
	 * 
	 * @param ints
	 *            the input array.
	 */

	public static int[] longestPlateau(int[] ints)
	{
		final int noDuplicateFreq = 1;	// since no duplicates, frequency = 1
		int value = ints[0]; // initial value is the first element of ints
		int start = 0;
		int frequency = 1; 
		int bestFrequency = 0;
		int index = 0; // dummy variable created to take the value i + 1 in the first for loop
		int lastValue = ints[0]; // to keep track of previous elements 
		int lastIndex = 0;	
		boolean condition = false; // condition that satisfies longest plateau when there are repeated integers
		int max = ints[0];
		
		for(int i = 0; i < ints.length - 1; i++) //This loops traverses all the elements in ints except when length of ints is 1
		{
			if(ints[i] == ints[i + 1]) 
			{
				frequency++; // adds one since it spotted a same number
				index = i + 1; 
				if((i == 0 || lastValue <= ints[i]) && (index == ints.length - 1
						|| ints[index] > ints[index + 1]) && frequency > bestFrequency) // condition for having a longest plateau. Also checking the frequency to ensure that we have the longest sub-array
				{
					condition = true;
					if(lastIndex != 0) // lastIndex is assigned to 0 initially so if sub-array that satisfies condition of plateau starts at index 0 (i.e the first element), no need to change lastIndex
					{
						start = lastIndex + 1;
					}
					bestFrequency = frequency;
					value = ints[i];
				}
			}
			else
			{
				lastValue = ints[i];
				lastIndex = i;
				frequency = 1;
			}
		}
		
		for (int i = 1; !condition && i < ints.length && ints.length > 1; i++) // this loop finds the longest plateau when there are no duplicate (repeated) integers in ints
		{
			if (Math.max(max, ints[i]) > max) // to find the greatest element in ints
			{
				max = ints[i];
				value = max;
				start = i;
				bestFrequency = noDuplicateFreq; // since no duplicates, frequency = 1												
			} 
			else 
			{
				value = max;
				bestFrequency = noDuplicateFreq;
			}
		}
		if (ints.length == 1) // to handle special case. When length of ints is 1
		{
			bestFrequency = noDuplicateFreq;
		}
		
		int[] result = {value, start, bestFrequency};
		return result;
	}


  	/**
	 * main() runs test cases on your longestPlateau() method. Prints summary
	 * information on basic operations and halts with an error (and a stack
	 * trace) if any of the tests fail.
	 */

	public static void main(String[] args) {
		String result;

		System.out.println("Let's find longest plateaus of arrays!\n");

		int[] test1 = { 4, 1, 1, 6, 6, 6, 6, 1, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test1) + ":");
		result = TestHelper.stringInts(longestPlateau(test1));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 6 , 3 , 4 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test2 = { 3, 3, 1, 2, 4, 2, 1, 1, 1, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test2) + ":");
		result = TestHelper.stringInts(longestPlateau(test2));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals(
		  "[ 3 , 0 , 2 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");
		
		int[] test3 = { 3, 3, 1, 2, 4, 0, 1, 1, 1, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test3) + ":");
		result = TestHelper.stringInts(longestPlateau(test3));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 1 , 6 , 4 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test4 = { 3, 3, 3, 4, 1, 2, 4, 4, 0, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test4) + ":");
		result = TestHelper.stringInts(longestPlateau(test4));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 4 , 6 , 2 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test5 = { 7, 7, 7, 7, 9, 8, 2, 5, 5, 5, 0, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test5)
				+ ":");
		result = TestHelper.stringInts(longestPlateau(test5));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 5 , 7 , 3 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test6 = { 4 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test6) + ":");
		result = TestHelper.stringInts(longestPlateau(test6));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 4 , 0 , 1 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test7 = { 4, 4, 4, 5, 5, 5, 6, 6 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test7) + ":");
		result = TestHelper.stringInts(longestPlateau(test7));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 6 , 6 , 2 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");

		System.out.println("\nAdditional tests done by the student or TA:\n");

		// Insert your additional test cases here.
		int[] test8 = { 4, 2 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test8) + ":");
		result = TestHelper.stringInts(longestPlateau(test8));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 4 , 0 , 1 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");
		
		int[] test9 = { 2, 4 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test9) + ":");
		result = TestHelper.stringInts(longestPlateau(test9));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 4 , 1 , 1 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");
		
		int[] test10 = { 1,2,3,4,4,5,6 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test10) + ":");
		result = TestHelper.stringInts(longestPlateau(test10));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 6 , 6 , 1 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");
		
		int[] test11 = {1,6,3,8,8,4,5,5,5,1,9,6,7,7};
		System.out.println("longest plateau of " + TestHelper.stringInts(test11) + ":");
		result = TestHelper.stringInts(longestPlateau(test11));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 5 , 6 , 3 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");
		
		int[] test12 = {3,3,3,3,1,8,2,5,5};
		System.out.println("longest plateau of " + TestHelper.stringInts(test12) + ":");
		result = TestHelper.stringInts(longestPlateau(test12));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 3 , 0 , 4 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");
		
		int[] test13 = {3,3,4,5,5,6,1};
		System.out.println("longest plateau of " + TestHelper.stringInts(test13) + ":");
		result = TestHelper.stringInts(longestPlateau(test13));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 6 , 5 , 1 ]"),
				"Wrong: that's not the longest plateau!!!  No chocolate.");

	}
}