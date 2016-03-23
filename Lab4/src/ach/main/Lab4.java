package ach.main;

import ach.list.DLList;
import ach.list.iterator.IIterator;

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
		DLList<String> testDLL = new DLList<>();

		try(BufferedReader reader = new BufferedReader(new FileReader("Lab4/words.txt"))) {
			String line;
			while((line = reader.readLine()) != null) {
				testDLL.add(line);
				System.out.println("Read in and added \'" + line + "\' to Double Lined List");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("The DLL contains: " + Arrays.toString(testDLL.toArray()));


		IIterator<String> it = testDLL.getCustomIterator();
		System.out.println("Iterator Testing:");
		System.out.println("Next: " + it.next());
		System.out.println("Has Previous: " + it.hasPrevious());
		System.out.println("Next Index: " + it.nextIndex());
		String next = it.next();
		System.out.println("Next: " + next);
		it.remove();
		System.out.println("Remove: " + next);
		System.out.println("The final state of the list: " + Arrays.toString(testDLL.toArray()));


		System.out.println("DLL Testing:");
		System.out.println("Removes Zombie");
		testDLL.remove(3);
		System.out.println(Arrays.toString(testDLL.toArray()));

		System.out.println("Replace: replaces bob with bill");
		testDLL.replace(0, "bill");
		System.out.println(Arrays.toString(testDLL.toArray()));

		System.out.println("Get Entry: gets entry at position 5");
		System.out.println(testDLL.getEntry(5));

		System.out.println("Contains \'burger\'? " + testDLL.contains("burger"));

		System.out.println("Empty? " + testDLL.isEmpty());
		System.out.println("Length? " + testDLL.getLength());

		testDLL.clear();
		System.out.println("DLL Cleared!");

		System.out.println("Empty? " + testDLL.isEmpty());
		System.out.println("Length? " + testDLL.getLength());

	}
}
