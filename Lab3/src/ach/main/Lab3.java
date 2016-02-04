package ach.main;

/**
 *
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *
 * Created by hayesj3 on 1/19/2016.
 */
public class Lab3 {

    /**
     * Entry point of the program
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Lab3 test = new Lab3();
    }

    /**
     * Testing code is in the constructor to remove the code from the static context of main();
     * in a real-world application of stack this would not be appropriate
     */
    public Lab3() {
        System.out.println("Hello World!");
    }
}
