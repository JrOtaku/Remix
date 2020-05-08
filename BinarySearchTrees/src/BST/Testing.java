package BST;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.TreeSet;

import org.junit.AfterClass;
import org.junit.Test;


public class Testing {
	
	private static int points = 0;
	
	
	@Test
	public void testRemoveNullElement(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		try {
			b.remove(null);
			fail("Did not throw IllegalArgumentException");
		} catch (Exception e){
			if (!(e instanceof IllegalArgumentException)) {
				fail("Did not throw IllegalArgumentException");				
			}
		}
		points += 1;
	}
	
	@Test
	public void testRemoveFromEmptyTree(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals("[]", b.toString());
		assertFalse(b.remove(7));
		assertEquals(0, b.size());
		points += 1;
	}

	@Test
	public void testRemoveJustRoot(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(4);
		assertTrue(b.remove(4));
		assertEquals("[]", b.toString());
		assertFalse(b.remove(4));
		assertEquals(0, b.size());
		points += 1;
	}
		
	@Test
	public void testRemoveRightChildInSimpleTree(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(4);
		b.insert(14);
		assertTrue(b.remove(14));
		points += 1;
		Integer[] a = {10, 4};
		boolean[] bool = {true, false};
		Iterator<Integer> i = b.preOrderIterator();
		assertTrue(i.hasNext());
		for (int k = 0; k < a.length; k++){
			assertEquals(a[k], i.next());
			assertEquals(bool[k], i.hasNext());
		}
		assertEquals(2, b.size());
		points += 1;
	}
	
	@Test
	public void testRemoveLeftChildInSimpleTree(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(4);
		b.insert(14);
		assertTrue(b.remove(4));
		
		Integer[] a = {10, 14};
		boolean[] bool = {true, false};
		Iterator<Integer> i = b.preOrderIterator();
		assertTrue(i.hasNext());
		for (int k = 0; k < a.length; k++){
			assertEquals(a[k], i.next());
			assertEquals(bool[k], i.hasNext());
		}
		assertEquals(2, b.size());
		points += 1;
	}
	
	@Test
	public void testRemoveRootInSimpleTree(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(4);
		b.insert(14);
		assertTrue(b.remove(10));
		Integer[] a = {4, 14};
		boolean[] bool = {true, false};
		Iterator<Integer> i = b.preOrderIterator();
		assertTrue(i.hasNext());
		for (int k = 0; k < a.length; k++){
			assertEquals(a[k], i.next());
			assertEquals(bool[k], i.hasNext());
		}
		assertEquals(2, b.size());
		points += 1;
	}
		
	@Test
	public void testRemoveLeafFromComplexTree(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(15);
		b.insert(5);
		b.insert(2);
		b.insert(7);
		b.insert(1);
		b.insert(3);
		b.remove(7);
		assertEquals("[1, 2, 3, 5, 10, 15]", b.toString());
		Integer[] m = {10, 5, 2, 1, 3, 15};
		boolean boo[] = {true, true, true, true, true, false}; 
		Iterator<Integer> i = b.preOrderIterator();
		assertTrue(i.hasNext());
		for (int k = 0; k < m.length; k++){
			assertEquals(m[k], i.next());
			assertEquals(boo[k], i.hasNext());
		}
		points += 3;
		assertEquals(6, b.size());
		points += 1;
	}
	
	@Test
	public void testRemoveNodeWithOneChildFromComplexTree(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(15);
		b.insert(5);
		b.insert(2);
		b.insert(1);
		b.insert(3);
		assertEquals("[1, 2, 3, 5, 10, 15]", b.toString());
		b.remove(5);
		assertEquals("[1, 2, 3, 10, 15]", b.toString());
		Integer[] n = {10, 2, 1, 3, 15};
		boolean boo2[] = {true, true, true, true, false}; 
		Iterator<Integer> i = b.preOrderIterator();
		assertTrue(i.hasNext());
		for (int k = 0; k < n.length; k++){
			assertEquals(n[k], i.next());
			assertEquals(boo2[k], i.hasNext());
		}
		assertEquals(5, b.size());
		points += 3;

	}
	
	@Test
	public void testRemoveNodeWithTwoChildrenFromComplexTree(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(15);
		b.insert(12);
		b.insert(17);
		b.insert(13);
		b.insert(14);
		b.insert(5);
		b.insert(2);
		b.insert(1);
		b.insert(3);
		assertEquals("[1, 2, 3, 5, 10, 12, 13, 14, 15, 17]", b.toString());
		b.remove(15);
		assertEquals("[1, 2, 3, 5, 10, 12, 13, 14, 17]", b.toString());
		Integer[] p = {10, 5, 2, 1, 3, 14, 12, 13, 17};
		boolean boo3[] = {true, true, true, true, true, true, true, true, false}; 
		Iterator<Integer> i = b.preOrderIterator();
		assertTrue(i.hasNext());
		for (int k = 0; k < p.length; k++){
			assertEquals(p[k], i.next());
			assertEquals(boo3[k], i.hasNext());
		}
		assertEquals(9, b.size());
		points += 3;

	}
	
