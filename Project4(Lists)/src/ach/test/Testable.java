package ach.test;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by hayesj3 on 3/18/2016.
 *
 * Inferface purely to facilitate testing purposes.
 */
public interface Testable {
	/**
	 * Tests all methods in the given Class
	 * @param obj the Testable to test
	 * @return true if all tests were successful, false otherwise
	 */
	static boolean test(Testable obj) {
		return obj.test();
	}

	/**
	 * Tests all methods in the implementing Class
	 * @return true if all tests were successful, false otherwise
	 */
	boolean test();
}
