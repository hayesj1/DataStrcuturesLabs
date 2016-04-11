package ach.tree;

import ach.main.Point;
import ach.main.PointComparator;

import java.util.Comparator;

/**
 * Implements a KD-Tree, with K = 2.
 *
 *
 *
 *
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 * Created by hayesj3 on 4/8/2016.
 */
public class KDTree<T extends Point> extends BinarySearchTree<T> {

	private Comparator<Point> xComp = PointComparator.xComp;
	private Comparator<Point> yComp = PointComparator.yComp;

	public KDTree(T point) {
		super(point);
	}

	private boolean isEvenLevel(int level) { return level % 2 == 0; }
	private boolean isOddLevel(int level) { return !isEvenLevel(level); }

	@Override
	protected T findEntry(BinaryNode<T> rootNode, T entry) {
		return findEntry(rootNode,entry, 1);
	}
	private T findEntry(BinaryNode<T> rootNode, T entry, int level) {
		T result = null;
		if (rootNode != null) {
			T rootEntry = rootNode.getData();
			if (entry.equals(rootEntry))
				result = rootEntry;
			else if(isEvenLevel(level+1)) { // current level is even
				if (xComp.compare(entry, rootEntry) < 0) {
					result = findEntry((BinaryNode<T>) rootNode.getLeftChild(), entry, ++level);
				} else {
					result = findEntry((BinaryNode<T>) rootNode.getRightChild(), entry, ++level);
				}
			} else { // current level is odd
				if (yComp.compare(entry, rootEntry) < 0) {
					result = findEntry((BinaryNode<T>) rootNode.getLeftChild(), entry, ++level);
				} else {
					result = findEntry((BinaryNode<T>) rootNode.getRightChild(), entry, ++level);
				}
			}
		}

		return result;
	}

	@Override
	protected T addEntry(BinaryNode<T> root, T newEntry) {
		return addEntry(root, newEntry, 1);
	}
	private T addEntry(BinaryNode<T> root, T newEntry, int level) {
		T result = null;
		int comp = 0;
		if(isEvenLevel(level+1)) {
			comp = xComp.compare(newEntry, root.getData());
		} else {
			comp = yComp.compare(newEntry, root.getData());
		}

		if (comp == 0) {
			if(this.isEmpty()) {
				result = root.getData();
				root.setData(newEntry);
			} else {
				if (root.hasRightChild()) {
					result = addEntry((BinaryNode<T>) root.getRightChild(), newEntry, ++level);
				} else {
					root.setRightChild(new BinaryNode<>(newEntry));
				}
			}
		} else if (comp < 0){
			if (root.hasLeftChild()) {
				result = addEntry((BinaryNode<T>) root.getLeftChild(), newEntry, ++level);
			} else {
				root.setLeftChild(new BinaryNode<>(newEntry));
			}
		} else {
			if (root.hasRightChild()) {
				result = addEntry((BinaryNode<T>) root.getRightChild(), newEntry, ++level);
			} else {
				root.setRightChild(new BinaryNode<>(newEntry));
			}
		}

		return result;
	}

	@Override
	protected BinaryNode<T> removeEntry(BinaryNode<T> root, T entry, ReturnObject ret) {
		return removeEntry(root, entry, ret, 1);
	}
	private BinaryNode<T> removeEntry(BinaryNode<T> root, T entry, ReturnObject ret, int level) {
		if (root != null){
			T rootData = root.getData();
			int comp = 0;
			if (isEvenLevel(level+1)) {
				comp = xComp.compare(entry, root.getData());
			} else {
				comp = yComp.compare(entry, root.getData());
			}

			if (comp == 0 && entry.equals(rootData)) { // entry matches root entry
				ret.set(rootData);
				root = removeFromRoot(root);
			} else if (comp < 0){
				BinaryNode<T> subTreeRoot = removeEntry((BinaryNode<T>) root.getLeftChild(), entry, ret, ++level);
				root.setLeftChild (subTreeRoot);
			} else {
				BinaryNode<T> subTreeRoot = removeEntry((BinaryNode<T>) root.getRightChild(), entry, ret, ++level);
				root.setRightChild (subTreeRoot);
			}
		}

		return root;
	}
}
