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
public interface IBinaryNode <T> {
	T getData();
	void setData (T newData);
	IBinaryNode <T> getLeftChild();
	IBinaryNode <T> getRightChild();
	void setLeftChild (IBinaryNode <T> leftChild);
	void setRightChild (IBinaryNode <T> rightChild);
	boolean hasLeftChild();
	boolean hasRightChild();
	boolean isLeaf();
	int getNumberOfNodes();
	int getHeight();
	IBinaryNode <T> copy();
}
