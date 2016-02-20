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

	private CircularArrayQueue<Passenger> passengers;

	public QueueOfPassengers() {
		passengers = new CircularArrayQueue<>();
	}

	public CircularArrayQueue<Passenger> getPassengers() {
		return passengers;
	}

	public Passenger removePassenger() throws EmptyQueueException {
		return passengers.dequeue();
	}

	public void addPassenger(Passenger p) {
		passengers.enqueue(p);
	}
}