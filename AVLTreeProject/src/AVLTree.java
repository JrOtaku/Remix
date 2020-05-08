import java.util.Iterator;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Stack;


public class AVLTree<T extends Comparable<? super T>> implements Iterable<T> {
	BinaryNode root;
	private int size;
	private int rotationCount;
	private int modnum;
	
	public AVLTree()
	{
		root = null;
		size = 0;
		rotationCount = 0;
		modnum = 0;
	}
	
	public int height()
	{
		if (root == null) return -1;
		return root.height;
	}
	
	public int getRotationCount()
	{
		return rotationCount;
	}
	
	public int size() {
		return size;
	}
	
	public ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<T>();
		if (root == null)
			return list;
		root.toArrayList(list);
		return list;
	}

	public String toString() {
		return toArrayList().toString();
	}

	public Object[] toArray() {
		return toArrayList().toArray();
	}
	
	public boolean insert(T n) {
		if (n == null)
			throw new IllegalArgumentException();
		if (root == null) {
			root = new BinaryNode(n);
			size++;
			return true;
		}
		MyBoolean b = new MyBoolean();
		root = root.insert(n, b);
		if (b.getValue()) {
			size++;
		}
		return b.getValue();
	}
	
	public boolean remove(T e) {
		if (e == null)
			throw new IllegalArgumentException();
		MyBoolean myboolean = new MyBoolean();
		if (root == null)
			return false;
		root = root.remove(e, myboolean);
		if (myboolean.value)
			size--;
		modnum++;
		return myboolean.value;
	}
	
	
	public class BinaryNode {
		private T element;
		private BinaryNode leftChild;
		private BinaryNode rightChild;
		private int height;

		public BinaryNode(T element) {
			this.element = element;
			this.leftChild = null;
			this.rightChild = null;
			this.height = 0;
		}
		
		public void toArrayList(ArrayList<T> list) {
			if (leftChild != null) {
				leftChild.toArrayList(list);
			}
			list.add(element);
			if (rightChild != null) {
				rightChild.toArrayList(list);
			}
		}
		
		
		public BinaryNode insert(T n, MyBoolean b)
		{
			int v = n.compareTo(element);
			if (v < 0) {
				if (leftChild == null) {
					leftChild = new BinaryNode(n);
					b.setTrue();
					return balanceLeft();
				}
				leftChild = leftChild.insert(n, b);
				return balanceLeft();
			}
			
			if (v > 0) {
				if (rightChild == null) {
					rightChild = new BinaryNode(n);
					b.setTrue();
					return balanceRight();
				}
				rightChild = rightChild.insert(n,b);
				return balanceRight();
			}
			
			return this;
		}

		public BinaryNode balanceLeft()
		{
			if(Math.abs(heightDiff()) > 1)
			{
				if(leftChild.heightDiff() < 0)
				{
					return doubleRight();
				}
				else
				{
					return singleRight();
				}
			}
			updateHeight();
			return this;
		}
		
		public BinaryNode balanceRight()
		{
			if(Math.abs(heightDiff()) > 1)
			{
				if(rightChild.heightDiff() > 0)
				{
					return doubleLeft();
				}
				else
				{
					return singleLeft();
				}
			}
			updateHeight();
			return this;
		}
		
		public int heightDiff()
		{
			if(this.leftChild != null && this.rightChild != null)
			{
				return this.leftChild.height - this.rightChild.height;
			}
			else if(this.leftChild != null)
			{
				return this.leftChild.height + 1;
			}
			else if(this.rightChild != null)
			{
				return -(this.rightChild.height + 1);
			}
			else
			{
				return 0;
			}
		}
		
		public BinaryNode singleLeft()
		{
			BinaryNode temp = this.rightChild;
			this.rightChild = temp.leftChild;
			temp.leftChild = this;
			rotationCount++;
			this.updateHeight();
			temp.updateHeight();
			return temp;
		}
		
		public BinaryNode singleRight()
		{
			BinaryNode temp = this.leftChild;
			this.leftChild = temp.rightChild;
			temp.rightChild = this;
			rotationCount++;
			this.updateHeight();
			temp.updateHeight();
			return temp;
		}
		
		public BinaryNode doubleLeft()
		{
			this.rightChild = this.rightChild.singleRight();
			return this.singleLeft();
		}
		
		public BinaryNode doubleRight()
		{
			this.leftChild = this.leftChild.singleLeft();
			return this.singleRight();
		}
		
		public void updateHeight()
		{
			if(this.leftChild != null && this.rightChild != null)
			{
				this.height = 1 + (this.leftChild.height > this.rightChild.height?this.leftChild.height:this.rightChild.height);
			}
			else if(this.leftChild != null)
			{
				this.height = this.leftChild.height + 1;
			}
			else if(this.rightChild != null)
			{
				this.height = this.rightChild.height + 1;
			}
			else
			{
				this.height = 0;
			}
		}
		
		public BinaryNode remove(T e, MyBoolean myboolean) {
			if (e.compareTo(element) < 0) {
				if (leftChild == null) {
					return this;
				}
				leftChild = leftChild.remove(e, myboolean);
				return balanceRight();
			}
			if (e.compareTo(element) > 0) {
				if (rightChild == null) {
					return this;
				}
				rightChild = rightChild.remove(e, myboolean);
				return balanceLeft();
			} else {
				myboolean.value = true;
				if (leftChild != null && rightChild != null) {
					if (leftChild.rightChild != null) {
						BinaryNode temp = leftChild;
						BinaryNode tempparent = this;
						while (temp.rightChild != null) {
							tempparent = temp;
							temp = temp.rightChild;
						}
						tempparent.rightChild = temp.leftChild;
						temp.leftChild = this.leftChild;
						temp.rightChild = this.rightChild;
						return temp.balanceRight();
					}
					leftChild.rightChild = this.rightChild;
					return this.leftChild.balanceRight();
				} else if (leftChild != null) {
					return this.leftChild.balanceRight();
				} else if (rightChild != null) {
					return this.rightChild.balanceLeft();
				} else {
					return null;
				}
			}
		}
	}
	
	public Iterator<T> iterator() {
		return new PreOrderIterator(root);
	}

	public class BSTIterator implements Iterator<T> {
		private Stack<BinaryNode> s = new Stack<BinaryNode>();
		private BinaryNode last = null;
		private int iteratormodnum;

		public BSTIterator(BinaryNode root) {
			iteratormodnum = modnum;
			if (root != null)
				getLeftAncestors(root);
		}

		public boolean hasNext() {
			return !s.empty();
		}

		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			BinaryNode temp = s.pop();
			if (temp.rightChild != null)
				getLeftAncestors(temp.rightChild);
			last = temp;
			return temp.element;
		}

		private void getLeftAncestors(BinaryNode n) {
			s.push(n);
			if (n.leftChild != null)
				getLeftAncestors(n.leftChild);
		}
		
