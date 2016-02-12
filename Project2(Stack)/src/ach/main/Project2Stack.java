package ach.main;

import ach.main.gui.Calculator;

import javax.swing.*;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *
 * Created by hayesj3 on 2/1/2016.
 */
public class Project2Stack {

	public static final String calcName = "Optimator Calculator";

	public static void main(String[] args) {
		System.out.println("Welcome to the " +calcName + "!");
		System.out.println("Try pressing 2+2= on your keyboard!");
		Calculator calc = new Calculator();
		calc.setVisible(true);
		calc.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
