import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;
//Emily Guajardo
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
     * This method returns true if this tree contains the specified element.
     * This method runs in O(log(N)) time.
     * This method throws a NullPointerException if the specified element is null.
     * @param e - object to be checked for containment in this set
     * @return true if this set contains the specified element, false otherwise
	*/
	public boolean contains(T e) {
		if(e == null) throw new NullPointerException();
		if(root == null) return false;
		return root.contains(e);
	}

	/**
	 * Returns a view of the portion of this set whose elements range from fromElement, 
	 * inclusive, to toElement, exclusive. If fromElement and toElement are equal, the 
	 * returned set is empty. This method runs in O(N) time.
	 * @param  fromElement - low endpoint (inclusive) of the returned set
	 * @param  toElement - high endpoint (exclusive) of the returned set
	 * @return A TreeSet of the elements of the BinarySearchTree whose elements range from 
	 *         fromElement, inclusive, to toElement, exclusive
	 */
	public TreeSet<T> subSet(T fromElement, T toElement){
		if(root == null) return new TreeSet<T>();
		return root.subSet(fromElement, toElement);
	}
	
	/**
	 * This method determines if the BinarySearchTree (BST) is size-balanced. A BST
	 * if sized-balanced if every node in the tree either has the same number of elements
	 * in its left subtree and right subtree or the number of elements in its children differs 
	 * by one. An empty tree is size-balanced. This method runs in O(N) time.
	 * @return true, if the BST is size-balanced. False otherwise.
	 */
	public boolean isSizeBalanced() {
		if(root == null) return true;
		return root.isSizeBalenced();
	}
	
	private class MyBoolean {
		boolean b = true;
		
		public void setFalse() {
			b = false;
		}
		
		public boolean getValue() {
			return b;
		}
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
	    
	    public BinaryNode(T e) {  
			element = e;
			leftChild = rightChild = null;
	    }
	    
	    public boolean contains(T e)
	    {
	    	if(this.element.compareTo(e) == 0)
	    	{
	    		return true;
	    	}
	    	else if(this.element.compareTo(e) < 0)
	    	{
	    		if(this.rightChild != null)
	    		{
	    			return this.rightChild.contains(e);
	    		}
	    		else
	    		{
	    			return false;
	    		}
	    	}
	    	else
	    	{
	    		if(this.leftChild != null)
	    		{
	    			return this.leftChild.contains(e);
	    		}
	    		else
	    		{
	    			return false;
	    		}
	    	}
	    }
	    
	    public boolean isSizeBalenced()
	    {
	    	int left = 0;
	    	int right = 0;
	    	if(this.leftChild != null)
	    	{
	    		left = this.leftChild.numChildren();
	    	}
	    	if(this.rightChild != null)
	    	{
	    		right = this.rightChild.numChildren();
	    	}
	    	if(left == right || left + 1 == right || right + 1 == left)
	    	{
	    		return true;
	    	}
	    	return false;
	    }
	    
	    public int numChildren()
	    {
	    	int x = 1;
	    	if(this.rightChild != null)
	    	{
	    		x += this.rightChild.numChildren();
	    	}
	    	if(this.leftChild != null)
	    	{
	    		x += this.leftChild.numChildren();
	    	}
	    	return x;
	    }
	    
	   public TreeSet<T> subSet(T fromElement, T toElement)
	   {
		   TreeSet<T> t = new TreeSet<T>();
		   if(this.leftChild != null)
		   {
			   t.addAll(leftChild.subSet(fromElement, toElement));
		   }
		   if(this.element.compareTo(fromElement) >= 0 && this.element.compareTo(toElement) < 0) t.add(this.element);
		   if(this.rightChild != null)
		   {
			   t.addAll(rightChild.subSet(fromElement, toElement));
		   }
		   return t;
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
