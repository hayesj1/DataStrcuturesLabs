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

	/**
	 * Adds the provided entry to the end of the List
	 * @param newEntry the new entry
	 */
	void add(E newEntry);

	/**
	 * Adds the provided entry to the List and at specified position
	 * @param position the location in the list to place the new entry
	 * @param newEntry the new entry
	 * @throws IndexOutOfBoundsException if either givenPosition < 0 or
	 * givenPosition > getLength()
	 */
	void add(int position, E newEntry);

	/**
	 * Removes the entry at the specified position.
	 * @param position the position of the entry that should be removed
	 * @return the removed entry
	 * @throws IndexOutOfBoundsException if either givenPosition < 0 or
	 * givenPosition >= getLength()
	 */
	E remove(int position);

	/**
	 * Replaces the entry at <code>position</code> with <code>newEntry</code>
	 * @param position the position of the entry to be replaced by <code>newEntry</code>
	 * @param newEntry the newEntry
	 * @return the replaced entry
	 * @throws IndexOutOfBoundsException if either givenPosition < 0 or
	 * givenPosition >= getLength()
	 */
	E replace (int position, E newEntry);

	/**
	 * Gets the entry at the specified position
	 * @param position the position of the entry to get
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