	@Test
	public void testRemoveRootFromComplexTree(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(15);
		b.insert(12);
		b.insert(17);
		b.insert(13);
		b.insert(14);
		b.insert(5);
		b.insert(2);
		b.insert(1);
		b.insert(3);
		assertEquals("[1, 2, 3, 5, 10, 12, 13, 14, 15, 17]", b.toString());
		b.remove(10);
		assertEquals("[1, 2, 3, 5, 12, 13, 14, 15, 17]", b.toString());
		Integer[] p = {5, 2, 1, 3, 15, 12, 13, 14, 17};
		boolean boo3[] = {true, true, true, true, true, true, true, true, false}; 
		Iterator<Integer> i = b.preOrderIterator();
		assertTrue(i.hasNext());
		for (int k = 0; k < p.length; k++){
			assertEquals(p[k], i.next());
			assertEquals(boo3[k], i.hasNext());
		}
		assertEquals(9, b.size());
		points += 3;
	}
	
	@Test
	public void testRemoveNonExistingElement(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(15);
		b.insert(12);
		b.insert(17);
		b.insert(13);
		b.insert(14);
		b.insert(5);
		b.insert(2);
		b.insert(1);
		b.insert(3);
		assertEquals("[1, 2, 3, 5, 10, 12, 13, 14, 15, 17]", b.toString());
		assertFalse(b.remove(16));
		assertEquals(10, b.size());
		points += 2;
	}
	
	@Test
	public void testRemoveAllElements(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(15);
		b.insert(12);
		b.insert(17);
		b.insert(13);
		b.insert(14);
		b.insert(5);
		b.insert(2);
		b.insert(1);
		b.insert(3);
		assertEquals("[1, 2, 3, 5, 10, 12, 13, 14, 15, 17]", b.toString());
		assertTrue(b.remove(10));
		assertTrue(b.remove(5));
		assertTrue(b.remove(3));
		assertTrue(b.remove(2));
		assertEquals("[1, 12, 13, 14, 15, 17]", b.toString());
		assertTrue(b.remove(1));
		assertEquals("[12, 13, 14, 15, 17]", b.toString());
		assertTrue(b.remove(12));
		assertTrue(b.remove(13));
		assertTrue(b.remove(14));
		assertTrue(b.remove(15));
		assertTrue(b.remove(17));
		assertEquals("[]", b.toString());
		assertEquals(0, b.size());
		points += 3;
	}
	

	
	@Test
	public void testRemoveInPreOrderIterator(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();

		// Testing exception throwing on empty tree.
		Iterator<Integer> i = b.preOrderIterator();
		try {
			i.remove();
			fail("Did not throw IllegalStateException");
		} catch (Exception e){
			if (!(e instanceof IllegalStateException)) {
				fail("Did not throw IllegalStateException");				
			}
		}
		points += 1;
		
		b.insert(5);
		b.insert(3);
		b.insert(2);
		b.insert(4);
		b.insert(7);
		assertEquals("[2, 3, 4, 5, 7]", b.toString());
		Iterator<Integer> it = b.preOrderIterator();
		Object[] ans = { 5, 4, 3, 2, 7 };
		for (int j = 0; j < ans.length; j++) {
			assertEquals(it.next(), ans[j]);
			it.remove();
		}
		points += 1;

		b.insert(5);
		b.insert(3);
		b.insert(7);
		i = b.preOrderIterator();
		
		// Testing exception throwing when next() has not been
		// called yet.
		assertTrue(i.hasNext());
		try {
			i.remove();
			fail("Did not throw IllegalStateException");
		} catch (Exception e){
			if (!(e instanceof IllegalStateException)) {
				fail("Did not throw IllegalStateException");				
			}
		}
		points += 1;
		
		i.next();
		assertTrue(i.hasNext());
		i.next();
		i.remove();
		assertEquals("[5, 7]", b.toString());
		points += 2;

		
		try {
			i.remove();
			fail("Did not throw IllegalStateException");
		} catch (Exception e){
			if (!(e instanceof IllegalStateException)) {
				fail("Did not throw IllegalStateException");				
			}
		}
		points += 1;
	}
	
