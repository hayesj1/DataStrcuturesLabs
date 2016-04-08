package ach.test;

import ach.list.DoubleLinkedList;

import java.util.List;

/**
 * Created by hayesj3 on 3/16/2016.
 */
public class ListTest {

	public static void main(String[] args) {
		System.out.println("Hello Test World!");

		DoubleLinkedList<String> testList = new DoubleLinkedList<>();

        // Add Test
        testList.add("Apples");
        System.out.println(testList.toArray().toString() + "\n");

        //Add (position) Test

        testList.add(0, "More Apples");
        System.out.println(testList.toArray().toString() + "\n");

        //Remove Test
        testList.remove(0);
        System.out.println(testList.toArray().toString() + "\n");

        //Replace Test
        testList.replace(0, "Oranges");
        System.out.println(testList.toArray().toString() + "\n");

        // Get Entry Test
        testList.add("Watermellon");
        testList.add("Grapefruit");
        System.out.println(testList.getEntry(testList.size() - 1));

        //Contains Test
        System.out.println(testList.contains("Grapefruit"));

        // Is Empty test
        // toArrayTest
	}
}
