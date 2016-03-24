package ach.game;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by hayesj3 on 3/23/2016.
 */
public enum Chips {
	White(1),
	Red(5),
	Blue(10),
	Green(25),
	Black(100),
	Purple(500);

	private int value;
	public int getValue() { return this.value; }

	Chips(int value) { this.value = value; }

	static Chips[] reverseValues() {	return new Chips[] {Purple, Black, Green, Blue, Red, White }; }
}
