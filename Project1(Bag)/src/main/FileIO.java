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

    private int count = 0;
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
        // TODO add dictionary.txt as a hardcoded filepath
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
            while(s.hasNext())
            {
                dicBag.add(s.next());
                count++;
            }
        }
        return true;
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
