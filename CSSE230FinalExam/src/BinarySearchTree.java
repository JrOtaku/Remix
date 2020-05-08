import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This class implements a Binary Search Tree. 
 *
 * @author wollowsk.
 */

public class BinarySearchTree <T extends Comparable<? super T>> implements Iterable<T>{

	private BinaryNode root;
	private int size;
	
	public BinarySearchTree() {
		root = null;	
		size = 0;
	}
	
	public int size(){
		return size;
	}
	
    /** 
     * This method returns true if all elements from the ArrayList are contained in the
     * tree. IMPORTANT: This method runs in linear time. You may assume that the ArrayList
     * is sorted.
     * @param c - ArrayLits to be checked for containment in this tree
     * @return true if this tree contains ALL of the elements from c, false otherwise
	*/
	public boolean containsAll(ArrayList<T> c) {
		if(c.isEmpty()) return true;
		if(root != null)
		{
			return root.containsAll(c);
		}
		return true;
	}
	
    /** 
     * This method constructs a new BinarySearchTree based on the graph passed to it.
     * The Graph has a getRoot() method. Use it to get the designated root of the graph
     * and construct a BST. You may assume that the graph is directed and has no cycles.
     * @param g - Graph to be used to construct a BST.
	*/
		public BinarySearchTree(Graph<T> g) {
//			for(int i = 0; i > g.getAllValues().size(); i++)
//			{
//				this.insert(g.getAllValues().get(i));
//			}
			ArrayList<T>add = g.getAllFromRoot();
			for(int i = 0; i < add.size(); i++)
			{
				insert(add.get(i));
			}
			//System.out.println();
		}
	
	/** Returns the least element in this set strictly greater than the given element, 
	 * or null if there is no such element. IMPORTANT: For maximum credit, you need to traverse
	 * the tree. In other words, you may not flatten the tree.
	 * @param e - the value to match
	 * @return the least element greater than e, or null if there is no such element
	*/
	public T higher(T e) {
		if(root != null) return root.higher(e, null);
		return null;
	}

	public ArrayList<T> toArrayList() {
		ArrayList<T> a = new ArrayList<T>();
		Iterator<T> i = iterator();
		while (i.hasNext()) a.add(i.next());
		return a;
	}
	
	public boolean insert(T element) {
		if (element == null) {
			throw new NullPointerException("Cannot insert null element into binary search tree");
		} else if (root == null) {
			root = new BinaryNode(element);
			size++;
			return true;
		} else {
			return root.insert(element);
		}
	}

	public String toString() {
		if (root == null) return "";
		return root.toString();
	}
	
	public String simpleToString() {
		if (root == null) return "";
		return root.simpleToString();
	}
	
	public Iterator<T> iterator(){
		return new TreeIterator();
	}

private class BinaryNode {
		
	    private T element;  
	    private BinaryNode leftChild;
	    private BinaryNode rightChild;
	    //private BinaryNode parent;
	    
	    public BinaryNode(T e) {  
			element = e;
			leftChild = rightChild = null;
	    }
	    
	    public boolean containsAll(ArrayList<T> c)
	    {
	    	if(c.isEmpty()) return true;
	    	System.out.println("ArrayList: " + c);
	    	ArrayList<T> leftArray = new ArrayList<T>();
	    	ArrayList<T> rightArray = new ArrayList<T>();
	    	int index = -1;
	    	boolean b = true;
	    	
	    	for(int i = 0; i < c.size(); i++)
	    	{
	    		if(this.element.compareTo(c.get(i)) > 0)
	    		{
	    			leftArray.add(c.get(i));
	    		}
	    		else if(this.element.compareTo(c.get(i)) < 0)
	    		{
	    			rightArray.add(c.get(i));
	    		}
	    		else
	    		{
	    			index = i;
	    		}
	    	}
	    	System.out.println("Left Array: " + leftArray);
	    	System.out.println("Right Array: " + rightArray);
	    	
	    	System.out.println("*****");
	    	System.out.println("Element: " + this.element);
	    	if(leftChild != null) System.out.println("Left Child: " + leftChild.element);
	    	if(rightChild != null)System.out.println("Right Child: " + rightChild.element);
	    	System.out.println("Left Array: " + leftArray);
	    	System.out.println("Right Array: " + rightArray);
	    	System.out.println("*****");
	    	System.out.println("!leftArray.isEmpty(): " + !leftArray.isEmpty());
	    	System.out.println("!rightArray.isEmpty(): " + !rightArray.isEmpty());
	    	if(leftChild == null && rightChild == null && (!leftArray.isEmpty() || !rightArray.isEmpty())) b = false;
	    	
	    	if(!leftArray.isEmpty())
	    	{
	    		if(leftChild != null)
	    		{
	    			b = leftChild.containsAll(leftArray);
	    		}
	    		b = false;
	    	}
	    	if(!rightArray.isEmpty())
	    	{
	    		if(rightChild != null)
	    		{
	    			b = rightChild.containsAll(rightArray);
	    		}
	    		b = false;
	    	}
	    	if(index != -1)
	    	{
	    		b = true;
	    	}
	    	System.out.println();
	    	
	    	System.out.println("b: " + b);
	    	return b;
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
//	    	if(c.isEmpty()) return true;
//	    	for(int i = 0; i > c.size(); i++)
//	    	{
//	    		if(this.element.compareTo(c.get(i)) == 0)
//		    	{
//		    		//c.remove(i);
//		    		if(c.isEmpty()) return true;
//		    		else if(this.leftChild != null && this.rightChild != null)
//		    		{
//		    			return this.leftChild.containsAll((ArrayList<T>)c.subList(0, i)) && 
//		    					this.rightChild.containsAll((ArrayList<T>)c.subList(i, c.size()));
//		    		}
//		    		else if(this.leftChild != null) 
//		    		{
//		    			return this.leftChild.containsAll((ArrayList<T>)c.subList(0, i));
//		    		}
//		    		else if(this.rightChild != null)
//		    		{
//		    			return this.rightChild.containsAll((ArrayList<T>)c.subList(i, c.size()));
//		    		}
//		    		return false;
//		    	}
//		    	else if(this.element.compareTo(c.get(0)) > 0)
//		    	{
//		    		if(this.rightChild != null)
//		    		{
//		    			return this.rightChild.containsAll(c);
//		    		}
//		    	}
//		    	return false;
//	    	}
//	    	return false;
	    }
	    
