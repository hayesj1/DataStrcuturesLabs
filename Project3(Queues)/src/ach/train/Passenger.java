package ach.train;
/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by camasok on 2/18/2016.
 */

public class Passenger
{
    private Station dest;

	public Passenger(Station dest) {
		this.dest = dest;
	}

	public Station getDest() {
        return dest;
    }

    public Passenger setDest(Station dest) {
        this.dest = dest;
	    return this;
    }
}