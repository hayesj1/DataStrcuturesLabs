package ach.train;

import ach.queue.CircularArrayQueue;
import ach.queue.EmptyQueueException;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by camasok on 2/18/2016.
 */

public class QueueOfPassengers {
	private Integer length = 0;
	private CircularArrayQueue<Passenger> passengers;

	public QueueOfPassengers() {
		passengers = new CircularArrayQueue<>();
	}

	public Integer getQueueLength() {
		return length;
	}

	public Passenger removePassenger() throws EmptyQueueException {
		if(length > 0 ) { length--; }
		return passengers.dequeue();
	}

	public void addPassenger(Passenger p) {
		passengers.enqueue(p);
		length++;
	}
}