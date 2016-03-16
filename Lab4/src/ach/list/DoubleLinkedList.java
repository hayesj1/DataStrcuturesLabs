package ach.list;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by hayesj3 on 3/16/2016.
 */
public class DoubleLinkedList<E> implements ILinkedList<E>{

	Node first;
	Node last;

	int size = 0;

	public DoubleLinkedList() {
		first = new Node(null, null, null);
		last = first;
	}

	@Override
	public void add(E newEntry) {
		Node node = new Node(newEntry, last, null);
		last = node;
		size++;
	}

	@Override
	public void add(int position, E newEntry) {
		if(position == size-1) { add(newEntry); }
		else {
			size++;
		}
	}

	@Override
	public E remove(int position) {
		E ret = null;

		size--;
		return ret;
	}

	@Override
	public E replace(int position, E newEntry) {
		E ret = null;

		return ret;
	}

	@Override
	public E getEntry(int position) {
		return null;
	}

	@Override
	public boolean contains() {
		boolean found = false;
		return found;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		first = null;
		last = null;
		size = 0;
	}

	@Override
	public E[] toArray() {
		E[] arr = null;
		return arr;
	}

	@Override
	public int getLength() {
		return size;
	}

	class Node {
		E data;
		Node prev;
		Node next;

		public Node(E data, Node prev, Node next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}

		public E getData() { return data; }
		public Node getPrev() { return prev; }
		public Node getNext() { return next; }

		public void setData(E data) { this.data = data; }
		public void setPrev(Node prev) { this.prev = prev; }
		public void setNext(Node next) { this.next = next; }
	}
}