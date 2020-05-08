package BST;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {
	BinaryNode root;
	private int size;
	private int modnum = 0;

	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public int height() {
		if (root == null)
			return -1;
		return root.height();
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
			modnum++;
			return true;
		}
		modnum++;
		return root.insert(n);
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

	public Iterator<T> iterator() {
		return new BSTIterator(root);
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
		
		public void remove()
		{
			if(modnum != iteratormodnum) throw new ConcurrentModificationException();
			if(last == null) throw new IllegalStateException();
			BinarySearchTree.this.remove(last.element);
			last = null;
			iteratormodnum++;
		}

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

		public void remove() {
			if(preitmodnum != modnum) throw new ConcurrentModificationException();
			if (last == null)
				throw new IllegalStateException();
			if (last.rightChild != null)
				s.pop();
			if (last.leftChild != null)
				s.pop();

			if (last.leftChild != null && last.rightChild != null) {
				if (last.leftChild.rightChild != null) {
					BinaryNode temp = last.leftChild;
					while (temp.rightChild != null) {
						temp = temp.rightChild;
					}
					s.push(temp);
				} else {
					s.push(last.leftChild);
				}
			} else if (last.leftChild != null) {
				s.push(last.leftChild);
			} else if (last.rightChild != null) {
				s.push(last.rightChild);
			}
			BinarySearchTree.this.remove(last.element);
			preitmodnum++;
			last = null;
		}
	}

	public class BinaryNode {
		private T element;
		private BinaryNode leftChild;
		private BinaryNode rightChild;

		public BinaryNode(T element) {
			this.element = element;
			this.leftChild = null;
			this.rightChild = null;
		}

		public BinaryNode getLeftChild() {
			return leftChild;
		}

		public BinaryNode getRightChild() {
			return rightChild;
		}

		public T getElement() {
			return element;
		}

		public int height() {
			int leftHeight = 0;
			int rightHeight = 0;
			if (leftChild != null)
				leftHeight += leftChild.height() + 1;
			if (rightChild != null)
				rightHeight += rightChild.height() + 1;
			if (rightHeight > leftHeight)
				return rightHeight;
			return leftHeight;
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

		public boolean insert(T n) {
			int v = n.compareTo(element);
			if (v < 0) {
				if (leftChild == null) {
					leftChild = new BinaryNode(n);
					size++;
					return true;
				}
				return leftChild.insert(n);
			} else if (v > 0) {
				if (rightChild == null) {
					rightChild = new BinaryNode(n);
					size++;
					return true;
				}
				return rightChild.insert(n);
			} else {
				return false;
			}
		}

		public BinaryNode remove(T e, MyBoolean myboolean) {
			if (e.compareTo(element) < 0) {
				if (leftChild == null) {
					return this;
				}
				leftChild = leftChild.remove(e, myboolean);
				return this;
			}
			if (e.compareTo(element) > 0) {
				if (rightChild == null) {
					return this;
				}
				rightChild = rightChild.remove(e, myboolean);
				return this;
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
						return temp;
					}
					leftChild.rightChild = this.rightChild;
					return this.leftChild;
				} else if (leftChild != null) {
					return this.leftChild;
				} else if (rightChild != null) {
					return this.rightChild;
				} else {
					return null;
				}
			}
		}
	}

	public class MyBoolean {
		private boolean value = false;
	}
}
