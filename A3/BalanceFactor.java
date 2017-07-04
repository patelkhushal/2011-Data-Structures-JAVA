/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 3, Problem 2: BalanceFactor.java
 * Student Name: Khushal Patel 
 * Student CSE account: york18
 * Student ID number: 214037618
 **********************************************************/

package A3sol;

public class BalanceFactor<E> 
{
	public BalanceFactor() 
	{} // empty constructor

	/*
	 * This method recursively calculates balance factors of each node in a
	 * proper binary tree
	 * 
	 * @param LinkedBinaryTree<E> the binary tree you want to compute balance
	 * factor of
	 */
	public void printBalanceFactor(LinkedBinaryTree<E> t) {
		Position<E> root = t.root();
		findHeight(t, root);
	}

	public int findHeight(LinkedBinaryTree<E> t, Position<E> p) 
	{
		if (!(t.isInternal(p))) 
		{
			return 1;
		} 
		else
		{
			int lefth = 1 + findHeight(t, t.left(p));
			int righth = 1 + findHeight(t, t.right(p));
			int balance = Math.abs(righth - lefth); // since balance factor has to be non-negative
			System.out.println("(" + p.element() + "," + balance + ")");
			return Math.max(righth, lefth); // Interested in maximum heigth of the left and right subtrees
		}
	}

	public static void main(String[] args) 
	{
		BalanceFactor<Integer> bf = new BalanceFactor<Integer>();
		LinkedBinaryTree<Integer> l = new LinkedBinaryTree<Integer>();

		Position<Integer> root = l.addRoot(0);
		Position<Integer> left = l.addLeft(root, 1);
		Position<Integer> right = l.addRight(root, 2);
		l.addLeft(left, 3);
		l.addRight(left, 4);
		Position<Integer> rightleft = l.addLeft(right, 5);
		l.addRight(right, 6);
		l.addLeft(rightleft, 7);
		l.addRight(rightleft, 8);
		
		bf.printBalanceFactor(l);
	}

}
