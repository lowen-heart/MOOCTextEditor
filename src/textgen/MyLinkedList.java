package textgen;

import java.util.AbstractList;

/**
 * A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E>
 *            The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		// sentinel nodes
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		// point the next node head to tail reference and tail previous to head
		// reference
		head.next = tail;
		tail.prev = head;

	}

	/**
	 * Appends an element to the end of the list
	 * 
	 * @param element
	 *            The element to add
	 */
	public boolean add(E element) {
		// TODO: Implement this method
		if (element.equals(null)) {
			throw new NullPointerException("Null values are not allowed");
		}
		// Create the new node to insert
		LLNode<E> newNode = new LLNode<E>((E) element);
		// next element of newNode points to tail and previous to the previous
		// of tail
		newNode.next = tail;
		newNode.prev = tail.prev;
		// update tail previous node next value to the newNode and previous tail
		// node to newNode
		tail.prev.next = newNode;
		tail.prev = newNode;
		// add 1 to size
		this.size += 1;
		return true;
	}

	/**
	 * Get the element at position index
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E get(int index) {
		// TODO: Implement this method.
		if (index < 0 || index > (this.size - 1)) {
			throw new IndexOutOfBoundsException("Index passed is out of bounds.");
		}
		E e;
		LLNode<E> currentNode = head.next;
		for (int i = 0; i < size; i++) {
			if (i == index) {
				e = (E) currentNode.data;
				return e;
			}
			currentNode = currentNode.next;
		}
		return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * 
	 * @param The
	 *            index where the element should be added
	 * @param element
	 *            The element to add
	 */
	public void add(int index, E element) {
		// TODO: Implement this method
		if (element.equals(null)) {
			throw new NullPointerException();
		}
		if (this.size == 0 && index == 0) {
			add(element);
		} else if (index < 0 || index > (this.size)) {
			throw new IndexOutOfBoundsException();
		} else {
			// go through the list and find the node at index to change position
			LLNode<E> currentNode = head.next;
			for (int i = 0; i < size; i++) {
				if (i == index) {
					LLNode<E> node = new LLNode<E>((E) element);
					node.prev = currentNode.prev;
					node.next = currentNode;
					currentNode.prev.next = node;
					currentNode.prev = node;
					this.size += 1;
					// break;
				}
				currentNode = currentNode.next;
			}
		}
	}

	/** Return the size of the list */
	public int size() {
		// TODO: Implement this method
		return size;
	}

	/**
	 * Remove a node at the specified index and return its data element.
	 * 
	 * @param index
	 *            The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException
	 *             If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {
		// TODO: Implement this method
		if (this.size == 0) {
			throw new NullPointerException();
		}
		if (index < 0 | index > (this.size - 1)) {
			throw new IndexOutOfBoundsException();
		}
		E value = null;
		LLNode<E> currentNode = head.next;
		for (int i = 0; i < size; i++) {
			if (i == index) {
				value = currentNode.data;
				currentNode.prev.next = currentNode.next;
				currentNode.next.prev = currentNode.prev;
				size--;
				// break;
			}
			currentNode = currentNode.next;
		}
		return value;
	}

	/**
	 * Set an index position in the list to a new element
	 * 
	 * @param index
	 *            The index of the element to change
	 * @param element
	 *            The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E set(int index, E element) {
		// TODO: Implement this method
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		if (element.equals(null)) {
			throw new NullPointerException();
		}
		E value = null;
		LLNode<E> currentNode = head.next;
		for (int i = 0; i < size; i++) {
			if (i == index) {
				value = currentNode.data;
				currentNode.data = element;
				break;
			}
			currentNode = currentNode.next;
		}
		return value;
	}
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
