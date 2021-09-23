package trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



class TreapTest {
	
	@Test
	
	void testAdd() {
	Treap<Integer> testTree = new Treap<Integer>();
	assertEquals(true, testTree.add(4,19));
	assertEquals(true, testTree.add(2, 31));
	assertEquals(true, testTree.add(5, 70));
	}
	void testDelete() {
	Treap<Integer> testTree = new Treap<Integer>();
	testTree.add(4, 19);
	testTree.add(2, 31);
	testTree.add(6, 70);
	testTree.add(1, 84);
	testTree.add(3, 12);
	testTree.add(5, 83);
	testTree.add(7, 26);
	assertEquals(true, testTree.delete(4));
	assertEquals(true, testTree.delete(3));
	assertEquals(false, testTree.delete(9));
	}
	void testFind() {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4, 19);
		testTree.add(2, 31);
		testTree.add(6, 70);
		testTree.add(1, 84);
		testTree.add(3, 12);
		testTree.add(5, 83);
		testTree.add(7, 26);
		assertEquals(true, testTree.find(4));
		assertEquals(true, testTree.find(3));
		assertEquals(false, testTree.find(9));
	}
}
