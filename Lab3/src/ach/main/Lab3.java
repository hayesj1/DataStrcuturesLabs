package ach.main;

import ach.notation.InfixToPostfix;

import java.util.Scanner;

/**
 *
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *
 * Created by hayesj3 on 2/04/2016.
 */
public class Lab3 {

	String inFix;
	String postFix;
	double result;
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
        System.out.println("Enter an Infix Expression as a String: ");
	    Scanner in = new Scanner(System.in);

	    inFix = in.nextLine();
	    System.out.println("You entered: " + inFix);
	    postFix = InfixToPostfix.convert(inFix);
	    System.out.println("The PostFix conversion of your expression is: " + postFix);
	    //result = ;
	    System.out.println("The Result of the Postfix : " + result);

    }
}
