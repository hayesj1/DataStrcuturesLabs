package ach.test;

import ach.queue.CircularArrayQueue;
import ach.queue.EmptyQueueException;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *
 * Created by hayesj3 on 2/12/2016.
 */
public class QueueTest {

	public static void main(String[] args) throws EmptyQueueException
	{
		System.out.println("Test!");
		CircularArrayQueue<String> test1 = new CircularArrayQueue();

		test1.enqueue("One");
		test1.enqueue("Two");
		test1.enqueue("Three");
		test1.enqueue("Four");
		test1.enqueue("Five");
		test1.enqueue("Six");
		test1.enqueue("Seven");
		test1.enqueue("Eight");
		test1.enqueue("Nine");
		test1.enqueue("Ten");

		for(int i = 0; i < 10; i++)
		{
			System.out.println(test1.dequeue());
		}

		System.out.println(test1.isEmpty());
		System.out.println(test1.isFull());

		test1.enqueue("One");
		test1.enqueue("Two");
		System.out.println(test1.getFront());

		test1.clear();

		System.out.println(test1.isEmpty());
	}
}
