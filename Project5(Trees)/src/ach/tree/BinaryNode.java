package ach.tree;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 * Created by hayesj3 on 4/8/2016.
 */
class BinaryNode <T> implements IBinaryNode<T> {
	private T data;
	private int size;
	private BinaryNode<T> leftChild;
	private BinaryNode<T> rightChild;

	public BinaryNode () {
		this (null);
	}

	public BinaryNode (T rootData) { this (rootData, null, null); }

	public BinaryNode (T rootData, BinaryNode <T> newLeftChild, BinaryNode <T> newRightChild) {
		data = rootData;
		leftChild = newLeftChild;
		rightChild = newRightChild;

		this.size = computeNumberOfNodes(newLeftChild) + computeNumberOfNodes(newRightChild) + 1;
	}

	@Override
	public T getData() {
		return data;
	}

	@Override
	public void setData (T newData) {
		data = newData;
	}

	@Override
	public IBinaryNode<T> getLeftChild () {
		return leftChild;
	}

	@Override
	public IBinaryNode<T> getRightChild() {
		return rightChild;
	}

	@Override
	public void setLeftChild(IBinaryNode<T> leftChild) {
		this.leftChild = (BinaryNode<T>) leftChild;
		this.size += computeNumberOfNodes(leftChild);
	}

	@Override
	public void setRightChild(IBinaryNode<T> rightChild) {
		this.rightChild = (BinaryNode<T>) rightChild;
		this.size += computeNumberOfNodes(rightChild);
	}

	@Override
	public boolean hasLeftChild() {
		return leftChild != null;
	}

	@Override
	public boolean hasRightChild() {
		return rightChild != null;
	}

	@Override
	public boolean isLeaf () {
		return (leftChild == null && rightChild == null);
	}

	public int getNumberOfNodes() {
		return size;
	}

	@Override
	public int getHeight () {
		return 0;
	}

	@Override
	public IBinaryNode <T> copy() {
		BinaryNode <T> newRoot = new BinaryNode <> (data);

		if (leftChild != null )
			newRoot.leftChild = (BinaryNode<T>)leftChild.copy();
		if (rightChild != null)
			newRoot.rightChild = (BinaryNode<T>)rightChild.copy();

		return newRoot;
	}


	private static int computeNumberOfNodes(IBinaryNode node) {
		int ret = 0;
		if(node == null) {
			ret = 0;
		} else if(node.isLeaf()) {
			ret = 1;
		} else {
			if (node.hasLeftChild()) {
				ret += computeNumberOfNodes(node.getLeftChild());
			}
			if (node.hasRightChild()) {
				ret += computeNumberOfNodes(node.getRightChild());
			}
		}
		return ret;
	}
}

