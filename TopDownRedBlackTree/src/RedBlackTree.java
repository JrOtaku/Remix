import java.util.Iterator;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Stack;

public class RedBlackTree<T extends Comparable<? super T>> implements Iterable<RedBlackTree.BinaryNode> {
	BinaryNode root;
	private int size;
	private int rotationCount;
	private int doubleRotationCount;
	private int modnum;

	public enum Color {
		RED, BLACK
	}

	public RedBlackTree() {
		root = null;
		size = 0;
		rotationCount = 0;
		doubleRotationCount = 0;
		modnum = 0;
	}

	public int height() {
		if (root == null)
			return -1;
		return root.height();
	}

	public int getRotationCount() {
		return rotationCount;
	}

	public int getDoubleRotationCount() {
		return -1;
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
			root.color = Color.BLACK;
			size++;
			return true;
		}
		MyBoolean b = new MyBoolean();
		root.insert(n, null, null, null, b);
		if (b.getValue()) {
			size++;
		}
		root.color = Color.BLACK;
		return b.getValue();
	}

	public boolean remove(T e) {
		if (e == null)
			throw new IllegalArgumentException();
		MyBoolean myboolean = new MyBoolean();
		if (root == null)
			return false;
		if ((root.rightChild == null || root.rightChild.color == Color.BLACK)
				&& (root.leftChild == null || root.leftChild.color == Color.BLACK)) {
			root.color = Color.RED;
			if (root.element == e) {
				root.deleteNode(null, myboolean);
			} else {
				root.remove(e, null, null, myboolean);
			}
		} else {
			if (root.element == e) {
				root.deleteNode(null, myboolean);
				myboolean.value = true;
			} else if (root.element.compareTo(e) < 0) {
				if (root.rightChild != null) {
					root.rightChild.remove2B(e, root, null, myboolean);

				}
			} else {
				if (root.leftChild != null) {
					root.leftChild.remove2B(e, root, null, myboolean);
				}
			}
		}
		if (myboolean.value)
			size--;
		modnum++;
		if (root != null) {
			root.color = Color.BLACK;
		}
		return myboolean.value;
	}

	public class BinaryNode {
		private T element;
		private BinaryNode leftChild;
		private BinaryNode rightChild;
		private Color color = Color.RED;

		public BinaryNode(T element) {
			this.element = element;
			this.leftChild = null;
			this.rightChild = null;
		}

		public T getElement() {
			return this.element;
		}

		public BinaryNode getLeftChild() {
			return this.leftChild;
		}

		public BinaryNode getRightChild() {
			return this.rightChild;
		}

		public Color getColor() {
			return this.color;
		}

		public int getDoubleRotationCount() {
			return doubleRotationCount;
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

		public boolean colorFlip() {
			if (this.leftChild != null && this.rightChild != null && this.leftChild.color == Color.RED
					&& this.rightChild.color == Color.RED) {
				this.color = Color.RED;
				this.leftChild.color = Color.BLACK;
				this.rightChild.color = Color.BLACK;
				return true;
			}
			return false;
		}

		public boolean doubleReds(BinaryNode n) {

			if (n == null)
				return false;
			return n.color == Color.RED;

		}

		public void insert(T n, BinaryNode p, BinaryNode gp, BinaryNode ggp, MyBoolean b) {
			if (colorFlip() && doubleReds(p)) {
				this.balance(p, gp, ggp);
			}
			int v = n.compareTo(element);
			if (v < 0) {
				if (leftChild == null) {
					leftChild = new BinaryNode(n);
					b.value = true;
					if (doubleReds(this)) {
						leftChild.balance(this, p, gp);
					}
					return;
				}
				leftChild.insert(n, this, p, gp, b);
			} else if (v > 0) {
				if (rightChild == null) {
					rightChild = new BinaryNode(n);
					b.value = true;
					if (doubleReds(this)) {
						rightChild.balance(this, p, gp);
					}
					return;
				}
				rightChild.insert(n, this, p, gp, b);
			}

		}

		private void balance(BinaryNode p, BinaryNode gp, BinaryNode ggp) {
			BinaryNode tempRoot = null;
			if (p == gp.leftChild) {
				if (this == p.leftChild) {
					gp.color = Color.RED;
					p.color = Color.BLACK;
					tempRoot = gp.singleRight();
				} else {
					this.color = Color.BLACK;
					gp.color = Color.RED;
					p.color = Color.RED;
					tempRoot = gp.doubleRight();
				}
			} else if (p == gp.rightChild) {
				if (this == p.rightChild) {
					gp.color = Color.RED;
					p.color = Color.BLACK;
					tempRoot = gp.singleLeft();
				} else {
					this.color = Color.BLACK;
					gp.color = Color.RED;
					p.color = Color.RED;
					tempRoot = gp.doubleLeft();
				}
			}

			if (ggp == null) {
				root = tempRoot;
				return;
			}
			if (ggp.leftChild == gp) {
				ggp.leftChild = tempRoot;
			} else {
				ggp.rightChild = tempRoot;
			}
		}

		public BinaryNode singleLeft() {
			BinaryNode temp = this.rightChild;
			this.rightChild = temp.leftChild;
			temp.leftChild = this;
			rotationCount++;
			return temp;
		}

		public BinaryNode singleRight() {
			BinaryNode temp = this.leftChild;
			this.leftChild = temp.rightChild;
			temp.rightChild = this;
			rotationCount++;
			return temp;
		}

		public BinaryNode doubleLeft() {
			this.rightChild = this.rightChild.singleRight();
			doubleRotationCount++;
			return this.singleLeft();
		}

		public BinaryNode doubleRight() {
			this.leftChild = this.leftChild.singleLeft();
			doubleRotationCount++;
			return this.singleRight();
		}

		public void remove(T e, BinaryNode p, BinaryNode gp, MyBoolean b) {
			if (e == null)
				throw new IllegalArgumentException();
			
			// Step 1
			// In superclass

			// Step 2 - The main case
			BinaryNode t;
			if (p == null) {
				// this is the the root
				if (this.element == e) {
					this.deleteNode(p, b);
					b.value = true;
					return;
				} else if (this.element.compareTo(e) < 0) {
					if (rightChild != null) {
						rightChild.remove(e, this, p, b);

					}
				} else {
					if (leftChild != null) {
						leftChild.remove(e, this, p, b);
					}
				}
			}
			// 2A
			else if ((this.rightChild == null || this.rightChild.color == Color.BLACK)
					&& (this.leftChild == null || this.leftChild.color == Color.BLACK)) {
				if (p.rightChild == this) {
					t = p.leftChild;
				} else {
					t = p.rightChild;
				}
				// 2A1
				if ((this.rightChild == null || this.rightChild.color == Color.BLACK)
						&& (this.leftChild == null || this.leftChild.color == Color.BLACK)
						&& (t==null || ((t.rightChild == null || t.rightChild.color == Color.BLACK)
						&& (t.leftChild == null || t.leftChild.color == Color.BLACK)))) {
					p.color = Color.BLACK;
					p.leftChild.color = Color.RED;
					p.rightChild.color = Color.RED;

					if (this.element == e) {
						this.deleteNode(p, b);
					} else if (this.element.compareTo(e) < 0) {
						if (rightChild != null) {
							rightChild.remove(e, this, p, b);

						}
					} else {
						if (leftChild != null) {
							leftChild.remove(e, this, p, b);
						}
					}
				}
				// 2A2
				// x is the right child
				else if (p.rightChild == this) {
					if (t.leftChild != null && t.leftChild.color == Color.RED
							&& (t.rightChild == null || t.rightChild.color == Color.BLACK)) {
						if(gp==null) {
							root = p.singleRight();
						}
						else if (gp.leftChild == p) {
							gp.leftChild = p.singleRight();
						} else {
							gp.rightChild = p.singleRight();
						}
						p.color = Color.BLACK;
						this.color = Color.RED;
						t.color = Color.RED;
						t.leftChild.color = Color.BLACK;
						if (this.element == e) {
							this.deleteNode(p, b);
						} else if (this.element.compareTo(e) < 0) {
							if (rightChild != null) {
								rightChild.remove(e, this, p, b);

							}
						} else {
							if (leftChild != null) {
								leftChild.remove(e, this, p, b);
							}
						}
					} else if (t.rightChild != null && t.rightChild.color == Color.RED
							&& (t.leftChild == null || t.leftChild.color == Color.BLACK)) {
						if (gp.leftChild == p) {
							gp.leftChild = p.doubleRight();
						} else {
							gp.rightChild = p.doubleRight();
						}
						p.color = Color.BLACK;
						this.color = Color.RED;
						if (this.element == e) {
							this.deleteNode(p, b);
						} else if (this.element.compareTo(e) < 0) {
							if (rightChild != null) {
								rightChild.remove(e, this, p, b);

							}
						} else {
							if (leftChild != null) {
								leftChild.remove(e, this, p, b);
							}
						}
					}
				} 
				//x is the left child
				else 
				{
					if (t.rightChild != null && t.rightChild.color == Color.RED
							&& (t.leftChild == null || t.leftChild.color == Color.BLACK)) {
						if(gp==null) {
							root = p.singleLeft();
						}
						else if (gp.leftChild == p) {
							gp.leftChild = p.singleLeft();
						} else {
							gp.rightChild = p.singleLeft();
						}
						p.color = Color.BLACK;
						this.color = Color.RED;
						t.color = Color.RED;
						t.rightChild.color = Color.BLACK;
						if (this.element == e) {
							this.deleteNode(p, b);
						} else if (this.element.compareTo(e) < 0) {
							if (rightChild != null) {
								rightChild.remove(e, this, p, b);

							}
						} else {
							if (leftChild != null) {
								leftChild.remove(e, this, p, b);
							}
						}
					} else if (t.leftChild != null && t.leftChild.color == Color.RED
							&& (t.rightChild == null || t.rightChild.color == Color.BLACK)) {
						if (gp.leftChild == p) {
							gp.leftChild = p.doubleLeft();
						} else {
							gp.rightChild = p.doubleLeft();
						}
						p.color = Color.BLACK;
						this.color = Color.RED;
						if (this.element == e) {
							this.deleteNode(p, b);
						} else if (this.element.compareTo(e) < 0) {
							if (rightChild != null) {
								rightChild.remove(e, this, p, b);

							}
						} else {
							if (leftChild != null) {
								leftChild.remove(e, this, p, b);
							}
						}
					}
				}
			}
			// 2B
			else {
				this.remove2BFull(e, p, gp, b);
			}

			// Step 3 - Actual Deletion

			// Step - Root turned black in RedBlackTree Class
		}
		
		public void remove2BFull(T e, BinaryNode p, BinaryNode gp, MyBoolean b) {
			if (this.element == e) {
				this.deleteNode(p, b);
				b.value = true;
				return;
			} else if (this.element.compareTo(e) < 0) {
				if (rightChild != null) {
					rightChild.remove2B(e, this, p, b);

				}
			} else {
				if (leftChild != null) {
					leftChild.remove2B(e, this, p, b);
				}
			}
		}

		public void remove2B(T e, BinaryNode p, BinaryNode gp, MyBoolean b) {
			if (this.color == Color.RED) {
				if (this.element == e) {
					this.deleteNode(p, b);
				} else if (this.element.compareTo(e) < 0) {
					if (rightChild != null) {
						rightChild.remove(e, this, p, b);

					}
				} else {
					if (leftChild != null) {
						leftChild.remove(e, this, p, b);
					}
				}
			} else {
				if(gp == null)
				{
					if(this == p.leftChild)
					{
						root = p.singleLeft();
						p.color = Color.RED;
						root.color = Color.BLACK;
						this.remove(e, p, gp, b);
					}
					else
					{
						root = p.singleRight();
						p.color = Color.RED;
						root.color = Color.BLACK;
						this.remove(e, p, gp, b);
					}
				}
				else
				{
					if (p.leftChild != this) {
						if (gp.leftChild == p) {
							gp.leftChild = p.singleLeft();
							p.color = Color.RED;
							gp.leftChild.color = Color.BLACK;
							this.remove(e, p, gp, b);
						} else {
							gp.rightChild = p.singleLeft();
							p.color = Color.RED;
							gp.rightChild.color = Color.BLACK;
							this.remove(e, p, gp, b);
						}
					} else {
						if (gp.leftChild == p) {
							gp.leftChild = p.singleRight();
							p.color = Color.RED;
							gp.leftChild.color = Color.BLACK;
							this.remove(e, p, gp, b);
						} else {
							gp.rightChild = p.singleRight();
							p.color = Color.RED;
							gp.rightChild.color = Color.BLACK;
							this.remove(e, p, gp, b);
						}
					}
				}
			}
		}

		public void deleteNode(BinaryNode p, MyBoolean b) {
			//if the parent is null
			if (p == null) {
				if (leftChild == null && rightChild == null) {
					root = null;
					b.value = true;
					return;
				} else if (leftChild == null && rightChild != null) {
					root = rightChild;
					root.color = Color.BLACK;
					b.value = true;
					return;
				} else if (leftChild != null && rightChild == null) {
					root = leftChild;
					root.color = Color.BLACK;
					b.value = true;
					return;
				} else {
					BinaryNode temp = this.leftChild;
					BinaryNode tempparent = this;
					while (temp.rightChild != null) {
						tempparent = temp;
						temp = temp.rightChild;
					}
					if(this.color == Color.RED)
					{
						this.element = temp.element;
						this.leftChild.remove(temp.element, this, p, b);
						
					}else {
						this.remove2BFull(temp.element, p, null, b);
//						this.leftChild.remove2B(temp.element, this, p, b);
						this.element = temp.element;
					}
					b.value = true;
					return;
				}
			}
			// case where both children are null
			else if (leftChild == null && rightChild == null) 
			{
				if (p.leftChild == this) {
					p.leftChild = null;
					b.value = true;
					return;
				} else if (p.rightChild == this) {
					p.rightChild = null;
					b.value = true;
					return;
				}
			} 
			else if (leftChild == null && rightChild != null) 
			{
				if(this.color == Color.RED)
				{
					if (p.leftChild == this) {
						p.leftChild = rightChild;
						b.value = true;
						return;
					}
					if (p.rightChild == this) {
						p.rightChild = rightChild;
						b.value = true;
						return;
					}
				}
				else
				{
					if (p.leftChild == this) {
						p.leftChild = rightChild;
						p.leftChild.color = Color.BLACK;
						b.value = true;
						return;
					}
					if (p.rightChild == this) {
						p.rightChild = rightChild;
						p.rightChild.color = Color.BLACK;
						b.value = true;
						return;
					}
				}
			} 
			else if (leftChild != null && rightChild == null) 
			{
				if(this.color == Color.RED)
				{
					if (p.leftChild == this) {
						p.leftChild = leftChild;
						b.value = true;
						return;
					}
					if (p.rightChild == this) {
						p.rightChild = leftChild;
						b.value = true;
						return;
					}
				}
				else
				{
					if (p.leftChild == this) {
						p.leftChild = leftChild;
						p.leftChild.color = Color.BLACK;
						b.value = true;
						return;
					}
					if (p.rightChild == this) {
						p.rightChild = leftChild;
						p.rightChild.color = Color.BLACK;
						b.value = true;
						return;
					}
				}
			} 
			else
			{
				BinaryNode temp;
				BinaryNode tempparent;
				if (p.leftChild == this) {
					temp = this.leftChild;
					tempparent = this;
					while (temp.rightChild != null) {
						tempparent = temp;
						temp = temp.rightChild;
					}
					if(this.color == Color.RED)
					{
						this.element = temp.element;
						this.leftChild.remove(temp.element, this, p, b);
						
					}else {
						this.remove2BFull(temp.element, p, null, b);
//						this.leftChild.remove2B(temp.element, this, p, b);
						this.element = temp.element;
					}
					b.value = true;
					return;
				}
			}
		}

	}

	public Iterator<RedBlackTree.BinaryNode> iterator() {
		return new PreOrderIterator(root);
	}

	public class PreOrderIterator implements Iterator<RedBlackTree.BinaryNode> {
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

		public RedBlackTree.BinaryNode next() {
			if (!hasNext())
				throw new NoSuchElementException();
			BinaryNode temp = s.pop();
			if (temp.rightChild != null)
				s.push(temp.rightChild);
			if (temp.leftChild != null)
				s.push(temp.leftChild);
			last = temp;
			return temp;
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
			RedBlackTree.this.remove(last.element);
			preitmodnum++;
			last = null;
		}
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