	@Test
	public void testPreorderIteratorRemoveStronger() {
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		int[] data = { 12, 8, 6, 5, 7, 9, 10, 18, 14, 16 };
		for (int i = 0; i < data.length; i++) {
			b.insert(data[i]);
		}
		assertEquals("[5, 6, 7, 8, 9, 10, 12, 14, 16, 18]", b.toString());
		Iterator<Integer> it = b.preOrderIterator();
		it.next();
		it.next();
		assertEquals(Integer.valueOf(6), it.next());
		it.remove();
		assertEquals("[5, 7, 8, 9, 10, 12, 14, 16, 18]", b.toString());
		assertEquals(Integer.valueOf(5), it.next());
		it.remove();
		assertEquals("[7, 8, 9, 10, 12, 14, 16, 18]", b.toString());
		assertEquals(Integer.valueOf(7), it.next());
		it.remove();
		assertEquals("[8, 9, 10, 12, 14, 16, 18]", b.toString());
		assertEquals(Integer.valueOf(9), it.next());
		assertEquals(Integer.valueOf(10), it.next());
		it.remove();
		assertEquals("[8, 9, 12, 14, 16, 18]", b.toString());
		assertEquals(Integer.valueOf(18), it.next());
		it.remove();
		assertEquals(Integer.valueOf(14), it.next());
		assertEquals(Integer.valueOf(16), it.next());

		/**
		 * 					12
		 * 			 /				\
		 * 			8				18
		 * 		 /	   \		 /	
		 * 		6		9		14
		 * 	  /   \      \		  \
		 * 	 5    7      10       16
		 */
		b = new BinarySearchTree<Integer>();
		for (int i = 0; i < data.length; i++) {
			b.insert(data[i]);
		}
		assertEquals("[5, 6, 7, 8, 9, 10, 12, 14, 16, 18]", b.toString());
		it = b.preOrderIterator();
		it.next();
		it.next();
		it.remove();
		assertEquals("[5, 6, 7, 9, 10, 12, 14, 16, 18]", b.toString());
		assertEquals(Integer.valueOf(7), it.next());
		assertEquals(Integer.valueOf(6), it.next());
		it.remove();
		assertEquals("[5, 7, 9, 10, 12, 14, 16, 18]", b.toString());
		assertEquals(Integer.valueOf(5), it.next());
		it.remove();
		assertEquals("[7, 9, 10, 12, 14, 16, 18]", b.toString());
		assertEquals(Integer.valueOf(9), it.next());
		points += 4;
	}

	
	@Test
	public void testRemoveInInOrderIterator(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();

		// Testing exception throwing on empty tree.
		Iterator<Integer> i = b.iterator();
		try {
			i.remove();
			fail("Did not throw IllegalStateException");
		} catch (Exception e){
			if (!(e instanceof IllegalStateException)) {
				fail("Did not throw IllegalStateException");				
			}
		}
		points += 1;
		
		b.insert(5);
		b.insert(3);
		b.insert(7);
		i = b.iterator();
		
		// Testing exception throwing when next() has not been
		// called yet.
		assertTrue(i.hasNext());
		try {
			i.remove();
			fail("Did not throw IllegalStateException");
		} catch (Exception e){
			if (!(e instanceof IllegalStateException)) {
				fail("Did not throw IllegalStateException");				
			}
		}
		points += 1;

		i.next();
		assertTrue(i.hasNext());
		i.next();
		i.remove();
		assertEquals("[3, 7]", b.toString());
		points += 2;
		
		try {
			i.remove();
			fail("Did not throw IllegalStateException");
		} catch (Exception e){
			if (!(e instanceof IllegalStateException)) {
				fail("Did not throw IllegalStateException");				
			}
		}
		points += 1;
	}	
	
	@Test
	public void testMeanStuffInOrder(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(15);
		b.insert(12);
		b.insert(17);
		b.insert(13);
		b.insert(14);
		b.insert(5);
		b.insert(2);
		b.insert(1);
		b.insert(3);
		assertEquals("[1, 2, 3, 5, 10, 12, 13, 14, 15, 17]", b.toString());
		Iterator<Integer> i = b.iterator();
		assertTrue(i.hasNext());
		i.next();
		i.remove();
		i.next();
		i.remove();
		assertEquals("[3, 5, 10, 12, 13, 14, 15, 17]", b.toString());
		assertTrue(i.hasNext());
		assertTrue(b.remove(5));
		assertEquals("[3, 10, 12, 13, 14, 15, 17]", b.toString());
		try {
			i.next();
			i.remove();
			fail("Did not throw ConcurrentModificationException");
		} catch (Exception e){
			if (!(e instanceof ConcurrentModificationException)) {
				fail("Did not throw ConcurrentModificationException");				
			}
		}
		points += 2;
		b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(15);
		b.insert(12);
		b.insert(17);
		b.insert(13);
		b.insert(14);
		b.insert(5);
		b.insert(2);
		b.insert(1);
		b.insert(3);
		assertEquals("[1, 2, 3, 5, 10, 12, 13, 14, 15, 17]", b.toString());
		i = b.iterator();
		assertTrue(i.hasNext());
		i.next();
		b.insert(6);
		try {
			i.next();
			i.remove();
			fail("Did not throw ConcurrentModificationException");
		} catch (Exception e){
			if (!(e instanceof ConcurrentModificationException)) {
				fail("Did not throw ConcurrentModificationException");				
			}
		}	
		points += 2;
	}
	
