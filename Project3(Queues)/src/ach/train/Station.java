package ach.train;

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
public class Station
{
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


	public Passenger getNextInLine() throws EmptyQueueException {
		return queueOfPassengers.removePassenger();
	}

	public void addPassengerToLine(Passenger p) {
		queueOfPassengers.addPassenger(p);
	}
}