package main;

import bag.LinkedBag;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by camasok on 1/21/2016.
 */
public class FileIO implements Runnable
{
    public LinkedBag<String> dicBag = new LinkedBag<>();

    private boolean cont = true;
    public FileIO() {
        parseFile();
    }

    public LinkedBag getDictionary() {
        LinkedBag<String> ret = new LinkedBag<>();
        ret.fromArray(Arrays.copyOf(this.dicBag.toArray(), this.dicBag.getCurrentSize()));
        return ret;
    }

    public boolean parseFile()
    {
        URL temp = this.getClass().getResource("/dictionary");
        if (temp == null) {
            return false;
        }
        Scanner s = null;
        try {
            s = new Scanner(new File(temp.toURI()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (s != null) {
            System.out.println("Beginning dictionary load process");
            Thread thread = new Thread(this);
            thread.run();
            while(s.hasNext())
            {
                dicBag.add(s.next());
            }
            cont = false;
            System.out.println("Dictionary load process completed successfully!");
        }
        return true;
    }

    @Override
    public void run() {
        int counter = 0;
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(". .  ");
            counter++;
            if((counter % 10) == 0) {
                System.out.println();
            }
        } while(this.cont);
    }
}
