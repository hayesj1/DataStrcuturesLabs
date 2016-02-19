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
public class Station
{
    private boolean IsTerminal = false;
    private boolean IsUsed = false;
    private String name = "";
    private int numOfPassengers

    public boolean isTerminal()
    {
        return IsTerminal;
    }

    public void setTerminal(boolean terminal)
    {
        IsTerminal = terminal;
    }

    public boolean isUsed()
    {
        return IsUsed;
    }

    public void setUsed(boolean used)
    {
        IsUsed = used;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


}