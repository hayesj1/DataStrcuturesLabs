package ach.main;

import ach.main.gui.Calculator;

import javax.swing.*;

/**
 * Created by hayesj3 on 2/1/2016.
 */
public class Project2Stack {

	public static void main(String[] args) {
		System.out.println("Welcome to the Optimus Calculator!");
		Calculator calc = new Calculator();
		calc.setVisible(true);
		calc.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
