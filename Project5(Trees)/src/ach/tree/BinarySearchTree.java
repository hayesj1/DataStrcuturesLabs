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
public class BinarySearchTree <T extends Comparable < ? super T>> extends BinaryTree<T> implements ISearchTree<T> {

	public BinarySearchTree() { super(); }
	public BinarySearchTree(T rootEntry){ super(rootEntry); }

	@Override
	public boolean contains(T entry) {
		return (getEntry(entry) != null);
	}

	@Override
	public T getEntry(T entry) {
		return findEntry(getRoot(), entry);
	}

	@Override
	public T add(T newEntry) {
		T result = null;
		if (isEmpty()) {
			setRoot(new BinaryNode<>(newEntry));
		}
		else {
			result = addEntry(getRoot(), newEntry);
		}
		return result;

	}

	@Override
	public T remove(T entry) {
		ReturnObject oldEntry = new ReturnObject();
		BinaryNode<T> newRoot = removeEntry(getRoot(), entry, oldEntry);
		setRoot(newRoot);
		return oldEntry.get();

	}

	protected T findEntry(BinaryNode<T> rootNode, T entry) {
		T result = null;
		if (rootNode != null) {
			T rootEntry = rootNode.getData();
			if (entry.equals(rootEntry))
				result = rootEntry;
			else if (entry.compareTo(rootEntry) < 0)
				result = findEntry((BinaryNode<T>)rootNode.getLeftChild(), entry);
			else
				result = findEntry((BinaryNode<T>)rootNode.getRightChild(), entry);
		}
		return result;
	}

	protected T addEntry(BinaryNode<T> root, T newEntry) {
		assert root != null;
		T result = null;
		int comp = newEntry.compareTo(root.getData());

		if (comp == 0) {
			result = root.getData();
			root.setData(newEntry);
		} else if (comp < 0) {
			if (root.hasLeftChild()) {
				result = addEntry((BinaryNode<T>) root.getLeftChild(), newEntry);
			} else {
				root.setLeftChild(new BinaryNode<>(newEntry));
			}
		} else {
			if (root.hasRightChild()) {
				result = addEntry((BinaryNode<T>) root.getRightChild(),
						newEntry);
			} else {
				root.setRightChild(new BinaryNode<>(newEntry));
			}
		}
		return result;
	}

	protected BinaryNode<T> removeEntry(BinaryNode<T> root, T entry, ReturnObject ret) {
		if (root != null){
			T rootData = root.getData();
			int comparison = entry.compareTo (rootData);
			if (comparison == 0) { // entry maches root entry
				ret.set(rootData);
				root = removeFromRoot(root);
			} else if (comparison < 0){
				BinaryNode<T> leftChild = (BinaryNode<T>) root.getLeftChild();
				BinaryNode<T> subTreeRoot = removeEntry(leftChild, entry, ret);
				root.setLeftChild (subTreeRoot);
			} else {
				BinaryNode<T> rightChild = (BinaryNode<T>) root.getRightChild();
				BinaryNode<T> subTreeRoot = removeEntry(rightChild, entry, ret);
				root.setRightChild (subTreeRoot);
			}
		}
		return root;
	}

	protected BinaryNode<T> removeFromRoot (BinaryNode<T> rootNode) {
		ReturnObject oldEntry = new ReturnObject();
		boolean hasLeft = rootNode.hasLeftChild();
		boolean hasRight = rootNode.hasRightChild();
		if (!hasLeft && !hasRight) { return null; }                              // is Leaf
		else if (!hasLeft) { return (BinaryNode<T>) rootNode.getRightChild(); }  // has Right
		else if (!hasRight) { return (BinaryNode<T>) rootNode.getRightChild(); } // has Left
		else {                                                                   // has two children
			BinaryNode<T> largest = (BinaryNode<T>) rootNode.getLeftChild();
			BinaryNode<T> largestChild = (BinaryNode<T>) largest.getRightChild();

			while (largestChild != null){
				largest = largestChild;
				largestChild = (BinaryNode<T>) largestChild.getRightChild();
			}

			rootNode.setData (largest.getData());
			rootNode.setLeftChild(removeEntry(largest, largest.getData(), oldEntry));
			return rootNode;
		}
	}

	protected class ReturnObject {
		BinaryNode<T> data = new BinaryNode<>(null);

		public T get() { return data.getData(); }
		public void set(T data) { this.data.setData(data); }
	}

	@Override
	public void setTree(T rootData ) { throw new UnsupportedOperationException(); }
	@Override
	public void setTree(T rootData, IBinaryTree<T> leftTree, IBinaryTree<T> rightTree) { throw new UnsupportedOperationException(); }

}
