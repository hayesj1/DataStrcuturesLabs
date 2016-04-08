package ach.tree;

import java.util.Iterator;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by hayesj3 on 4/8/2016.
 */
public interface ISearchTree<T extends Comparable <? super T>> extends ITree<T> {

	/** Searches for a specific entry in the tree
	 * @param entry An object to be found
	 * @return true if the object is in the tree; false otherwise
	 */
	boolean contains (T entry);

	/** Retrieves a specific entry in the tree
	 * @param entry An object to be found
	 * @return the object that was found or null, if it was not found
	 */
	T getEntry (T entry);

	/** Adds a new entry to the tree, if it does not match an
	 * existing object;
	 * replaces the existing object with the new entry.
	 * @param newEntry An object to be added
	 * @return null if newEntry was added, and the old entry in case
	 * of replacement
	 */
	T add (T newEntry);

	/** Removes a specific entry from the tree.
	 * @param entry An object to be removed.
	 * @return either the object that was removed, or null if no such
	 * object exists
	 */
	T remove (T entry);

	/** Creates an iterator that traverses all entries in the tree.
	 * @return An iterator that provides sequential and ordered
	 * access to the entries in the tree
	 */
	Iterator<T> getInorderIterator();

}
