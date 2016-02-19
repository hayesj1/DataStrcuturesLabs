package ach.train;
import java.util.String;
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

public class Passenger
{
    private String endingLoc;
    private String startingLoc;

    public String getEndingLoc() {
        return endingLoc;
    }

    public void setEndingLoc(String endingLoc) {
        this.endingLoc = endingLoc;
    }

    public String getStartingLoc() {
        return startingLoc;
    }

    public void setStartingLoc(String startingLoc) {
        this.startingLoc = startingLoc;
    }
}