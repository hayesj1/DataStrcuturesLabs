package ach.train;

import ach.queue.CircularArrayQueue;
import ach.queue.EmptyQueueException;

import java.util.ArrayList;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by hayesj3 on 2/18/2016.
 */
public class Train {
	public static final int capacityPerCar = 25;
	/**
	 * allows easy calculation of Train Number
	 */
	private static int numTrains = 1000;

	private int trainNo = -1;
	private int numCars;
	private int passengersOnBoard = 0;
	private int capacity = 0;
	private ArrayList<Passenger> passengers = new ArrayList<>();
	private CircularArrayQueue<Station> stops = new CircularArrayQueue<>();
	private Station currStation = null;
	private String firstStation = "";

	public void offLoad() {
		// removes passengers
		if (passengersOnBoard > 0) {
			if (currStation.isTerminal() && !currStation.getName().equals(firstStation)) { passengers.clear(); }

			passengers.removeIf(passenger -> (passenger.getDest().equals(currStation.getName())));
		}
	}
	public void board() {
		// boards all passengers, or as many as can fit on the train, from the platform at the current station
		try {
			while (passengersOnBoard <= capacity) {
				passengers.add(currStation.getNextInLine());
				passengersOnBoard++;
			}
		} catch (EmptyQueueException e) {
			return;
		}
	}

	public void travel(int delay) throws EmptyQueueException {
		// Train moves to next stop, drops stop it was just at in TrainRoute
		try {
			System.out.println("train #" + this.getTrainNo() + " has departed from " + stops.dequeue());
			currStation = null;
			Thread.sleep(0 * 1000);
			this.currStation = stops.getFront();
			System.out.println("Train #" + this.getTrainNo() + " has arrived at " + this.getCurrStation());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void beginRoute(int delay) {
		try {
			while (!this.stops.isEmpty()) {
				this.travel(delay);
				this.offLoad();
				this.board();
			}
		} catch (EmptyQueueException e) {
			System.out.println("Train #" + this.getTrainNo() + " is now out of service!");
			return;
		}
	}

	public Train init(int numCars, Station[] stations) {
		this.trainNo = numTrains++;
		this.setNumCars(numCars);
		this.setStops(stations);
		this.setCapacity();
		this.setFirstStation(stops.getFront().getName());

		return this;
	}

	public int getNumCars() { return numCars; }
	public int getTrainNo() { return trainNo; }
	public Station getCurrStation() { return currStation; }
	public String getFirstStation() { return this.firstStation; }

	public void setNumCars(int numCars) { this.numCars = numCars; }
	public void setCapacity() { this.capacity = numCars * capacityPerCar; }
	public void setFirstStation(String name) { this.firstStation = name; }
	public void setStops(Station[] stations) {
		for (int i = 0; i < stations.length; i++) {
			stops.enqueue(stations[i]);
		}
	}
}
