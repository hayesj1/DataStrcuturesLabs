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
public interface IBinaryTree<T> extends ITree<T>, ITreeIterator<T> {
	/** Sets this binary tree to a one-node binary tree
	 *
	 * @param rootData the object in the data of the tree's root
	 */
	void setTree (T rootData);

	/** Set this binary tree to a new binary tree with the given root
	 * and subtreees.
	 *
	 * @param rootData the object in the data of the tree's root
	 * @param leftTree the left subtree of the new tree
	 * @param rightTree the right subtree of the new tree
	 */
	void setTree (T rootData, IBinaryTree<T> leftTree, IBinaryTree<T> rightTree);

}
