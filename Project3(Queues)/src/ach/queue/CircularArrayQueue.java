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
		return false;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public void clear() {

	}
}