	@Test
	public void testMeanStuffPreOrder(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(15);
		b.insert(12);
		b.insert(17);
		b.insert(13);
		b.insert(14);
		b.insert(5);
		b.insert(2);
		b.insert(1);
		b.insert(3);
		assertEquals("[1, 2, 3, 5, 10, 12, 13, 14, 15, 17]", b.toString());
		Iterator<Integer> i = b.preOrderIterator();
		assertTrue(i.hasNext());
		i.next();
		i.remove();
		i.next();
		i.remove();
		assertEquals("[1, 2, 3, 12, 13, 14, 15, 17]", b.toString());
		assertTrue(i.hasNext());
		assertTrue(b.remove(3));
		assertEquals("[1, 2, 12, 13, 14, 15, 17]", b.toString());
		try {
			i.next();
			i.remove();
			fail("Did not throw ConcurrentModificationException");
		} catch (Exception e){
			if (!(e instanceof ConcurrentModificationException)) {
				fail("Did not throw ConcurrentModificationException");				
			}
		}
		points += 2;
		b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(15);
		b.insert(12);
		b.insert(17);
		b.insert(13);
		b.insert(14);
		b.insert(5);
		b.insert(2);
		b.insert(1);
		b.insert(3);
		assertEquals("[1, 2, 3, 5, 10, 12, 13, 14, 15, 17]", b.toString());
		i = b.preOrderIterator();
		assertTrue(i.hasNext());
		i.next();
		b.insert(6);
		try {
			i.next();
			i.remove();
			fail("Did not throw ConcurrentModificationException");
		} catch (Exception e){
			if (!(e instanceof ConcurrentModificationException)) {
				fail("Did not throw ConcurrentModificationException");				
			}
		}	
		points += 2;
	}
	
	@Test
	public void testPerformanceRegularRemove(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		int nums = 1000000;
		for (int i = 0; i < nums; i++){
			b.insert((int)(Math.random()*nums));
		}
		nums = 1000000;
		for (int i = 0; i < nums; i++){
			b.remove((int)(Math.random()*nums));
		}
		points += 3; 
	}
	
	@Test
	public void testPerformanceRemoveInOrderIterator(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		int size = 1048576;
		int v = size / 2;
		int temp;
		while (v > 0) {
			temp = v;
			while (temp < size){
				b.insert(temp);
				temp += v;
				}
			v = v / 2;
		}
		assertEquals(size-1, b.size());
		assertEquals(19, b.height());
		Iterator<Integer> i = b.iterator();
		Integer prev = i.next();
		i.remove();
		Integer current;
		while (i.hasNext()){
			current = i.next();
			i.remove();
			if (current < prev) fail("Not a BST at: " + prev + " " +  current);
		}
		if (!(b.size() == 0)) fail("Something appears to be wrong with your in-order iterator");
		points += 2;
	}
	
	@Test
	public void testPerformancePreOrderIterator(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		int size = 1048576;
		int halfSize = (size/2)-100;
		int v = size / 2;
		int temp;
		while (v > 0) {
			temp = v;
			while (temp < size){
				b.insert(temp);
				temp += v;
				}
			v = v / 2;
		}
		assertEquals(size-1, b.size());
		assertEquals(19, b.height());
		Iterator<Integer> i = b.preOrderIterator();
		Integer prev = i.next();
		i.remove();
		Integer current;
		int count = 1;
		while (i.hasNext()){
			count++;
			current = i.next();
			i.remove();
			if (count < halfSize && current > prev) fail("Not a BST at: " + prev + " " +  current);
		}
		if (!(b.size() == 0)) fail("Something appears to be wrong with your in-order iterator");
		points += 2;
	}
		
	@AfterClass
	public static void testNothing(){
		System.out.println("Points: " + points);
	}

}