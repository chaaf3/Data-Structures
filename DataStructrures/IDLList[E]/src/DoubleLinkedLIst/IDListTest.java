package DoubleLinkedLIst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class IDListTest {
	@BeforeAll
	static void MakeDLL() {
		IDList<Integer> tester = new IDList<Integer>();
		tester.add(1,0);
		tester.append(4);
	
	}
	@Test
	void testAdd() {
		IDList<Integer> tester = new IDList<Integer>();
		
		assertEquals(true, tester.add(0, 1));
		assertEquals(false, tester.add(-2, 7));
	}

	@Test
	void testAppend() {
		IDList<Integer> tester = new IDList<Integer>();
		assertEquals(true,tester.append(5));
	}

	@Test
	void testGet() {
		IDList<Integer> tester = new IDList<Integer>();
		tester.add(1,0);
		tester.append(4);
		assertEquals(1, (Integer)tester.get(0));
	}

	@Test
	void testGetHead() {
		IDList<Integer> tester = new IDList<Integer>();
		tester.add(1,0);
		tester.append(4);
		assertEquals(1, tester.getHead());
	}

	@Test
	void testGetLast() {
		IDList<Integer> tester = new IDList<Integer>();
		tester.add(1,0);
		tester.append(4);
		assertEquals()
	}

	@Test
	void testSize() {
		fail("Not yet implemented");
	}

	@Test
	void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveLast() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveAt() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveE() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	@Test
	void testMain() {
		fail("Not yet implemented");
	}

}