//		public void remove()
//		{
//			if(modnum != iteratormodnum) throw new ConcurrentModificationException();
//			if(last == null) throw new IllegalStateException();
//			BinarySearchTree.this.remove(last.element);
//			last = null;
//			iteratormodnum++;
//		}

	}

	public Iterator<T> preOrderIterator() {
		return new PreOrderIterator(root);
	}

	public class PreOrderIterator implements Iterator<T> {
		private Stack<BinaryNode> s = new Stack<BinaryNode>();
		private BinaryNode last = null;
		private int preitmodnum;

		public PreOrderIterator(BinaryNode root) {
			preitmodnum = modnum;
			if (root != null)
				s.push(root);
		}

		public boolean hasNext() {
			return !s.empty();
		}

		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			BinaryNode temp = s.pop();
			if (temp.rightChild != null)
				s.push(temp.rightChild);
			if (temp.leftChild != null)
				s.push(temp.leftChild);
			last = temp;
			return temp.element;
		}

//		public void remove() {
//			if(preitmodnum != modnum) throw new ConcurrentModificationException();
//			if (last == null)
//				throw new IllegalStateException();
//			if (last.rightChild != null)
//				s.pop();
//			if (last.leftChild != null)
//				s.pop();
//
//			if (last.leftChild != null && last.rightChild != null) {
//				if (last.leftChild.rightChild != null) {
//					BinaryNode temp = last.leftChild;
//					while (temp.rightChild != null) {
//						temp = temp.rightChild;
//					}
//					s.push(temp);
//				} else {
//					s.push(last.leftChild);
//				}
//			} else if (last.leftChild != null) {
//				s.push(last.leftChild);
//			} else if (last.rightChild != null) {
//				s.push(last.rightChild);
//			}
//			AVLTree.this.remove(last.element);
//			preitmodnum++;
//			last = null;
//		}
	}
	
	public class MyBoolean {
		private boolean value = false;
		
		public boolean getValue() {
			return value;
		}
		
		public void setTrue() {
			value = true;
		}
	}
}