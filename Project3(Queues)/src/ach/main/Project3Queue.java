package ach.main;

import ach.train.Passenger;
import ach.train.Station;
import ach.train.Train;
import ach.train.TrainRoute;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
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
	private static int delay = -1;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Welcome to the Train Route Simulator!");
		System.out.println("How many minutes for the simulation run for?");
		Scanner s = new Scanner(System.in);
		int dur = s.nextInt();
		delay = ThreadLocalRandom.current().nextInt(6);
		TrainRoute trainRoute = new TrainRoute();
		Station[] stations = TrainRoute.stationPool;

		Thread passengerAdder = new Thread(() -> {
			ThreadLocalRandom rand = ThreadLocalRandom.current();
			while (true) {
				try {
					int stIdx = rand.nextInt(stations.length);
					stations[stIdx].addPassengerToLine(new Passenger()
							.setDest(TrainRoute.stationNamePool[rand.nextInt(TrainRoute.numStations)]));
					System.out.println("Added Passenger to " + stations[stIdx].getName() + " Station");
					Thread.sleep(rand.nextInt(10) * 1000);
				} catch (InterruptedException e) { continue; }
			}
		});
		Thread trainDispatcher = new Thread(() -> {
			ThreadLocalRandom rand = ThreadLocalRandom.current();
			ExecutorService exSer = Executors.newCachedThreadPool();

			while (true) {
				Train train = new Train().init(rand.nextInt(8), stations);
				Integer trainNo = train.getTrainNo();
				FutureTask<Integer> future = new FutureTask<>(() -> {
					train.beginRoute(delay);
				}, trainNo);
				exSer.execute(future);
				dispatchedTrains.add(future);
				System.out.println("Dispatched Train #" + train.getTrainNo());

				try {
					Thread.sleep(delay * 1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread arrivalDisplay = new Thread(() -> {
			do {
				dispatchedTrains.forEach(f -> {
					try {
						System.out.println("Train #" + f.get(5L, TimeUnit.SECONDS) + " has arrived it its final stop!");
						dispatchedTrains.remove(f);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					} catch (TimeoutException e) { }
				});
			} while (!dispatchedTrains.isEmpty());
		});
		ExecutorService exSer = Executors.newSingleThreadExecutor();
		exSer.execute(() -> {
			passengerAdder.start();
			trainDispatcher.start();
			System.out.println("Starting Train Dispatch System...");
			arrivalDisplay.start();
		});
		int t = displayCounter(dur*60);
		if(t == 0) { exSer.shutdown(); }
		exSer.awaitTermination(t, TimeUnit.SECONDS);
	}

	private static int displayCounter(int dur) {
		JFrame window = new JFrame("Train Route Simulator");
		Container contentpane = window.getContentPane();
		JPanel imagePanel = new JPanel();
		JPanel counterPanel = new JPanel(new BorderLayout(5, 2));
		JTextField counter = new JTextField(String.valueOf(dur) + " seconds remain in the Simulation");

		Image train = null;
		Font contm = null;
		try {
			train = ImageIO.read(new File("train.gif"));

			contm = Font.createFont(Font.TRUETYPE_FONT, new File("contm.ttf"));
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(contm);
		} catch (IOException e) {
			System.err.println("The train.gif file was not found or is corrupt!");
		} catch (FontFormatException e) {
			e.printStackTrace();
		}

		counter.setEditable(false);
		counter.setHorizontalAlignment(JTextField.CENTER);
		counter.setFont(contm);
		counter.setPreferredSize(new Dimension(50, 26));
		imagePanel.setPreferredSize(new Dimension(500, 274));
		counterPanel.setPreferredSize(new Dimension(500, 26));

		if(train != null) {
			imagePanel.update(train.getGraphics());
			contentpane.add(imagePanel, BorderLayout.SOUTH);
		}
		counterPanel.add(counter);

		contentpane.add(counterPanel, BorderLayout.NORTH);

		window.setVisible(true);

		int i;
		for (i = dur; i > 0; i--) {
			counter.setText(String.valueOf(i) + " seconds remain in the Simulation");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
		return i;
	}
}
