package ach.tree;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by hayesj3 on 4/8/2016.
 */
public interface ITree<T> {

	/**
	 * Retrieves the root node's data
	 *
	 * @return root node's data
	 */
	T getRootData();

	/**
	 * Gets number of nodes in the path to the deepest node
	 * @return the number of levels in the tree
	 */
	int getHeight();

	/**
	 * @return the number of nodes in the tree
	 */
	int getNumberOfNodes();

	/**
	 * Tests for emptiness, ie getNumberOfNodes == 0 is true
	 * @return true if there are no nodes in the tree, false otherwise
	 */
	boolean isEmpty();

	/**
	 * deletes all nodes in the tree
	 */
	void clear();

}
