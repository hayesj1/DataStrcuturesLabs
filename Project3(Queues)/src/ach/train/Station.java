package ach.train;

import ach.queue.EmptyQueueException;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by camasok on 2/18/2016.
 */
public class Station
{
	/**
	 * true if this is a Terminus station; false if it is a En-Route station
	 */
    private boolean IsTerminal = false;
    private String name = "";
    private QueueOfPassengers queueOfPassengers = new QueueOfPassengers();

    public boolean isTerminal()
    {
        return IsTerminal;
    }

    public void setTerminal(boolean terminal)
    {
        IsTerminal = terminal;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

	public Integer getQueueLength() { return this.queueOfPassengers.getQueueLength(); }
	/**
	 * dequeues and returns the first passenger in line
	 * @return the first passenger in the queue
	 * @throws EmptyQueueException if the queue is empty; aka there are no waiting passengers
	 */
	public Passenger getNextInLine() throws EmptyQueueException {
		return queueOfPassengers.removePassenger();
	}

	/**
	 * Adds a passengers to the station's queue
	 * @param rand the random to use for randomizing the destination and number of passengers to add
	 */
	public void addPassengerToLine(ThreadLocalRandom rand) {
		int numPassengers;
		do {
			numPassengers = rand.nextInt(10);
		} while (numPassengers == 0); // guarantees at least 1 passenger is added

		for (int i = 0; i < numPassengers; i++) {
			queueOfPassengers.addPassenger(new Passenger(TrainRoute.stationPool[rand.nextInt(TrainRoute.numStations)]));
		}

	}

	@Override
	public String toString() {
		return this.getName();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) { return false; }
		else if(obj == this) { return true; }
		else if(!(obj instanceof Station)) { return false; }

		Station other = (Station) obj;

		return (this.getName().equals(other.getName()));
	}
}