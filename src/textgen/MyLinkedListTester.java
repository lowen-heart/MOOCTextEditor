/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH = 10;

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
		shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);

	}

	/**
	 * Test if the get method is working correctly.
	 */
	/*
	 * You should not need to add much to this method. We provide it as an
	 * example of a thorough test.
	 */
	@Test
	public void testGet() {
		// test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}

		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));

		try {
			shortList.get(-1);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			shortList.get(shortList.size());
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		// test longer list contents
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			assertEquals("Check " + i + " element", (Integer) i, longerList.get(i));
		}

		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}

	}

	/**
	 * Test removing an element from the list. We've included the example from
	 * the concept challenge. You will want to add more tests.
	 */
	@Test
	public void testRemove() {
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer) 21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());

		// TODO: Add more tests here
//		try {
//			a = emptyList.remove(0);
//			fail("Check null pointer exception");
//		} catch (NullPointerException e) {
//
//		}
		try {
			a = list1.remove(-1);
			fail("Check index out of bounds for negative values");
		} catch (IndexOutOfBoundsException e) {

		}
		
		for(int i = 0; i < list1.size(); i++){
			System.out.println(list1.get(i));
		}
		
		try {
			a = list1.remove(list1.size());
			fail("Check index out of bounds for values over the size");
		} catch (IndexOutOfBoundsException e) {
			assertEquals("Remove: check size", 2, list1.size());
		}

		// test a removal from the end of the list
		a = list1.remove(list1.size() - 1);
		assertEquals("Remove: check a is correct ", 42, a);
		assertEquals("Remove: check element 0 is correct ", (Integer) 21, list1.get(0));
		assertEquals("Remove: check size is correct ", 1, list1.size());

	}

	/**
	 * Test adding an element into the end of the list, specifically public
	 * boolean add(E element)
	 */
	@Test
	public void testAddEnd() {
		// TODO: implement this test
		try {
			emptyList.add(46);
		} catch (NullPointerException e) {
			fail("Add: check add to an empty list");
		}
		// test that adding a null element is returns a NullPointerException
		try {
			list1.add(null);
			fail("Check null pointer exception");
		} catch (NullPointerException e) {

		}
		// test that methods returns true if added correctly
		assertEquals("Add: Check return value true", true, list1.add(5));
		// test last value inserted is correct
		assertEquals("Add: check element added at position 3 is correct", (Integer) 5, list1.get(3));
		assertEquals("Add: check size is correct", 4, list1.size());
		// add another element to the list and test that added correctly at the
		// end
		list1.add(77);
		assertEquals("Add: check if new element at index 4 is added correctly", (Integer) 77, list1.get(4));
		// test element inserted before is not lost
		assertEquals("Add: check if element at index 3 is correct", (Integer) 5, list1.get(3));
		assertEquals("Add: check if element at index 2 is correct", (Integer) 42, list1.get(2));

	}

	/** Test the size of the list */
	@Test
	public void testSize() {
		// TODO: implement this test
		// test that the size is correctly updated
		list1.add(89);
		assertEquals("Size: check size after adding is 4", 4, list1.size());

		list1.remove(list1.size() - 1);
		assertEquals("Size: check size after removing is 3", 3, list1.size());

		emptyList.add(89);
		assertEquals("Size: check size after adding an element to an emptylist is 1", 1, emptyList.size());

		emptyList.remove(0);
		assertEquals("Size: check size after removing to an emptylist is 0", 0, emptyList.size());

	}

	/**
	 * Test adding an element into the list at a specified index, specifically:
	 * public void add(int index, E element)
	 */
	@Test
	public void testAddAtIndex() {
		// TODO: implement this test
		// testing add at the beginning of an empty list
		try {
			emptyList.add(0, 54);
		} catch (NullPointerException e) {
			fail("Add: check that adding to an emptylist do not raise a null pointer exception");
		} catch (IndexOutOfBoundsException e){
			fail("Add: check that adding to an emptylist do not raise an index out of bounds exception");
		}
		// test adding an element not in a too high index
		try {
			list1.add(list1.size()+1, 89);
			fail("Add: check that method do not add over upper bound");
		} catch (IndexOutOfBoundsException e) {

		}
		// test adding an element in an negative index
		try {
			list1.add(-1, 89);
			fail("Add: check that method do not add in a negative index");
		} catch (IndexOutOfBoundsException e) {

		}
		// test that adding a null element is returns a NullPointerException
		try {
			list1.add(null);
			fail("Check null pointer exception");
		} catch (NullPointerException e) {

		}
		// testing correctness of adding at a defined index
		list1.add(1, 89);
		assertEquals("Add: check that 89 is added correctly", (Integer) 89, list1.get(1));
		// testing correctness of shifted elements are still in the list and
		// shifted by one position
		assertEquals("Add: check that 21 is now at index 2", (Integer) 21, list1.get(2));
		// testing correctness of adding at the end of the current list
		list1.add(list1.size - 1, 20);
		assertEquals("Add: check adding at the the end", (Integer) 42, list1.get(list1.size - 1));
		// testing correctness of adding at the beginning of the list
		list1.add(0, 44);
		assertEquals("Add: check adding at the beginning", (Integer) 44, list1.get(0));
		assertEquals("Add: check that former node at index 0 with value 65, is now at index 1", (Integer) 65,
				list1.get(1));
	}

	/** Test setting an element in the list */
	@Test
	public void testSet() {
		// TODO: implement this test
		int value;
		try {
			value = list1.set(-1, 30);
			fail("Set: check index out of bounds negative, too low");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			value = list1.set(list1.size(), 20);
			fail("Set: check index out of bounds over upper bound");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			value = list1.set(0, null);
			fail("Set: check null pointer exception");
		} catch (NullPointerException e) {

		}

		value = list1.set(0, 53);
		assertEquals("Set: check returned value is correct", 65, value);
		assertEquals("Set: check is set correctly at the begin", (Integer) 53, list1.get(0));

		assertEquals("Set: check returned value is correct", (Integer) 21, list1.set(1, 89));
		assertEquals("Set: check is set correctly", (Integer) 89, list1.get(1));

		assertEquals("Set: check returned value is correct", (Integer) 42, list1.set(2, 15));
		assertEquals("Set: check is set correctly at the end", (Integer) 15, list1.get(2));
	}

	// TODO: Optionally add more test methods.

}
