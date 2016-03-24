package ach.list;

import ach.list.iterator.CustomIterable;
import ach.list.iterator.IIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by hayesj3 on 3/16/2016.
 */
public class DLList<E> implements IList<E>, CustomIterable<E> {

	private Node first;
	private Node last;

	private int size = 0;
	private boolean initialized;

	public DLList() {
		first = null;
		last = null;
		size = 0;
		initialized = false;
	}
	private void init(E data) {
		first = new Node(data, null, last);
		last = null;
		size = 1;
		initialized = true;
	}

	@Override
	public void add(E newEntry) {
		if(!initialized || isEmpty()) {
			init(newEntry);
		} else {
			if (last == null) {
				last = new Node(newEntry, first, null);
				first.setNext(last);
			} else {
				Node temp = new Node(newEntry, last, null);
				last.setNext(temp);
				last = temp;
			}
			size++;
		}
	}

	@Override
	public void add(int position, E newEntry) {
		if(!initialized || isEmpty()) {
			init(newEntry);
		} else {
			IIterator<E> iterator = getCustomIterator();
			for (int i = 0; i < position; i++, iterator.next()) ;
			iterator.add(newEntry);
			size++;
		}
	}

	@Override
	public E remove(int position) {
		if(!initialized || isEmpty()) {
			return null;
		} else {
			E data = null;
			IIterator<E> iterator = getCustomIterator();
			for (int i = 0; i < position; i++, iterator.next()) ;
			data = iterator.next();
			iterator.remove();
			size--;
			return data;
		}

	}

	@Override
	public E replace(int position, E newEntry) {
		if(!initialized || isEmpty()) {
			return null;
		} else {
			E data = null;
			IIterator<E> iterator = getCustomIterator();
			for (int i = 0; i < position; i++, iterator.next()) ;
			data = iterator.next();
			iterator.set(newEntry);
			return data;
		}
	}

	@Override
	public E getEntry(int position) {
		if(!initialized || isEmpty()) {
			return null;
		} else {
			IIterator<E> iterator = getCustomIterator();
			for (int i = 0; i < position - 1; i++, iterator.next()) ;
			E data = iterator.next();
			return data;
		}
	}

	@Override
	public boolean contains(E value) {
		boolean found = false;
		IIterator<E> iterator = getCustomIterator();
		try {
			for (int i = 0; !found && i < getLength() + 1; i++) {
				E val = iterator.next();
				if (val.equals(value)) {
					found = true;
				}
			}
		} catch (NoSuchElementException nsee) {} //do nothing if end of the list is reached

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
		initialized = false;
	}

	@Override
	public E[] toArray() {
		E[] arr = (E[]) new Object[size];
		IIterator<E> iterator = getCustomIterator();

		for (int i = 0; i < size -1; i++) { arr[i] = iterator.next(); }

		return arr;
	}

	@Override
	public int getLength() { return size; }

	@Override
	public IIterator<E> getCustomIterator() {
		return new ListIterator();
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

	public class ListIterator implements Iterator<E>, IIterator<E> {
		private Node currNode;
		private int currIdx;
		private boolean hasNextBeenCalled;
		private boolean hasPrevBeenCalled;

		public ListIterator() {
			this.currNode = null;
			this.currIdx = -1;
			this.hasNextBeenCalled = false;
			hasPrevBeenCalled = false;
		}


		@Override
		public E next() throws NoSuchElementException {
			Node temp;
			if(currNode == null) {
				temp = first;
			} else {
				temp = currNode.getNext();
			}
			E data = temp.getData();
			currNode = temp;
			currIdx++;
			hasNextBeenCalled = true;
			return data;
		}

		@Override
		public E previous() throws NoSuchElementException {
			E data = currNode.getPrev().getData();
			currNode = currNode.getPrev();
			currIdx--;
			hasPrevBeenCalled = true;
			return data;
		}

		@Override
		public boolean hasNext() {
			return currIdx < getLength() || currNode.getNext() != null;
		}
		@Override
		public boolean hasPrevious() {
			return currIdx > 0 || currNode.getPrev() != null;
		}

		@Override
		public int nextIndex() {
			return currIdx+1;
		}

		@Override
		public int previousIndex() {
			return currIdx-1;
		}

		@Override
		public void remove() throws IllegalStateException {
			//E data = null;                     //uncomment to return removed node's data
			if (hasNextBeenCalled || hasPrevBeenCalled) {
				currNode.getNext().setPrev(currNode.getPrev());
				currNode.getPrev().setNext(currNode.getNext());
				//data = currNode.getData();    //uncomment to return removed node's data
				currNode = currNode.getNext();
				hasNextBeenCalled = false;
				hasPrevBeenCalled = false;
			} else { throw new IllegalStateException(); }

			//return data;                      //uncomment to return removed node's data
		}

		@Override
		public void set(E newEntry) {
			currNode.setData(newEntry);
		}

		@Override
		public void add(E newEntry) {
			Node temp = new Node(newEntry, currNode.getPrev(), currNode.getNext());
			currNode.getPrev().setNext(temp);
			currNode.getNext().setPrev(temp);
			currIdx++;
		}
	}
}