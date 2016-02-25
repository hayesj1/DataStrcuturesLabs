package ach.main;

import ach.train.Station;
import ach.train.Train;
import ach.train.TrainRoute;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *
 * Created by hayesj3 on 2/12/2016.
 */
public class Project3Queue {

	private static ArrayList<FutureTask<Integer>> dispatchedTrains = new ArrayList<>();
	/**
	 * controls travel time for trains
	 */
	private static int delay = -1;
	/**
	 * True as long as the simulation should continue
	 */
	private static boolean continueSim = true;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Welcome to the Train Route Simulator!");
		System.out.println("How many minutes for the simulation run for?");
		Scanner s = new Scanner(System.in);
		int dur = s.nextInt(); // grab the simulation's duration from the user

		// set travel time; but keep it from having a value of 0
		do {
			delay = ThreadLocalRandom.current().nextInt(6);
		} while (delay == 0);
		TrainRoute trainRoute = new TrainRoute();
		Station[] stations = TrainRoute.stationPool;

		ExecutorService exSer = Executors.newCachedThreadPool();// controls and adds multithreaded functionality to allow
																// regular train dispatches, random passenger enqueueings,
																// and notification when a train has gone out of service

		// adds passenger(s) to a random station
		Thread passengerAdder = new Thread(() -> {
			ThreadLocalRandom rand = ThreadLocalRandom.current();
			while (!exSer.isShutdown() && continueSim) {
				try {
					int stIdx = rand.nextInt(stations.length);
					stations[stIdx].addPassengerToLine(rand);
					//System.out.println("Added Passenger to " + stations[stIdx].getName() + " Station"); //debug output
					Thread.sleep(rand.nextInt(3) * 1000);
				} catch (InterruptedException e) {}
			}
		});
		// dispatches new trains
		Thread trainDispatcher = new Thread(() -> {
			ThreadLocalRandom rand = ThreadLocalRandom.current();

			while (!exSer.isShutdown() && continueSim) {
				Train train = new Train(rand, stations);
				Integer trainNo = train.getTrainNo();
				FutureTask<Integer> future = new FutureTask<>(() -> {
					train.beginRoute(delay);
				}, trainNo);
				exSer.submit(future);
				dispatchedTrains.add(future);
				System.out.println("Dispatched Train #" + train.getTrainNo());

				try {
					Thread.sleep(delay * 1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		// outputs a line to stdout when a train completes the route
		Thread arrivalDisplay = new Thread(() -> {
			do {
				dispatchedTrains.forEach(train -> {
					try {
						System.out.println("Train #" + train.get(5L, TimeUnit.SECONDS) + " has arrived it its final stop!");
						dispatchedTrains.remove(train);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					} catch (TimeoutException e) { }
				});
			} while (!dispatchedTrains.isEmpty());
		});

		exSer.execute(() -> { // Start the simulation
			passengerAdder.start();
			trainDispatcher.start();
			System.out.println("Starting Train Dispatch System...");
			arrivalDisplay.start();
		});
		int t = displayCounter(dur*60); // show the countdown and animation
		exSer.shutdown();
		continueSim = false;
		if(t == 0) { exSer.shutdownNow(); }

		if (exSer.awaitTermination(t+5, TimeUnit.SECONDS)) { // +5 to allow any extra output to be flushed
			System.out.println("Train Route Simulator has shutdown successfully!");
		} else {
			System.out.println("Train Route Simulator failed to shutdown successfully!");
		}
		System.exit(0);
	}

	/**
	 * displays a decorative animation with a decorative simulation countdown.
	 * @param dur the number of seconds to run for
	 * @return the time remaining; usually 0 unless therre was an unexpected issue
	 */
	private static int displayCounter(int dur) {
		JFrame window = new JFrame("Train Route Simulator");
		Container contentpane = window.getContentPane();
		JPanel imagePanel = new JPanel();
		JPanel counterPanel = new JPanel(new BorderLayout(5, 2));
		JTextField counter = new JTextField(String.valueOf(dur) + " seconds remain in the Simulation");

		URL url = Project3Queue.class.getResource("train.gif");
		ImageIcon train = new ImageIcon(url, "Train gif");

		Font oldNewspaper = null;
		try {
			URL url2 = Project3Queue.class.getResource("OldNewspaperTypes.ttf");
			oldNewspaper = Font.createFont(Font.TRUETYPE_FONT, new File(url2.toURI()));
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(oldNewspaper);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		counter.setEditable(false);
		counter.setHorizontalAlignment(JTextField.CENTER);
		counter.setFont(new Font(oldNewspaper.getName(), Font.BOLD, 18));
		counter.setPreferredSize(new Dimension(50, 26));
		imagePanel.setPreferredSize(new Dimension(500, 274));
		counterPanel.setPreferredSize(new Dimension(500, 26));

		imagePanel.add(new JLabel(train), SwingConstants.CENTER);
		counterPanel.add(counter);

		contentpane.add(imagePanel, BorderLayout.SOUTH);
		contentpane.add(counterPanel, BorderLayout.NORTH);

		window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		window.pack();
		window.setVisible(true);

		int i;
		for (i = dur; i >= 0; i--) {
			counter.setText(String.valueOf(i) + " seconds remain in the Simulation");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
		window.dispose();
		return i;
	}
}
