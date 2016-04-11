package ach.main;

import ach.tree.BinarySearchTree;
import ach.tree.KDTree;

import java.util.Random;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 * Created by hayesj3 on 4/8/2016.
 */
public class Project5Tree {
	public static final int length = 26;
	public static final int width = 26;

	public static void main(String[] args) {
		System.out.println("2D-Tree Project by Group ACH!");
		System.out.println("Initializing Tree with Point (0,0)...");
		BinarySearchTree<Point> tree = new KDTree<>(new Point(0,0));
		boolean first = true; // resolves a duplicate (0,0) issue
		for (int i = 0; i < length; i++) {
			for (int j = (first) ? 1 : 0; j < width; j++) {
				if(first) { first = false; }
				Point p = new Point(i, j);
				System.out.println("Adding Point " + p + " to Tree...");
				tree.add(p);
			}
		}
		System.out.println("Initializing Tests...");
		Random rand = new Random(System.currentTimeMillis());
		int x1 = rand.nextInt(length);
		int y1 = rand.nextInt(width);

		int x2 = rand.nextInt(length);
		int y2 = rand.nextInt(width);

		Point p1 = new Point(x1, y1);
		Point p2 = new Point(x2, y2);
		Point pClone = new Point(x1, y1);
		Point pOut = new Point(length*2, width*2);

		System.out.println("Beginning Tests...");

		System.out.println("Point 1: " + p1);
		System.out.println("Point 2: " + p2);
		System.out.println("Point Clone: " + pClone);
		System.out.println("Point Out of Bounds: " + pOut);

		System.out.println("Point 1 == Point 2: " + p1.equals(p2));
		System.out.println("Point 1 == Point Clone: " + p1.equals(pClone));

		System.out.println("Point 1 in Tree: " + tree.contains(p1));
		System.out.println("Point 2 in Tree: " + tree.contains(p2));
		System.out.println("Point Out of Bounds in Tree: " + tree.contains(pOut));

		System.out.println("Removing Point Clone from Tree: " + tree.remove(pClone));
		System.out.println("Point 1 in Tree: " + tree.contains(p1));
		System.out.println("Removing Point Out of Bounds from Tree: " + tree.remove(pOut));
	}
}
