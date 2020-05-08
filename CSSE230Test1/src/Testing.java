import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import org.junit.AfterClass;
import org.junit.Test;

public class Testing {
		
	private static int points = 0;
	
	@Test
	public void testToArray(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals(b.toArray().length, 0);
		points += 2;
		b.insert(0);
		assertEquals(b.toArray().length, 1);
		points += 3;
		for (int i = 1; i < 16; i++) b.insert(i);
		Object[] a = b.toArray();
		for (int i = 0; i < 16; i++) {
			System.out.println(i + " " + a[i]);
			assertEquals(i, a[i]);
		}
		points += 7;
		b = new BinarySearchTree<Integer>();
		b.insert(15);
		b.insert(10);
		b.insert(20);
		b.insert(5);
		b.insert(12);
		b.insert(17);
		b.insert(25);
		b.insert(2);
		b.insert(11);
		b.insert(18);
		b.insert(22);
		a = b.toArray();
		Integer[] aa = {2,5,10,11,12,15,17,18,20,22,25};
		for (int i = 0; i < aa.length; i++) {
			assertEquals(a[i],aa[i]);
		}
		points += 8;
	}

	@Test
	public void testAddAll(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		ArrayList<Integer> a = new ArrayList<Integer>();
		assertFalse(b.addAll(a));
		points += 2;
		for (int i = 1; i < 8; i++) a.add(i);
		assertTrue(b.addAll(a));
		points += 9;
		assertFalse(b.addAll(a));
		points += 2;
		a = new ArrayList<Integer>();
		assertFalse(b.addAll(a));
		points += 2;
	}
	
	@Test
	public void testIsLadder(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertTrue(b.isLadder());
		points += 1;
		b.insert(3);
		assertTrue(b.isLadder());
		points += 2;
		b.insert(7);
		assertTrue(b.isLadder());
		points += 2;
		b.insert(8);
		assertFalse(b.isLadder());
		points += 2;
		b = new BinarySearchTree<Integer>();
		b.insert(3); b.insert(7); b.insert(5);
		assertTrue(b.isLadder());
		points += 2;
		b.insert(8);
		assertFalse(b.isLadder());
		points += 2;
		b = new BinarySearchTree<Integer>();
		b.insert(3); b.insert(7); b.insert(5);b.insert(2);
		assertFalse(b.isLadder());
		points += 2;	
		b = new BinarySearchTree<Integer>();
		b.insert(3); b.insert(7); b.insert(5);b.insert(4);b.insert(6);
		assertFalse(b.isLadder());
		points += 2;	
		b = new BinarySearchTree<Integer>();
		for (int i = 1; i < 50; i++) {
			b.insert(i);
			b.insert(100-i);
		}
		b.insert(50);
		assertTrue(b.isLadder());
		points += 2;
		b = new BinarySearchTree<Integer>();
		for (int i = 1; i < 48; i++) {
			b.insert(i);
			b.insert(100-i);
		}
		b.insert(49);
		b.insert(48);
		b.insert(52);
		b.insert(50);
		b.insert(51);
		assertFalse(b.isLadder());
		points += 3;
	}

	
	@AfterClass
	public static void testNothing(){
		System.out.println("Points: " + points);
	}
	
	
}


