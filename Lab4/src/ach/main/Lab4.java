package ach.main;

import ach.list.DLList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	}
}
