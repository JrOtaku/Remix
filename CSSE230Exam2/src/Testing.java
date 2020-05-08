import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.TreeSet;

import org.junit.AfterClass;
import org.junit.Test;

public class Testing {
		
	private static int points = 0;
	

	@Test
	public void testContains(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertFalse(b.contains(6)); 
		points += 1;
		try {
			b.contains(null);
			fail("Did not throw NullPointerException");
		} catch (Exception e){
			if (!(e instanceof NullPointerException)) {
				fail("Did not throw NullPointerException");				
			}
		}
		points += 1;
		b.insert(4);
		assertFalse(b.contains(6));
		points += 1;
		b.insert(6);
		assertTrue(b.contains(6));
		points += 1;
		assertFalse(b.contains(5));
		points += 1;
		b.insert(2); b.insert(1); b.insert(5);
		assertFalse(b.contains(0));
		points += 1;
		assertFalse(b.contains(7));
		points += 1;
		assertTrue(b.contains(6));	
		assertTrue(b.contains(5));	
		assertTrue(b.contains(4));	
		assertTrue(b.contains(2));	
		assertTrue(b.contains(1));	
		points += 1;

		// Test O(log(N)) behavior
		// should run in about 0.3 seconds
		b = new BinarySearchTree<Integer>();
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
		for (int i = 1; i < 1048576; i++) {
			assertTrue(b.contains(i));
		}
		points += 7;
	}
	
	@Test
	public void testSubSet(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals(0, b.subSet(3, 7).size());
		points += 1;
		b.insert(7);
		assertEquals(0, b.subSet(3, 7).size());
		points += 1;
		b.insert(2);
		assertEquals(0, b.subSet(3, 7).size());
		points += 2;
		b.insert(3);
		assertEquals(1, b.subSet(3, 7).size());
		points += 3;
		b.insert(5);b.insert(4);
		assertEquals(3, b.subSet(3, 7).size());
		points += 2;
		b.insert(8);
		assertEquals(3, b.subSet(3, 7).size());
		points += 2;
		assertEquals(0, b.subSet(4, 4).size());
		points += 2;
		
		// testing O(N) behavior
		// should run in about 2.5 seconds
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
		for (int i = 1; i < 50; i++) assertEquals(i-1, b.subSet(1, i).size());
		points += 2;
	}
	
	@Test
	public void testSizeBalanced(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertTrue(b.isSizeBalanced());
		points += 1;
		b.insert(4);
		assertTrue(b.isSizeBalanced());
		points += 1;
		b.insert(6);
		assertTrue(b.isSizeBalanced());
		points += 1;
		b.insert(8);
		assertFalse(b.isSizeBalanced());
		points += 1;
		b.insert(2);
		assertTrue(b.isSizeBalanced());
		points += 1;
		b.insert(7);
		assertFalse(b.isSizeBalanced());
		points += 1;
		b.insert(5);
		b.insert(3);
		assertFalse(b.isSizeBalanced());
		points += 1;
		b.insert(1);
		assertTrue(b.isSizeBalanced());
		points += 1;
		b.insert(0);
		assertTrue(b.isSizeBalanced());
		points += 1;
		
		// testing O(N) behavior
		// should run in about 2.2 seconds
		b = new BinarySearchTree<Integer>();
		int size = 1048576;
		int v = size / 2;
		int temp;
		while (v > 0) {
			temp = v;
			while (temp < size){
				b.insert(temp);
				temp += v;
				}
			assertTrue(b.isSizeBalanced());
			v = v / 2;
		}
		for (int i = 1; i < 250; i++) assertTrue(b.isSizeBalanced());
		points += 7;
		b.insert(1048576);
		assertTrue(b.isSizeBalanced());
		points += 2;
		b.insert(1048577);
		assertFalse(b.isSizeBalanced());
		points += 2;
	}


	@AfterClass
	public static void testNothing(){
		System.out.println("Points: " + points);
	}
	
	
}

