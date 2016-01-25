package main;

import bag.LinkedBag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 *
 * Created by hayesj3 on 1/21/2016.
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 */
public class Project1Bag {

    public static final String projectName = "Optimus Spell Checker";

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
        do {
            userWords = getUserWords();
        } while(userWords.getCurrentSize() == 0);

        System.out.println("Your words: " + userWords);
        System.out.println("Dictionary's Words: " + dict);
        checkWords();
        System.out.println("Correctly spelled words: " + correctWords);
        System.out.println("Incorrectly spelled words: " + incorrectWords);
    }

    private LinkedBag getUserWords() {
        LinkedBag<String> words = new LinkedBag<>();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the absolute path to the file containing the words to be checked:\n");
        String text;

        try (BufferedReader fr = new BufferedReader(new FileReader(input.nextLine()))){
            while ((text = fr.readLine()) != null) {
                words.add(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    private void checkWords() {
        this.correctWords = userWords.intersection(dict);
        this.incorrectWords = userWords.inverseIntersection(correctWords);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the " + projectName + "!!");
        Project1Bag main = new Project1Bag();
    }
}
