package ach.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 * Created by hayesj3 on 4/8/2016.
 */
public class BinaryTree<T> implements IBinaryTree<T> {
	private BinaryNode<T> root;

	public BinaryTree() { root = null; }
	public BinaryTree(T rootData) { root = new BinaryNode<>(rootData); }
	public BinaryTree(T rootData, BinaryTree <T> leftTree, BinaryTree <T> rightTree) {
		privateSetTree(rootData, leftTree, rightTree);
	}

	private void privateSetTree (T rootData, BinaryTree <T> leftTree, BinaryTree <T> rightTree) {
		root = new BinaryNode <> (rootData);

		if (leftTree != null && !leftTree.isEmpty()) {
			root.setLeftChild(leftTree.root);
		}
		if (rightTree != null && !rightTree.isEmpty()) {
			if(rightTree != leftTree) {
				root.setRightChild(rightTree.root);
			} else {
				root.setRightChild(rightTree.root.copy());
			}
		}

		if (leftTree != null && leftTree != this)
			leftTree.clear();
		if (rightTree != null && rightTree != this)
			rightTree.clear();
	}

	@Override
	public void setTree(T rootData) {
		privateSetTree (rootData, null, null);
	}

	@Override
	public void setTree(T rootData, IBinaryTree<T> leftTree, IBinaryTree<T> rightTree) {
		privateSetTree (rootData, (BinaryTree <T>) leftTree, (BinaryTree <T>) rightTree);
	}

	protected void setRoot(BinaryNode<T> root) { this.root = root; }
	protected BinaryNode<T> getRoot() { return this.root; }

	@Override
	public T getRootData() {
		return root.getData();
	}

	@Override
	public int getHeight() {
		return root.getHeight();
	}

	@Override
	public int getNumberOfNodes() {
		return root.getNumberOfNodes();
	}

	@Override
	public boolean isEmpty() {
		return getNumberOfNodes() == 0;
	}

	@Override
	public void clear() {
		root = null;
	}

	@Override
	public Iterator<T> getPreorderIterator() {
		return null;
	}

	@Override
	public Iterator<T> getPostOrderIterator() {
		return null;
	}

	@Override
	public Iterator<T> getInorderIterator() {
		return null;
	}

	@Override
	public Iterator<T> getLevelorderIterator() {
		return null;
	}

	private class PreOrderIterator implements Iterator <T>{
		private BinaryNode<T> currNode;
		private Stack <BinaryNode<T>> nodeStack;

		public PreOrderIterator (){
			currNode = null;
			nodeStack = new Stack<>();
		}

		@Override
		public boolean hasNext() {
			return (currNode != null);
		}

		@Override
		public T next() {
			T data = currNode.getData();

			if (currNode.hasRightChild())
				nodeStack.push((BinaryNode<T>)currNode.getRightChild());
			if (currNode.hasLeftChild())
				currNode = (BinaryNode<T>) currNode.getLeftChild();
			else if (!nodeStack.isEmpty())
				currNode = nodeStack.pop();
			else
				currNode = null;

			return data;
		}

		@Override
		public void remove (){
			throw new UnsupportedOperationException();
		}
	}

	private class InOrderIterator implements Iterator <T> {
		private BinaryNode<T> currNode;
		private Stack <BinaryNode<T>> nodeStack;

		public InOrderIterator (){
			currNode = root; // the source for next()
			nodeStack = new Stack <>();
		}

		@Override
		public boolean hasNext() {
			return (!nodeStack.isEmpty () || currNode != null);
		}

		@Override
		public T next () {
			BinaryNode<T> nextNode = null;

			while (currNode != null){
				nodeStack.push(currNode);
				currNode = (BinaryNode<T>) currNode.getLeftChild();
			}

			if (!nodeStack.isEmpty()) {
				nextNode = nodeStack.pop();
				assert(nextNode != null);
				currNode = (BinaryNode<T>) nextNode.getRightChild();
			}
			else
				throw new NoSuchElementException();
			return nextNode.getData();
		}

		@Override
		public void remove (){
			throw new UnsupportedOperationException();
		}
	}


}
