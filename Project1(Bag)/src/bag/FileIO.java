package bag;
import java.io.*;
import java.util.Scanner;

/**
 * Created by camasok on 1/21/2016.
 */
public class FileIO
{
    public LinkedBag<String> dicBag = new LinkedBag<>();

    public void parseFile(String filename)
    {

       Scanner n = null;
        try {
            n = new Scanner(new BufferedReader((new FileReader(filename))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(n.hasNext())
        {
            dicBag.add(n.next());
        }
    }
}
