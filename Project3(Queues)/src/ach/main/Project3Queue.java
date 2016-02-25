package ach.main;

import ach.train.Station;
import ach.train.Train;
import ach.train.TrainRoute;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
		int t = displayGui(dur*60, TrainRoute.stationPool); // show the countdown and animation
		exSer.shutdown();
		continueSim = false;
		if(t == 0) { exSer.shutdownNow(); }

		if (exSer.awaitTermination(t+5, TimeUnit.SECONDS)) { // +5 to allow any extra output to be flushed
			// If all threads shutdown properly.
			System.out.println("Train Route Simulator has shutdown successfully!");
		} else {
			// If there is at least one dangling thread
			System.out.println("Train Route Simulator failed to shutdown successfully!");
		}
		System.exit(0);
	}

	/**
	 * displays a decorative animation with a decorative simulation countdown.
	 * @param dur the number of seconds to run for
	 * @param stations
	 * @return the time remaining; usually 0 unless therre was an unexpected issue
	 */
	private static int displayGui(int dur, Station[] stations) {
		JFrame window = new JFrame("Train Route Simulator");
		Container contentpane = window.getContentPane();
		JPanel imagePanel = new JPanel();
		JPanel counterPanel = new JPanel(new BorderLayout(0, 0));
		JPanel listPanel = new JPanel(new BorderLayout(0, 0));
		JTextField counter = new JTextField(String.valueOf(dur));
		JLabel counterL = new JLabel("Seconds until Termination:");
		DefaultListModel<Pair<Station, Integer>> lm = new DefaultListModel<>();
		JList<Pair<Station, Integer>> stationlist;

		URL url = Project3Queue.class.getResource("train.gif");
		URL url2, url3;
		ImageIcon train = new ImageIcon(url, "Train gif");

		AudioInputStream ais = null;
		Clip clip = null;
		Font oldNewspaper = null;
		try {
			url2 = Project3Queue.class.getResource("OldNewspaperTypes.ttf");
			oldNewspaper = Font.createFont(Font.TRUETYPE_FONT, new File(url2.toURI()));
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(oldNewspaper);

			url3 = Project3Queue.class.getResource("trainWhistle.wav");
			ais = AudioSystem.getAudioInputStream(url3);
			Mixer mix = AudioSystem.getMixer(null);
			clip = AudioSystem.getClip(mix.getMixerInfo());
			clip.open(ais);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}

		for (Station station : stations) {
			lm.addElement(new Pair<>(station, station.getQueueLength()));
		}
		stationlist = new JList<>(lm);

		counter.setEditable(false);
		counter.setHorizontalAlignment(JTextField.CENTER);
		counter.setFont(new Font(oldNewspaper.getName(), Font.BOLD, 18));
		counter.setBorder(new EmptyBorder(0,0,0,0));
		counterL.setFont(new Font(oldNewspaper.getName(), Font.BOLD, 18));
		counterL.setHorizontalAlignment(SwingConstants.TRAILING);
		counterL.setLabelFor(counter);

		stationlist.setFont(new Font(oldNewspaper.getName(), Font.BOLD, 13));
		stationlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		counter.setPreferredSize(new Dimension(25, 126));
		counterL.setPreferredSize(new Dimension(300, 50));
		stationlist.setPreferredSize(new Dimension(175, 176));
		imagePanel.setPreferredSize(new Dimension(500, 274));
		counterPanel.setPreferredSize(new Dimension(500, 176));

		imagePanel.add(new JLabel(train), SwingConstants.CENTER);
		counterPanel.add(counter, BorderLayout.CENTER);
		counterPanel.add(counterL, BorderLayout.WEST);
		listPanel.add(stationlist, BorderLayout.CENTER);

		contentpane.add(imagePanel, BorderLayout.SOUTH);
		contentpane.add(counterPanel, BorderLayout.WEST);
		contentpane.add(listPanel, BorderLayout.EAST);

		window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		window.pack();
		window.setVisible(true);

		int i;
		clip.loop(dur/5); //loops the playback of the whistle
		for (i = dur; i >= 0; i--) {
			counter.setText(String.valueOf(i));
			for (int j = 0; j < lm.getSize(); j++) {
				Pair<Station, Integer> p = lm.get(j);
				p.setVal(p.getKey().getQueueLength());
			}
			stationlist.repaint();
			if(!window.isActive()) {
				clip.stop();
			} else {
				clip.loop(i/5);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
		clip.close();
		window.dispose();
		return i;
	}

	/**
	 * A basic pairing of a key to a value
	 * @param <K> the Key Type
	 * @param <V> the Value Type
	 */
	static class Pair<K, V> {
		private K key;
		private V val;

		public Pair(K key, V val) {
			this.key = key;
			this.val = val;
		}

		public K getKey() { return key;	}
		public V getVal() { return val; }

		public void setVal(V val) { this.val = val; }

		@Override
		public String toString() {
			return (key.toString() + ": " + val.toString());
		}
	}
}
