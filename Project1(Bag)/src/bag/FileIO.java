package bag;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by camasok on 1/21/2016.
 */
public class FileIO implements Runnable
{
    public LinkedBag<String> dicBag = new LinkedBag<>();

    private int count = 0;
    public FileIO() {
        parseFile();
    }

    public LinkedBag getDictionary() { return new LinkedBag().fromArray(Arrays.copyOf(this.dicBag.toArray(), this.dicBag.getCurrentSize())); }

    public void parseFile()
    {
        // TODO add dictionary.txt as a hardcoded filepath
        String temp = "Dictionary.txt";

       Scanner s = null;
        try {
            s = new Scanner(new File(temp));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (s != null) {
            while(s.hasNext())
            {
                dicBag.add(s.next());
                count++;
            }
        }
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(".  ");
        } while(this.count <= 98500);
    }
}