	    public T higher(T e, BinaryNode n)
	    {
	    	System.out.println(this.element);
	    	System.out.println(e);
	    	System.out.println(this.element.compareTo(e));
	    	
	    	if(this.element.compareTo(e) == 0)
	    	{
	    		System.out.println("RightChild : " + rightChild);
	    		System.out.println("Parent: " + n);
	    		if(this.rightChild != null)
	    		{
	    			return rightChild.getSmallest();
	    		}
	    		return n.element;
	    	}
	    	else if(this.element.compareTo(e) > 0)
	    	{
	    		System.out.println("In Left Child" + this.element);
	    		if(this.leftChild != null) return this.leftChild.higher(e, this);
	    		return this.element;
	    	}
	    	else if(this.element.compareTo(e) < 0)
	    	{
	    		System.out.println("In Right Child");
	    		if(this.rightChild != null) return this.rightChild.higher(e, this);
	    		//return this.element;
	    	}
	    	System.out.println();
	    	return null;
	    }
	    
	    private T getLargest() {
	    	if(rightChild != null) return rightChild.getLargest();
	    	return this.element;
	    }
	    
	    private T getSmallest() {
	    	if (leftChild != null) return leftChild.getSmallest();
	    	return this.element;
	    }
	     
	    public boolean insert(T element2) {
	 		if (element.compareTo(element2) > 0) {
				if (leftChild == null) {
					leftChild = new BinaryNode(element2);
					size++;
					return true;
				} else {
					return leftChild.insert(element2);
				}
			} 
	 		if (element.compareTo(element2) < 0) {	 		
				if (rightChild == null) {
					rightChild = new BinaryNode(element2);
					size++;
					return true;
				} else {
					return rightChild.insert(element2);
				}
			} 
	 		return false;
		}
    
	    public String toString() {
	    	String s = "[" + element + " " +
	    	 			((leftChild == null)? null : leftChild.element)+ " " +
	    	 			((rightChild == null)? null : rightChild.element)+	
	    	 			"]\n";
	    	if (leftChild != null) {
	    		s += leftChild.toString();
	    	}
	       	if (rightChild != null) {
	       		s += rightChild.toString();
	    	}	
	       	return s;
	    }
	    
	    public String simpleToString() {
	    	String s = element.toString() + " ";
	    	if (leftChild != null) {
	    		s += leftChild.simpleToString();
	    	}
	       	if (rightChild != null) {
	       		s += rightChild.simpleToString();
	    	}	
	       	return s;
	    }
	    
		public ArrayList<BinaryNode> getLeftAncestors(){
			ArrayList<BinaryNode> temp = new ArrayList<BinaryNode>();
			BinaryNode ptr = this;
			while (ptr != null) {
				temp.add(0,ptr);
				ptr = ptr.leftChild;
			}
			return temp;
		}
	}

// This is an in-order iterator
private class TreeIterator implements Iterator<T> {
	private ArrayList<BinaryNode> nodes;

	public TreeIterator(){
		nodes = new ArrayList<BinaryNode>();
		if (root != null){
			nodes.addAll(root.getLeftAncestors());
		}
	}
	public boolean hasNext() {
		return !nodes.isEmpty();
	}

	public T next() {
		if (!hasNext()) throw new NoSuchElementException();
		BinaryNode temp = nodes.remove(0);
		if (temp.rightChild != null){
			nodes.addAll(0, temp.rightChild.getLeftAncestors());
		}
		return temp.element;
	}

	public void remove() {
	}
}

}
