package main;

import bag.LinkedBag;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 *
 * Created by camasok on 1/21/2016.
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 */
public class FileIO
{
    public LinkedBag<String> dicBag = new LinkedBag<>();

    private boolean cont = true;
    public FileIO() {}

    public LinkedBag getDictionary() {
        LinkedBag<String> ret = new LinkedBag<>();
        ret.fromArray(Arrays.copyOf(this.dicBag.toArray(), this.dicBag.getCurrentSize()));
        //ret = dicBag;
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
            while(s.hasNext())
            {
                synchronized (this) {
                    dicBag.add(s.next());
                }
            }
            cont = false;
            System.out.println("Dictionary load process completed successfully!");
        }
        return true;
    }
}
