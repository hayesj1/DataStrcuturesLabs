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
public interface ITreeIterator<T> {
	/**
	 * @return an Iterator implementing Preorder traversal for a tree
	 */
	Iterator<T> getPreorderIterator();

	/**
	 * @return an Iterator implementing Postorder traversal for a tree
	 */
	Iterator<T> getPostOrderIterator();

	/**
	 * @return an Iterator implementing Inorder traversal for a tree
	 */
	Iterator<T> getInorderIterator();

	/**
	 * @return an Iterator implementing Levelorder traversal for a tree
	 */
	Iterator<T> getLevelorderIterator();

}
