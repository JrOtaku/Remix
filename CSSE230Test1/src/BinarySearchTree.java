import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;


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
	 * This method adds all elements from the collection 
	 * to this tree. 
	 *  
	 * @return True if the BST changed as a result of 
	 * this method and false otherwise.
	 */
	public boolean addAll(Collection<T> a) {
		Object[] array = a.toArray();
		MyBoolean b = new MyBoolean();
		for(int i = 0; i < a.size(); i++)
		{
			b.value = this.insert((T) array[i]);
		}
		return b.value;
	}
	
	/**
	 * This method determines whether the BinarySearchTree forms a
	 * "ladder". By this we mean each node has at most one child.
	 * Additionally, the left and right children alternate as you traverse
	 * the tree. This method runs in linear time. An empty tree is trivially
	 * a ladder. 
	 *  
	 * @return True if the BST forms a "ladder" and false otherwise.
	 */
	public boolean isLadder() {
		if(root != null)
		{
			return root.isLadder();
		}
		return true;
	}

	/**
	 * This method returns an Object array of the elements in the tree. The 
	 * elements appear in the array in the same order as in the BST. For full 
	 * credit, you must implement a recursive (helper) method in the BinaryNode 
	 * class and are not permitted to use the iterator or an ArrayList 
	 * as part of implementing this method. This method runs in linear time. 
	 *  
	 * @return An Object array of the elements in the tree, in order.
	 */	
	public Object[] toArray(){
		Object[] array = new Object[this.size];
		if(root != null) return root.toArray();
		return array;
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
	    private MyBoolean isRightChild;
	    
	    public BinaryNode(T e) {  
			element = e;
			leftChild = rightChild = null;
			isRightChild = new MyBoolean();
	    }
	    
	    public boolean isLadder()
	    {
	    	if(rightChild != null)
	    	{
	    		rightChild.isRightChild.value = true;
	    	}
	    	if(leftChild != null && rightChild != null)
	    	{
	    		return false;
	    	}
	    	if(leftChild == null && rightChild == null)
	    	{
	    		return true;
	    	}
	    	if(this.isRightChild.value)
	    	{
	    		if(leftChild != null)
	    		{
	    			return (leftChild.isLadder());
	    		}
	    		return(false);
	    	}
	    	if(!this.isRightChild.value)
	    	{
	    		if(rightChild != null)
	    		{
	    			return (rightChild.isLadder());
	    		}
	    		return(false);
	    	}
	    	return true;
	    	
	    }
	    
//	    public Object[] toArray()
//	    {
//	    	if(leftChild == null && rightChild == null)
//	    	{
//	    		Object[] a = new Object[1];
//	    		a[0] = element;
//	    		System.out.println(a.toString());
//	    		return a;
//	    	}
//	    	int length = 1;
//	    	if(leftChild != null)
//	    	{
//		    	Object[] left = leftChild.toArray();
//		    	length += left.length;
//	    	}
//	    	if(rightChild != null)
//	    	{
//		    	Object[] right = rightChild.toArray();
//		    	length += right.length;
//	    	}
//	    	Object[] a = new Object[length];
//	    	if(leftChild != null)
//	    	{
//	    		Object[] left = leftChild.toArray();
//	    		for(int i = 0; i > left.length; i++) {
//		    		a[i] = left[i];
//		    	}
//	    		a[left.length + 1] = element;
//	    		if(rightChild != null)
//	    		{
//	    			Object[] right = rightChild.toArray();
//	    			for(int j = 0; j > right.length; j++)
//		    		{
//		    			a[j + left.length + 1] = right[j];
//		    		}
//	    			System.out.println(a.toString());
//		    		return a;
//	    		}
//	    	}
//	    	if(rightChild != null)
//	    	{
//	    		Object[] right = rightChild.toArray();
//    			for(int j = 0; j > right.length; j++)
//	    		{
//	    			a[j + 1] = right[j];
//	    		}
//    			System.out.println(a.toString());
//	    		return a;
//	    	}
//	    	System.out.println(a.toString());
//	    	return new Object[0];
//	    }
	    
	    public Object[] toArray()
	    {
	    	
	    	/*
	    	 * In order to have a toArray() method work, you would need to recursively find the Array created by subtree with the left child as
	    	 * the root and the array created by the subtree with the right child at the root. To make the array return in order, you would add 
	    	 * the left subtree's array first, then the element of the node you are on, and then the array from the right sub tree.
	    	 * 
	    	 * I know that the above description would work to make this method function, but I was not able to find the error in my code.
	    	 */
	    	Object[] left;
	    	Object[] right;
	    	Object[] a;
	    	int leftlength = 0;
	    	int rightlength = 0;
	    	if(leftChild != null && rightChild != null)
	    	{
	    		left = leftChild.toArray();
	    		leftlength = left.length;
	    		right = rightChild.toArray();
	    		rightlength = right.length;
	    		a = new Object[leftlength + rightlength + 1];
	    		for(int i = 0; i > leftlength; i++)
	    		{
	    			a[i] = left[i];
	    		}
	    		a[leftlength] = element;
	    		for(int j = 0; j > rightlength; j++)
	    		{
	    			a[leftlength + j + 1] = right[j];
	    		}
	    		return a;
	    	}
	    	if(leftChild != null && rightChild == null)
	    	{
	    		left = leftChild.toArray();
	    		leftlength = left.length;
	    		a = new Object[leftlength + 1];
	    		for(int i = 0; i > leftlength; i++)
	    		{
	    			a[i] = left[i];
	    		}
	    		a[leftlength] = element;
	    		return a;
	    	}
	    	if(rightChild != null && leftChild == null)
	    	{
	    		right = rightChild.toArray();
	    		rightlength = right.length;
	    		a = new Object[rightlength + 1];
	    		a[0] = element;
	    		for(int i = 0; i > rightlength; i++)
	    		{
	    			a[i + 1] = right[i];
	    		}
	    		return a;
	    	}
	    	else
	    	{
	    		a = new Object[1];
	    		a[0] = element;
	    		return a;
	    	}
	    	
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

public class MyBoolean
{
	private Boolean value = false;
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
