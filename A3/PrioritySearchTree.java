/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 3, Problem 3: PrioritySearchTree.java
 * Student Name: Khushal Patel 
 * Student CSE account: york18
 * Student ID number: 214037618
 **********************************************************/


package A3sol;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


public class PrioritySearchTree<E> 
{
	private ArrayList<Point> points = new ArrayList<Point>();
	
	public PrioritySearchTree() // constructor
	{
		this.points = new ArrayList<Point>();
	} 
	
	
	
	/*
	 * Prints the binary tree in the format root-right-subtree-left subtree
	 * 
	 * Takes O(n) time, since it visits all the nodes of the tree once 
	 * @param LinkedBinaryTree<Point> the tree you want to print
	 * @param Position<Point> P the position from where you want the tree to be printed, ususally root 
	 */
	public void printTree(LinkedBinaryTree<Point> t, Position<Point> p)
	{
		System.out.println(p.element());
		if(t.right(p) != null)
		{
			printTree(t, t.right(p));
		}
		if(t.left(p) != null)
		{
			printTree(t, t.left(p));
		}
	}
	
	
	/*
	 * Fills all the null nodes of a binary tree as per the requirement in question 3 of asignment 3
	 * @param LinkedBinaryTree<Point> the tree you want to fill nodes of
	 * 
	 */
	private void fillNodes(LinkedBinaryTree<Point> t)
	{
		getExternal(t, t.root());
		ArrayList<Point> sorted = new ArrayList<Point>();
		sorted = sortArr(this.getPoints());
		fillNodes(sorted, t);
	}
	
	/*
	 * Gets all the external points of the binary tree and stores it in an ArrayList<Point>
	 * @param LinkedBinaryTree<Point> binary tree you want external points of
	 * @param Position<Point> position of the node
	 */
	public void getExternal(LinkedBinaryTree<Point> t, Position<Point> p)
	{
		if (t.isExternal(p))
		{
			Point point = p.element();
			points.add(point);
		} 
		else
		{
			getExternal(t, t.left(p));
			getExternal(t, t.right(p));
		}
	}
	
	/*
	 * Sorts an array using Comparator and Priority queue
	 * @param ArrayList<Point> the array you want to sort
	 * @return ArrayList<Point> sorted ArrayList
	 */
	public ArrayList<Point> sortArr(ArrayList<Point> array)
	{
		ArrayList<Point> sorted = new ArrayList<Point>();
		Comparator<Point> comparator = new Point();
		PriorityQueue<Point> queue = new PriorityQueue<Point>(array.size(), comparator);
		for(Point p : array)
		{
			queue.add(p);
		}
		while(true){
			Point maxY = queue.poll();
			if(maxY == null) break;
			sorted.add(maxY);
		}
		return sorted;
	}
	/*
	 * fills up all the node in a tree as specified in the assignment question
	 * @param ArrayList<Point> the sorted array in decreasing y cordinates
	 * @param LinkedBinaryTree<Point>  the tree you want to fill nodes of
	 */
	private void fillNodes(ArrayList<Point> array, LinkedBinaryTree<Point> t)
	{
		if(t.root.element() == null)
		{
			t.set(t.root(), array.get(0));
			array.remove(0); //remove(int index) takes O(n) time in the worst case but takes O(1) time in the best case and removing at index 0 is always O(1)
		}
		fillNodes(array, t, t.right(t.root()));
		fillNodes(array, t, t.left(t.root()));
	}
	
	/*
	 * This method recursively fills up the nodes of a binary tree
	 * @param ArrayList<Point> the sorted array in decreasing y cordinates
	 * @param LinkedBinaryTree<Point>  the tree you want to fill nodes of
	 */
	private void fillNodes(ArrayList<Point> array, LinkedBinaryTree<Point> t, Position<Point> p)
	{
		if(t.isInternal(p) && array.size() > 0)
		{
			if(t.isExternal(t.right(p)) && 
					!(t.right(p).element().equals(array.get(0))|| t.left(p).element().equals(array.get(0))))
			{
				// don't do anything since the node's value has to either one of its external nodes
			}
			else
			{
				t.set(p, array.get(0));
				array.remove(0); //remove(int index) takes O(n) time in the worst case but takes O(1) time in the best case and removing at index 0 is always O(1)
				fillNodes(array, t, t.right(p));
				fillNodes(array, t, t.left(p));
			}
		}
	}
	
	/*
	 * @return ArrayList<Points> the field of this object ArrayList<Point>
	 */
	public ArrayList<Point> getPoints() 
	{
		return points;
	}
	
	/*
	 * This class implements a 2-Dimensional Point in a space
	 */
	public static class Point implements Comparator<Point>{
		private int x;
		private int y;
		
		
		public Point()
		{}
		public Point(int x1, int y1)
		{
			this.x = x1;
			this.y = y1;
		}
		

		public int compare(Point p1, Point p2) throws ClassCastException
		{
			int xa, ya, xb, yb;
			xa = ((Point) p1).getX();
			ya = ((Point) p1).getY();
			xb = ((Point) p2).getX();
			yb = ((Point) p2).getY();
			if (ya != yb)
				return (yb - ya);
			else
				return (xb - xa);
		}
		
		

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}

		/**
		 * @return the x
		 */
		public int getX() {
			return x;
		}

		/**
		 * @param x
		 *            the x to set
		 */
		public void setX(int x) {
			this.x = x;
		}

		/**
		 * @return the y
		 */
		public int getY() {
			return y;
		}

		/**
		 * @param y
		 *            the y to set
		 */
		public void setY(int y) {
			this.y = y;
		}

	}

	public static void main(String[] args) 
	{
		PrioritySearchTree<Point> pst = new PrioritySearchTree<Point>();
		LinkedBinaryTree<Point> t = new LinkedBinaryTree<Point>();

		Point p1 = new Point(-8, 3);
		Point p2 = new Point(-7, 1);
		Point p3 = new Point(-1, 6);
		Point p4 = new Point(2, 4);
		Point p5 = new Point(4, 8);
		Point p6 = new Point(5, 9);
		Point p7 = new Point(7, 1);
		Point p8 = new Point(9, 7);

		Position<Point> root = t.addRoot(null);
		Position<Point> left = t.addLeft(root, null);
		Position<Point> right = t.addRight(root, null);
		Position<Point> leftleft = t.addLeft(left, null);
		Position<Point> leftright = t.addRight(left, null);
		t.addLeft(leftleft, p1);
		t.addRight(leftleft, p2);
		t.addLeft(leftright, p3);
		t.addRight(leftright, p4);

		Position<Point> rightleft = t.addLeft(right, null);
		Position<Point> rightright = t.addRight(right, null);
		t.addLeft(rightleft, p5);
		t.addRight(rightleft, p6);
		t.addLeft(rightright, p7);
		t.addRight(rightright, p8);

		pst.fillNodes(t);
		pst.printTree(t, t.root());
		
	}

}
