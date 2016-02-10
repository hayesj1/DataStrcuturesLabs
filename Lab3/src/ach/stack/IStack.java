package ach.stack;

/**
 *
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *
 * Created by hayesj3 on 2/1/2016.
 */
public interface IStack<T> {

	/**
	 * Pushes entry onto the stack
	 * @param entry the entry to push
	 */
	void push(T entry);

	/**
	 * pops the bottom item from the stack
	 * @return the popped entry
	 */
	T pop();

	/**
	 * Looks at the bottom entry without removing it
	 * @return the item on the bottom of the stack
	 */
	T peek();

	/**
	 * Tests for emptyness
	 * @return true if the stack is empty; false otherwise
	 */
	boolean isEmpty();

	/**
	 * Clears all entries from the stack
	 */
	void clear();
}
