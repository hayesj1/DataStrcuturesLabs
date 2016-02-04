package ach.stack;

/**
 * Created by hayesj3 on 2/1/2016.
 */
public interface IStack<T> {

	void push(T entry);
	T pop();
	T peek();
	boolean isEmpty();
	void clear();
}
