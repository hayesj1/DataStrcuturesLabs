package ach.list;

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
 *         Created by hayesj3 on 3/18/2016.
 */
public class DoubleLinkedList<E> implements IList<E>, Iterable<E> {

	private Node first;
	private Node last;

	private int size = 0;
	private boolean initialized;

	public DoubleLinkedList() {
		first = null;
		last = null;
		size = 0;
		initialized = false;
	}
	private void init() {
		first = new Node(null, null, null);
		last = new Node(null, first, null);
		first.setNext(last);
		initialized = true;
	}

	@Override
	public void add(E newEntry) {
		if(!initialized || isEmpty()) {
			init();
			first.setData(newEntry);
			size++;
		} else {
			if(size > 1) {
				Node temp = new Node(newEntry, last, null);
				last.setNext(temp);
				last = temp;
				size++;
			} else {
				last.setData(newEntry);
				size++;
			}
		}
	}

	@Override
	public void add(int position, E newEntry) {
		if(position < 0 || position > size-1) {
			throw new IndexOutOfBoundsException("Index " + position + ", is > for max index " + (size-1) + ", or is < 0");
		}
		if (!initialized || isEmpty()) {
			init();
			first.setData(newEntry);
			size++;
		} else if (position == size - 1) {
			last.setData(newEntry);
		} else {
			ListIterator iterator = new ListIterator();
			for (int i = 0; i < position; i++, iterator.next()) ;
			iterator.add(newEntry);
			size++;
		}
	}

	@Override
	public E remove(int position) {
		E data = null;
		if(!initialized || isEmpty()) {
			return null;
		} else if (position == 0) {
			data = first.getData();
			first = first.getNext();
			first.setPrev(null);
		} else if (position == size-1) {
			data = last.getData();
			last = last.getPrev();
			last.setNext(null);
		} else {
			ListIterator iterator = new ListIterator();
			for (int i = 0; i < position - 1; i++, iterator.next()) ;
			data = iterator.next();
			iterator.remove();
			size--;
			if(size == 0) {
				first = null;
				last = null;
				initialized = false;
			}
		}

		return data;
	}

	@Override
	public E replace(int position, E newEntry) {
		if(!initialized || isEmpty()) {
			return null;
		} else {
			E data = null;
			ListIterator iterator = new ListIterator();
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
			ListIterator iterator = new ListIterator();
			for (int i = 0; i < position; i++, iterator.next()) ;
			return iterator.next();
		}
	}

	@Override
	public boolean contains(E value) {
		if(isEmpty()) {
			return false;
		}
		boolean found = false;
		Iterator it = iterator();
		try {
			for (int i = 0; !found && i < getLength() + 1; i++) {
				if (it.next().equals(value)) {
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
		E[] arr = (E[]) new Object[getLength()];
		ListIterator iterator = (ListIterator) this.iterator();

		for (int i = 0; i < getLength(); i++) {
			arr[i] = iterator.next();
		}

		return arr;
	}

	@Override
	public int getLength() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		DoubleLinkedList<E>.ListIterator ret = new DoubleLinkedList<E>.ListIterator();
		return ret;
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
			this.currNode = first;
			this.currIdx = -1;
			this.hasNextBeenCalled = false;
			hasPrevBeenCalled = false;
		}


		@Override
		public E next() throws NoSuchElementException {
			E data = null;
			if(currIdx < 0) {
				data = this.currNode.getData();
				currIdx++;
				hasNextBeenCalled = true;
			} else if(!hasNext()) {
				throw new NoSuchElementException();
			} else {
				data = currNode.getNext().getData();
				currNode = currNode.getNext();
				currIdx++;
				hasNextBeenCalled = true;
			}
			return data;
		}

		@Override
		public E previous() throws NoSuchElementException {
			E data = null;
			if(hasPrevious()) {
				data = currNode.getPrev().getData();
				currNode = currNode.getPrev();
				currIdx--;
				hasPrevBeenCalled = true;
			} else {
				throw new NoSuchElementException();
			}
			return data;
		}

		@Override
		public boolean hasNext() {
			return currNode.getNext() != null && currIdx < getLength();
		}
		@Override
		public boolean hasPrevious() {
			return currNode.getPrev() != null && currIdx > 0;
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
			//T data = null;                     //uncomment to return removed node's data
			if (hasNextBeenCalled || hasPrevBeenCalled) {
				if (hasNext()) { currNode.getNext().setPrev(currNode.getPrev()); }
				if(hasPrevious()) { currNode.getPrev().setNext(currNode.getNext()); }
				currIdx--;
				//data = currNode.getData();    //uncomment to return removed node's data
				//currNode = currNode.getNext();
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
