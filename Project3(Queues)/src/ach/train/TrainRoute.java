package ach.train;

import java.util.Random;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by camasok on 2/18/2016.
 */
public class TrainRoute
{
	public static final int numStations = 9;
	public static final Station[] stationPool = new Station[numStations];
    public static  final String[] stationNamePool = {"Red", "Blue", "Yellow","Cyan","Magenta","Green","White","Black","Rainbow"};
    private boolean[] stationNameUsed = { false, false, false, false, false, false, false, false, false };

	public TrainRoute() {
	    Random rand = new Random();
	    for (int i = 0; i < numStations; i++) {
		    Station st = new Station();
		    int temp;
		    while(stationPool[i] == null) {
			    temp = rand.nextInt(numStations);

			    if (stationNameUsed[temp]) { continue; }

			    st.setName(stationNamePool[i]);
			    st.setTerminal((i == 0 || i == numStations-1));
			    stationNameUsed[i] = true;
			    stationPool[i] = st;
		    }
	    }
    }


}