/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 1, Problem 3: Window.java
 * Student Name:   Khushal Patel
 * Student cse account:  york18
 * Student ID number:  214037618 
 **********************************************************/


package A1;


/*
* Given an array of windows in the plane, this class counts how many overlapping and how many
* enclosing pairs of windows there are (without double counting).
 */
public class Window
{

    protected double left;
    protected double right;
    protected double bottom;
    protected double top;

    public Window(double left, double right, double bottom, double top) throws InvalidWindowException
    {
   	 this.left = left;
   	 this.right = right;
   	 this.bottom = bottom;
   	 this.top = top;
   	 if(left >= right || bottom >= top)
   	 {
   		 throw new InvalidWindowException("Invalid Window");
   	 }
    }
    
    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Window [left=" + left + ", right=" + right + ", bottom=" + bottom + ", top=" + top + "]";
	}

	/**
     * @return the left
     */
    public double getLeft()
    {
   	 return left;
    }

    /**
     * @return the right
     */
    public double getRight()
    {
   	 return right;
    }

    /**
     * @return the bottom
     */
    public double getBottom()
    {
   	 return bottom;
    }

    /**
     * @return the top
     */
    public double getTop()
    {
   	 return top;
    }

    /**
     * @param left the left to set
     * @throws InvalidWindowException
     */
    public void setLeft(double left) throws InvalidWindowException
    {
   	 this.left = left;
   	 if(left >= right)
   	 {
   		 throw new InvalidWindowException("Invalid Window");
   	 }
    }

    /**
     * @param right the right to set
     * @throws InvalidWindowException
     */
    public void setRight(double right) throws InvalidWindowException
    {
   	 this.right = right;
   	 if(left >= right)
   	 {
   		 throw new InvalidWindowException("Invalid Window");
   	 }
    }

    /**
     * @param bottom the bottom to set
     * @throws InvalidWindowException
     */
    public void setBottom(double bottom) throws InvalidWindowException
    {
   	 this.bottom = bottom;
   	 if(bottom >= top)
   	 {
   		 throw new InvalidWindowException("Invalid Window");
   	 }
    }

    /**
     * @param top the top to set
     * @throws InvalidWindowException
     */
    public void setTop(double top) throws InvalidWindowException
    {
   	 this.top = top;
   	 if(bottom >= top)
   	 {
   		 throw new InvalidWindowException("Invalid Window");
   	 }
    }
    
    /*
     * Checks if this object encloses another passed object 
     * @param w Window you want to check if it is enclosed by this object
     */
    public boolean encloses(Window w)
    {
    	if(this.left <= w.left && this.right >=  w.right && this.bottom <= w.bottom && this.top >= w.top) // condition to check if this encloses w
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    /*
     * Checks if this object overlaps another passed object 
     * @param w Window you want to check if it is overlapping
     */
    public boolean overlaps(Window w)
    {
    	if(((w.right > this.left && w.left < this.right) && (w.bottom < this.top && w.top > this.bottom))
    			|| this.encloses(w))// condition to check if this overlaps w
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    /*
     * Counts how many overlapping pairs of windows there are
     * @param windows Arrays of windows
     */
    public static int overlapCount(Window[] windows)
    {
    	boolean temp;
    	int count = 0;
    	for(int i = 0; i < windows.length ; i++)
    	{
    		for(int j = i + 1; j < windows.length; j++)
    		{
    			temp = windows[i].overlaps(windows[j]);
        		if(temp)
        		{
        			count ++;
        		}
    		}
    	}
    	return count;
    }
    
    /*
     * Counts how many enclosing pairs of windows there are
     * @param windows Arrays of windows
     */
    public static int enclosureCount(Window[] windows)
    {
    	boolean temp;
    	int count = 0;
    	for(int i = 0; i < windows.length; i++) //This loop checks windows[i] encloses windows[j] and vice versa
    	{
    		for(int j = i + 1; j < windows.length; j++)
    		{
    			temp = windows[i].encloses(windows[j]);
        		if(temp)
        		{
        			count ++;
        		}
        		temp = windows[j].encloses(windows[i]); // also check window[j] encloses windows[i]
        		if(temp)
        		{
        			count++;
        		}
    		}
    	}
    	return count;
    }
    /*
     * This class is used to throw Invalid Window Exception when a window created does not meet the
     * requirements of window
     */
    public class InvalidWindowException extends Exception
    {
   	 public InvalidWindowException()
   	 {} // no message constructor

   	 public InvalidWindowException(String msg)
   	 {
   		 super(msg); // detailed message constructor
   	 }
    }
    
    public static void main(String[] args) throws InvalidWindowException
    {
    	Window w1 = new Window(2, 3, 1, 5);
    	Window w2 = new Window(1, 3, 3, 4);
    	Window w3 = new Window(1, 3, 3, 5);
    	Window w4 = new Window(4, 6, 1, 4);
    	Window w5 = new Window(2, 3, 1, 4);
    	Window w6 = new Window(2.5, 3, 2, 3);
    	Window w7 = new Window(2, 3, 1, 5);
    	Window w8 = new Window(2, 3, 0, 1); 
    	Window[] windows = {w1,w2,w3,w4,w5,w6,w7};
    	
    	System.out.println("Testing overlap and overlapCount method" ); //it checks both the overlaps and overlapCount works since overlapCount uses overlaps method
    	System.out.println("Total Count: " + Window.overlapCount(windows));
    	String Test1 = (Window.overlapCount(windows) == 13)? "Passed" : "Failed";
    	System.out.println(Test1);
    	
    	System.out.println("Testing encloses and enclosureCount method:");
    	System.out.println("Total Count: " + Window.enclosureCount(windows));
    	String Test2 = (Window.enclosureCount(windows) == 8)? "Passed" : "Failed";
    	System.out.println(Test2);	
    	
    	System.out.println("Testing overlaps method (special case): "); //to check if two windows whose boundaries touch, overlaps
    	if(w1.overlaps(w8) == true)
    	{
    		System.out.println("Failed");
    	}
    	else
    	{
    		System.out.println("Passed");
    	}
    	
    	System.out.println("Testing encloses method (special case): "); // to check if two objects of same field encloses one another
    	if(w1.encloses(w7) && w7.encloses(w1))
    	{
    		System.out.println("Passed");
    	}
    	else
    	{
    		System.out.println("Failed");
    	}
    }
    
}


