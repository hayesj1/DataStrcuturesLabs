package ach.queue;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *
 * Created by hayesj3 on 2/12/2016.
 */
public interface IQueue<T> {

	int DEFAULT_CAPACITY = 25;

	void enqueue(T newEntry);
	T dequeue();

	T getFront();

	boolean isEmpty();
	boolean isFull();
	void clear();
}
