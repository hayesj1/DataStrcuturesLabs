package ach.list.iterator;

import java.util.NoSuchElementException;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by hayesj3 on 3/16/2016.
 */
public interface IIterator<E> {

	/** Returns the next element in the iteration.
	 * @return the next element in the iteration
	 * @throws NoSuchElementException if the iteration has no more elements
	 */
	E next() throws NoSuchElementException;

	/** Returns the previous element in the list and moves the cursor
	 * position backwards.
	 * This method may be called repeatedly to iterate through the list
	 * backwards, or intermixed with calls to next() to go back and forth.
	 * (Note that alternating calls to next and previous will return the
	 * same element repeatedly.)
	 * @return the previous element in the list
	 * @throws NoSuchElementException - if the iteration has no previous
	 * element
	 */
	E previous() throws NoSuchElementException;

	/** Returns true if the iteration has more elements.
	 * (In other words, returns true if next would
	 * return an element rather than throwing an exception.)
	 * @return true if the iteration has more elements, false otherwise
	 */
	boolean hasNext();

	/** Returns true if this list iterator has more elements when
	 * traversing the list in the reverse direction. (In other words,
	 * returns true if previous() would return an element rather than
	 * throwing an exception.)
	 * @return true if the list iterator has more elements when
	 * traversing the list in the reverse direction
	 */
	boolean hasPrevious();

	/** Returns the index of the element that would be returned by a
	 * subsequent call to next(). (Returns list size if the list iterator
	 * is at the end of the list.)
	 * @return the index of the element that would be returned by a
	 * subsequent call to next, or list size if the list iterator is at
	 * the end of the list
	 */
	int nextIndex();

	/** Returns the index of the element that would be returned by a
	 * subsequent call to previous(). (Returns -1 if the list iterator is
	 * at the beginning of the list.)
	 * @return the index of the element that would be returned by a
	 * subsequent call to previous, or -1 if the list iterator is at the
	 * beginning of the list
	 */
	int previousIndex();

	/** Removes from the list the last element that was returned by next()
	 * or previous() (optional operation).
	 * Precondition: next() or previous() has been called but remove() or
	 * add() method has not been called. This call can only be made once
	 * per call to next or previous. The list has not been altered during
	 * the iteration except by calls to iterator's remove(), add(), or
	 * set() methods.
	 * @throws UnsupportedOperationException - if the remove operation is
	 * not supported by this list iterator
	 * @throws IllegalStateException - if neither next nor previous have
	 * been called, or remove or add have been called after the last call
	 * to next or previous
	 */
	void remove() throws IllegalStateException;

	/** optional method **/
	void set(E newEntry);

	/** Inserts the specified element into the list (optional operation).
	 * The element is inserted immediately before the element that would
	 * be returned by next(), if any, and after the element that would be
	 * returned by previous(), if any. (If the list contains no elements,
	 * the new element becomes the sole element on the list.) The new
	 * element is inserted before the implicit cursor: a subsequent call
	 * to next would be unaffected, and a subsequent call to previous
	 * would return the new element.(This call increases by one the value
	 * that would be returned by a call to nextIndex or previousIndex.)
	 * @param newEntry an object to be inserted
	 * @throws UnsupportedOperationException - if the add method is not
	 * supported by this list iterator
	 * @throws ClassCastException - if the class of the specified element
	 * prevents it from being added to this list
	 * @throws IllegalArgumentException - if some aspect of this element
	 * prevents it from being added to this list
	 */
	void add(E newEntry);
}
