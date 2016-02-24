package ach.queue;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by hayesj3 on 2/18/2016.
 */
public class EmptyQueueException extends Exception {

	public EmptyQueueException(String operation) {
		super("Cannot do " + operation + " on an empty queue!");
	}
	public EmptyQueueException() { super(); }
}
