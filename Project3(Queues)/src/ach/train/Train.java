package ach.train;

import ach.queue.CircularArrayQueue;
import ach.queue.EmptyQueueException;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

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
	private static final int MAX_CARS = 7;
	/**
	 * allows easy calculation of Train Number
	 *
	 * because modern convention for train numbers is in the thousands,
	 * the first train initialized will have number 1000
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

	public Train(ThreadLocalRandom rand, Station[] stations) {
		this.trainNo = numTrains++;
		this.setNumCars(rand.nextInt(MAX_CARS+1));
		this.setStops(stations);
		this.setCapacity();
		this.setFirstStation(stops.getFront().getName());
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

	public void travel(int delay) throws EmptyQueueException {
		// Train moves to next stop, drops stop it was just at in TrainRoute
		try {
			stops.dequeue();
			currStation = null;
			Thread.sleep(delay * 1000);
			this.currStation = stops.getFront();
			if(this.getCurrStation() == null) { throw new EmptyQueueException(); }
		} catch (InterruptedException e) {
			return;
			//e.printStackTrace();
		}
	}

	public void offLoad() {
		// removes passengers
		if (passengersOnBoard > 0) {
			if (currStation.isTerminal() && !currStation.getName().equals(firstStation)) { passengers.clear(); }
			else {
				passengers.removeIf(passenger -> (passenger.getDest().equals(currStation)));
			}
		}
	}
	public void board() {
		// boards all passengers, or as many as can fit on the train, from the platform at the current station
		int passCount = 0;
		try {
			while (passengersOnBoard <= capacity) {
				passengers.add(currStation.getNextInLine());
				passengersOnBoard++;
				passCount++;
			}
		} catch (EmptyQueueException e) {
			System.out.println("All passengers at " + getCurrStation() + " station boarded Train #" + getTrainNo());
			return;
		}
		System.out.println(passCount + " passengers boarded Train #" + getTrainNo() + " at " + getCurrStation() + " station");
	}

	public int getNumCars() { return numCars; }
	public int getTrainNo() { return trainNo; }
	public Station getCurrStation() { return currStation; }
	public String getFirstStation() { return this.firstStation; }

	public void setNumCars(int numCars) { this.numCars = numCars; }
	public void setCapacity() { this.capacity = numCars * capacityPerCar; }
	public void setFirstStation(String name) { this.firstStation = name; }
	public void setStops(Station[] stations) {
		for (Station station : stations) {
			stops.enqueue(station);
		}
	}
}
