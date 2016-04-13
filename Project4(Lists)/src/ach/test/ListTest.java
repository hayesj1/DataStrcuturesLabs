package ach.test;

import ach.list.DoubleLinkedList;

import java.util.Arrays;

/**
 * Created by hayesj3 on 3/16/2016.
 */
public class ListTest {

	public static void main(String[] args) {
		System.out.println("Hello Test World!");

		DoubleLinkedList<String> testList = new DoubleLinkedList<>();
		String[] temp = new String[testList.getLength()];
        // Add Test
        testList.add("Apples");
        System.out.println(Arrays.toString(testList.toArray(temp)) + "\n");

        //Add (position) Test

        testList.add(0, "More Apples");
        System.out.println(Arrays.toString(testList.toArray(temp)) + "\n");

        //Remove Test
        testList.remove(0);
        System.out.println(Arrays.toString(testList.toArray(temp)) + "\n");

        //Replace Test
        testList.replace(0, "Oranges");
        System.out.println(Arrays.toString(testList.toArray(temp)) + "\n");

        // Get Entry Test
        testList.add("Watermellon");
        testList.add("Grapefruit");
		System.out.println(Arrays.toString(testList.toArray(temp)));
		System.out.println(testList.getEntry(testList.getLength() - 1) + "\n");

        //Contains Test
        System.out.println(testList.contains("Grapefruit") + "\n");

        // Is Empty test
		System.out.println(testList.isEmpty());
		testList.clear();
		System.out.println(testList.isEmpty());
	}
}
