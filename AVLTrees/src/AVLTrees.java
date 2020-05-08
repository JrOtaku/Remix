import java.util.Iterator;

import BST.BinarySearchTree.BinaryNode;

public class AVLTrees<T extends Comparable<? super T>> implements Iterable<T> {
	BinaryNode root;
	
	public AVLTrees()
	{
		root = null;
	}
	
	public class BinaryNode()
	{
		private T element;
		private BinaryNode leftChild;
		private BinaryNode rightChild;

		public BinaryNode(T element) {
			this.element = element;
			this.leftChild = null;
			this.rightChild = null;
		}
	}
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
