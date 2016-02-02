package ach.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
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
		this.entries[this.entries.length - ++pos] = entry;
		if (this.pos >= this.entries.length) { doubleCapacity(); }
		this.bottom = this.entries[this.entries.length - pos];
	}

	@Override
	public T pop() {
		if(isEmpty()) { throw new EmptyStackException(); }
		T entry = this.entries[this.entries.length - pos];
		pos--;
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
		return (this.pos < 1);
	}

	@Override
	public void clear() {
		if(isEmpty()) { throw new EmptyStackException(); }
		this.entries = (T[]) new Object[DEFAULT_CAPACITY];
		this.bottom = null;
		this.pos = 0;
	}

	private void doubleCapacity() {
		Arrays.copyOf(this.entries, this.capacity * 2);
		this.capacity = this.capacity * 2;
	}

	public static int getDefaultCapacity() { return DEFAULT_CAPACITY; }
}
