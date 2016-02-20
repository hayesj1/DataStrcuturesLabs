package ach.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
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
	/**
	 * marks the next free space
	 */
	private int pos;
	private int capacity;

	public ArrayStack() { this(DEFAULT_CAPACITY); }
	public ArrayStack(int initialCapacity) {
		capacity = initialCapacity;
		entries = (T[]) new Object[capacity];
		pos = 0;
	}
	public ArrayStack(T[] original) {
		capacity = original.length;
		entries = (T[]) new Object[capacity];
		pos = 0;

		for (int i = 0; i < original.length; i++) {
			push(original[i]);
		}
	}

	@Override
	public void push(T entry) {
		if(isEmpty()) { pos++; }
		this.entries[this.entries.length - pos] = entry;
		if (this.pos + 1 >= this.entries.length) { doubleCapacity(); }
		this.bottom = this.entries[this.entries.length - pos++];
	}

	@Override
	public T pop() {
		if(isEmpty()) { throw new EmptyStackException(); }
		T entry = this.entries[this.entries.length - --pos];
		if(isEmpty()) { this.bottom = null; }
		else { this.bottom = this.entries[this.entries.length - pos]; }
		return entry;
	}

	@Override
	public T peek() {
		if(isEmpty()) { throw new EmptyStackException(); }
		return this.bottom;
	}

	@Override
	public boolean isEmpty() {
		return (this.pos <= 1);
	}

	@Override
	public void clear() {
		if(isEmpty()) { throw new EmptyStackException(); }
		this.entries = (T[]) new Object[DEFAULT_CAPACITY];
		this.bottom = null;
		this.pos = 0;
	}

	private void doubleCapacity() {
		T[] temp = Arrays.copyOf(this.entries, this.capacity * 2);
		T[] old = this.entries;

		for (this.pos = 1; pos < old.length; pos++) {
			temp[temp.length - pos] = old[old.length-pos];
		}

		this.capacity = this.capacity * 2;
		this.entries = temp;
	}

	public static int getDefaultCapacity() { return DEFAULT_CAPACITY; }
}
