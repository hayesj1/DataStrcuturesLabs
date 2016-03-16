package ach.list;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *
 * Created by hayesj3 on 3/16/2016.
 */
public interface IList<E> {

	void add(E newEntry);

	/**
	 *
	 * @param position
	 * @param newEntry
	 * @throws IndexOutOfBoundsException if either givenPosition < 0 or
	 * givenPosition > getLength()
	 */
	void add(int position, E newEntry);

	/**
	 *
	 * @param position
	 * @return the removed entry
	 * @throws IndexOutOfBoundsException if either givenPosition < 0 or
	 * givenPosition >= getLength()
	 */
	E remove(int position);

	/**
	 *
	 * @param position
	 * @return the replaced entry
	 * @throws IndexOutOfBoundsException if either givenPosition < 0 or
	 * givenPosition >= getLength()
	 */
	E replace (int position);

	/**
	 *
	 * @param position
	 * @return the entry at givenPosition
	 * @throws IndexOutOfBoundsException if either givenPosition < 0 or
	 * givenPosition >= getLength()
	 */
	E getEntry(int position);

	boolean contains();
	boolean isEmpty();

	void clear();
	E[] toArray();

	int getLength();
}
