package main;

import bag.LinkedBag;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by hayesj3 on 1/21/2016.
 */
public class Project1Bag {

    private LinkedBag<String> dict = null;
    private LinkedBag<String> userWords = new LinkedBag<>();
    private LinkedBag<String> correctWords = new LinkedBag<>();
    private LinkedBag<String> incorrectWords = new LinkedBag<>();
    public Project1Bag() {
        //main code
        FileIO fileIO = new FileIO();
        if(!fileIO.parseFile()) {
            System.err.println("Dictionary file not found! Aborting!");
            System.exit(-1);
        }
        dict = fileIO.getDictionary();
        String[] words = null;
        do {
            try {
                words = getUserWords();
            } catch(FileNotFoundException e){
                System.err.println("File not found! Please try again!");
            }
        } while(words == null);

        userWords.fromArray(words);
        checkWords();
    }

    private String[] getUserWords() throws FileNotFoundException {
        String temp = "";
        String[] array = null;
        Scanner input = new Scanner(System.in);
        Scanner s = new Scanner(new File(input.nextLine()));
        do {
            temp += s.nextLine();
        } while (s.hasNextLine());
        array = temp.split("\t|\n");
        return array;
    }

    private void checkWords() {
        this.correctWords = userWords.intersection(dict);
        this.incorrectWords = userWords.inverseIntersection(correctWords);
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Project1Bag main = new Project1Bag();
    }
}
