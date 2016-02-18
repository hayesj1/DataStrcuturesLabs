package ach.queue;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by hayesj3 on 2/18/2016.
 */
public class CircularArrayQueue<T> implements IQueue<T> {

	private T[] queue;
	private int front;
	private int back;
	private int capacity;

	public CircularArrayQueue() { this(DEFAULT_CAPACITY); }

	public CircularArrayQueue(int capacity) {
		this.capacity = capacity;
		queue = (T[]) new Object[capacity];
		front = 0;
	}

	@Override
	public void enqueue(T newEntry) {

	}

	@Override
	public T dequeue() {
		return null;
	}

	@Override
	public T getFront() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return front == (back + 1) % capacity;
	}

	@Override
	public boolean isFull() {
		return front == (back + 2) % capacity;
	}

	@Override
	public void clear() {

	}
}
