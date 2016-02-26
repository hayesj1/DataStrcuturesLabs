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
		queue = (T[]) new Object[capacity+1];
		front = 0;
		back = queue.length-2;
	}

	@Override
	public void enqueue(T newEntry) {
		if(isEmpty()) {
			front = 0;
			back = 0;
			queue[back] = newEntry;
		} else {
			if (isFull()) {
				ensureCapacity();
			}

			back = (back + 1) % capacity;
			queue[back] = newEntry;
		}
	}

	@Override
	public T dequeue() throws EmptyQueueException {
		T entry;
		if(isEmpty()) {
			//front = 0;
			throw new EmptyQueueException("dequeue");
		}
		entry = queue[front];
		queue[front] = null;
		front = (front+1)%capacity;
		return entry;
	}

	@Override
	public T getFront() {
		return queue[front];
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
		for (T entry : queue) {
			entry = null;
		}
		front = 0;
		back = capacity-1;
	}

	private void ensureCapacity() {
		T[] old = queue;
		T[] temp = (T[]) new Object[2*capacity+1];
		int i;
		for (i = 0; i < capacity - 1; i++){
			temp[i] = old[(front + i)%capacity];
			old[(front + i)%capacity] = null;
		}
		//front = 0;
		//back = i-1;
		capacity *= 2;
		queue = temp;
	}
}
