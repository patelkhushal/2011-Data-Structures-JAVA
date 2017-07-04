/**********************************************************
 * EECS2011: Fundamentals of Data Structures,  Fall 2016
 * Assignment 3, Problem 1: CardShuffle.java
 * Student Name: Khushal Patel 
 * Student CSE account: york18
 * Student ID number: 214037618
 **********************************************************/


package A3sol;

public class CardShuffle<T>
{
	public CardShuffle() 
	{}
		
	/*
	 * This class returns the shuffled order of any given linked list
	 * @param SinglyLinkedListImp<T> linked list you want to shuffle
	 * @return SinglyLinkedListImp<T> shuffled version of the passed LinkedList, null if the size of the list is odd
	 */
	public SinglyLinkedListImp<T> cardsShuffle(SinglyLinkedListImp<T> l) //shuffles the cards as desired
	{
		if(!(l.getSize() % 2 == 0))
		{
			System.out.println("Size of list is an odd number!");
			return null;
		}
		int breakIndex = (l.getSize() / 2); //the midpoint of the list
		int j = breakIndex - 1;
		Node<T> node1 = l.head;
		Node<T> node2 = node1.next;
		Node<T> node3 = node1;
		Node<T> node4 = node1;
		
		for(int i = 0; i < j; i++)
		{
			node3 = node3.next;
			node4 = node3.next;
		}
		
		for(int i = 0; i < j; i++)
		{
			node1.next = node4;
			node3.next = node4.next;
			node4.next = node2;
			
			node1 = node2;
			node2 = node1.next;
			node4 = node3.next;
		}
		return l;
	}
	
	public static void main(String[] args)
	{
		CardShuffle<Integer> c = new CardShuffle<Integer>();
		SinglyLinkedListImp<Integer> l = new SinglyLinkedListImp<Integer>();
		for(int i = 1; i < 31; i++)
		{
			l.add(i);
		}
		System.out.println(l);
		System.out.println(c.cardsShuffle(l));
	}
	
	public static class SinglyLinkedListImp<T>
	{
	    private Node<T> head;
	    private Node<T> tail;
	    private int size = 0;
	    
	    public SinglyLinkedListImp()
	    {}
	     
	    public String toString() 
	    {
            String result = "";
            Node<T> current = head;
            result += current.value + ", ";
            for(int i = 0; current.getNext() != null; i++)
            {
                current = current.getNext();
                if(i == this.getSize() - 2)
                {
                	result += current.value;
                }
                else
                {
                	result += current.value + ", ";
                }
            }
            return "{" + result + "}";
	    }
	    
	    public void add(T element)
	    {
	         
	        Node<T> nd = new Node<T>();
	        nd.setValue(element);
	        /**
	         * check if the list is empty
	         */
	        if(head == null)
	        {
	            //since there is only one element, both head and
	            //tail points to the same object.
	            head = nd;
	            tail = nd;
	        } else 
	        {
	            //set current tail next link to new node
	            tail.setNext(nd);
	            //set tail as newly created node
	            tail = nd;
	        }
	        size ++;
	    }
	     
	    public void addAfter(T element, T after)
	    {
	         
	        Node<T> tmp = head;
	        Node<T> refNode = null;
	       
	        /**
	         * Traverse till given element
	         */
	        while(true)
	        {
	            if(tmp == null)
	            {
	                break;
	            }
	            if(tmp.compareTo(after) == 0)
	            {
	                //found the target node, add after this node
	                refNode = tmp;
	                break;
	            }
	            tmp = tmp.getNext();
	        }
	        if(refNode != null)
	        {
	            //add element after the target node
	            Node<T> nd = new Node<T>();
	            nd.setValue(element);
	            nd.setNext(tmp.getNext());
	            if(tmp == tail){
	                tail = nd;
	            }
	            tmp.setNext(nd);
	            size++;
	        } 
	        else 
	        {
	            System.out.println("Unable to find the given element...");
	        }
	    }
	     
	    public T deleteFront()
	    {
	         
	        if(head == null)
	        {
	            System.out.println("Underflow...");
	        }
	        Node<T> tmp = head;
	        head = tmp.getNext();
	        if(head == null)
	        {
	            tail = null;
	        }
	        size--;
	        return tmp.getValue();
	    }
	     
	    public T deleteAfter(T after)
	    {
	         
	        Node<T> tmp = head;
	        Node<T> refNode = null;
	        System.out.println("Traversing to all nodes..");
	        /**
	         * Traverse till given element
	         */
	        while(true){
	            if(tmp == null){
	                break;
	            }
	            if(tmp.compareTo(after) == 0){
	                //found the target node, add after this node
	                refNode = tmp;
	                break;
	            }
	            tmp = tmp.getNext();
	        }
	        if(refNode != null)
	        {
	            tmp = refNode.getNext();
	            refNode.setNext(tmp.getNext());
	            if(refNode.getNext() == null)
	            {
	                tail = refNode;
	            }
	            size--;
	            return tmp.getValue();
	        } 
	        else 
	        {
	            return null;
	        }
	    }
	     
	    public void traverse()
	    {
	        Node<T> tmp = head;
	        while(true){
	            if(tmp == null){
	                break;
	            }
	            tmp = tmp.getNext();
	        }
	    }
	    
	    private int getSize()
	    {
	    	return this.size;
	    }
	}
	
	public static class Node<T> implements Comparable<T> 
	{ 
	    private T value;
	    private Node<T> next;
	     
	    public T getValue() {
	        return value;
	    }
	    public void setValue(T value) {
	        this.value = value;
	    }
	    public Node<T> getNext() {
	        return next;
	    }
	    public void setNext(Node<T> ref) {
	        this.next = ref;
	    }
	    @Override
	    public int compareTo(T arg)
	    {
	        if(arg == this.value)
	        {
	            return 0;
	        } else 
	        {
	            return 1;
	        }
	    }
	}

}


