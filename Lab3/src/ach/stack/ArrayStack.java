package ach.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 *
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * A reversed array implementation of Stack(ex. Builds from the highest index down to the lowest)
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *
 * Created by hayesj3 on 2/1/2016.
 */
public class ArrayStack<T> implements IStack<T> {

	public static final int DEFAULT_CAPACITY = 25;

	private T bottom;
	private T[] entries;
	private int pos;
	private int capacity;

	public ArrayStack() { this(DEFAULT_CAPACITY); }
	public ArrayStack(int initialCapacity) {
		capacity = initialCapacity;
		entries = (T[]) new Object[capacity];
		pos = 0;
	}

	/**
	 * Convenience constructor for testing.
	 * @param original the array to build a stack from
	 */
	public ArrayStack(T[] original) {
		capacity = original.length;
		entries = (T[]) new Object[capacity];
		pos = 0;

		for (int i = 0; i < original.length; i++) {
			push(original[i]);
		}
	}

	/**
	 * Pushes entry onto the stack
	 * @param entry the entry to push
	 */
	@Override
	public void push(T entry) {
		this.entries[this.entries.length - ++pos] = entry;
		if (this.pos >= this.entries.length) { doubleCapacity(); }
		this.bottom = this.entries[this.entries.length - pos];
	}

	/**
	 * pops the bottom item from the stack
	 * @return the popped entry
	 */
	@Override
	public T pop() {
		if(isEmpty()) { throw new EmptyStackException(); }
		T entry = this.entries[this.entries.length - pos];
		pos--;
		if(isEmpty()) { this.bottom = null; }
		else { this.bottom = this.entries[this.entries.length - pos]; }
		return entry;
	}

	/**
	 * Looks at the bottom entry without removing it
	 * @return the item on the bottom of the stack
	 */
	@Override
	public T peek() {
		if(isEmpty()) { throw new EmptyStackException(); }
		return this.bottom;
	}

	/**
	 * Tests for emptyness
	 * @return true if the stack is empty; false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return (this.pos < 1);
	}

	/**
	 * Clears all entries from the stack
	 */
	@Override
	public void clear() {
		if(isEmpty()) { throw new EmptyStackException(); }
		this.entries = (T[]) new Object[DEFAULT_CAPACITY];
		this.bottom = null;
		this.pos = 0;
	}

	/**
	 * doubles the capacity of the backing-array
	 */
	private void doubleCapacity() {
		Arrays.copyOf(this.entries, this.capacity * 2);
		this.capacity = this.capacity * 2;
	}
}
