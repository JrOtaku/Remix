import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

import org.junit.AfterClass;
import org.junit.Test;

public class Testing {
		
	private static int points = 0;
	
	@Test
	public void testHigher(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals(null, b.higher(6));
		points += 1;
		b.insert(4);
		assertEquals(null, b.higher(6));
		points += 1;
		b.insert(6);
		assertEquals(null, b.higher(6));
		points += 1;
		assertEquals(new Integer(6), b.higher(4));
		points += 1;		
		assertEquals(new Integer(6), b.higher(5));
		points += 2;
		b.insert(2); b.insert(1); b.insert(3); b.insert(5); b.insert(7);
		assertEquals(new Integer(4), b.higher(3));
		points += 2;
		assertEquals(new Integer(3), b.higher(2));
		points += 2;
		assertEquals(new Integer(2), b.higher(1));
		points += 2;
		
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
		assertEquals(new Integer(2), b.higher(1));
		points += 2;
		assertEquals(null, b.higher(1048575));
		points += 2;
		assertEquals(new Integer(1048575), b.higher(1048574));
		points += 2;		
		assertEquals(new Integer(524288), b.higher(524287));
		points += 2;		
	}
	
	@Test
	public void testContainsAll(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		ArrayList<Integer> t = new ArrayList<Integer>();		
		assertTrue(b.containsAll(t));
		points += 1;
		b.insert(40); b.insert(60); b.insert(20); b.insert(10); b.insert(30); b.insert(50); b.insert(70);
		assertTrue(b.containsAll(t));
		points += 1;
		t.add(40);		
		assertTrue(b.containsAll(t));
		points += 1;
		t = new ArrayList<Integer>();
		t.add(20); t.add(30); t.add(40);
		assertTrue(b.containsAll(t));
		points += 2;
		t.add(80);
		assertFalse(b.containsAll(t));
		points += 2;
		t = new ArrayList<Integer>();
		t.add(0); t.add(20); t.add(30); t.add(40);
		assertFalse(b.containsAll(t));
		points += 2;
		
		b = new BinarySearchTree<Integer>();
		t = new ArrayList<Integer>();
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
		for (int i = 1; i < 1048576; i++) t.add(i);
		assertTrue(b.containsAll(t));
		t.add(1048576);
		assertFalse(b.containsAll(t));
		t.add(0,0);
		assertFalse(b.containsAll(t));
		points += 11;
	}
	
	@Test
	public void testConstructor() {
		Graph<Integer> g = new Graph<Integer>();
		assertEquals("", new BinarySearchTree(g).simpleToString());
		points += 1;
		for (int i = 1; i < 8; i++) {
			   g.addNode(i);
		}
		// no edges, as such no children, just 1 as root
		assertEquals("1 ", new BinarySearchTree(g).simpleToString());
		points += 1;
		
		g.addEdge(3,2,1);
		g.addEdge(4,3,1);
		g.addEdge(5,4,1);
		g.addEdge(6,5,1);
		// a couple of edges, but none from node 1, a such, tree has just 1 as root
		assertEquals("1 ", new BinarySearchTree(g).simpleToString());
		points += 3;
		
		g.addEdge(1,6,1);
		// now a tree, but without 7
		assertEquals("1 6 5 4 3 2 ", new BinarySearchTree(g).simpleToString());
		points += 5;		
		
		g.addEdge(1,7,1);
		// now all nodes are in tree
		assertEquals("1 6 5 4 3 2 7 ", new BinarySearchTree(g).simpleToString());
		points += 5;
		g = new Graph<Integer>();
		String s = "";
		for (int i = 1; i < 5001; i++) {
			   g.addNode(i);
			   s = s.concat(i + " ");
		}
		for (int i = 1; i < 5000; i++) {
			   g.addEdge(i,i+1,1);
		}
		assertEquals(s, new BinarySearchTree(g).simpleToString());
		points += 5;
	}

	@AfterClass
	public static void testNothing(){
		System.out.println("Points: " + points);
	}
	
	
}

