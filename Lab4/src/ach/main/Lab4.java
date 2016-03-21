package ach.main;

import ach.list.DLList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by hayesj3 on 3/16/2016.
 */
public class Lab4 {

	public static void main(String[] args) {
		//TODO finsh this code that reads from the text file, adding each word to a DLList<String> and then tests all the methods

		BufferedReader reader = null;
		DLList<String> testDLL = new DLList<>();


		try{
			reader = new BufferedReader(new FileReader("words.txt"));
			testDLL.add(reader.readLine());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//TODO add test code to demonstrate DLL.

		System.out.println("Add test, adds burrito to end.");
		testDLL.add("burrito");
		System.out.println(Arrays.toString(testDLL.toArray()));

		System.out.println("Add avacado after bob.");
		testDLL.add(1, "avacado");
		System.out.println(Arrays.toString(testDLL.toArray()));

		System.out.println("Removes Zombie");
		testDLL.remove(4);
		System.out.println(Arrays.toString(testDLL.toArray()));

		System.out.println("replace test, replaces bob with bill");
		testDLL.replace(0, "bill");
		System.out.println(Arrays.toString(testDLL.toArray()));

		System.out.println("getEntry test, gets entry at position 5");
		System.out.println(testDLL.getEntry(5));

		System.out.println("Contains test, checks for burger");
		System.out.println(testDLL.contains("burger"));

		System.out.println(testDLL.isEmpty());
		System.out.println(testDLL.getLength());

		testDLL.clear();

		System.out.println(testDLL.isEmpty());
		System.out.println(testDLL.getLength());




	}
}
