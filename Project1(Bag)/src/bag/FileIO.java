package bag;
import java.io.*;
import java.io.Scanner;

/**
 * Created by camasok on 1/21/2016.
 */
public class FileIO
{
    public dicBag = new LinkedBag<T>()

    public boolean parseFile(String filename)
    {
       Scanner n = null;

        n = new Scanner(new BufferedReader((new FileReader(filename))));
        while(n.hasNext())
        {
            dicBag.add(n);
        }
    }
}
