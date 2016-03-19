package ach.card;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by hayesj3 on 3/18/2016.
 */
public enum Faces {
	JOKER,
	ACEL,
	TWO,
	THREE,
	FOUR,
	FIVE,
	SIX,
	SEVEN,
	EIGHT,
	NINE,
	JACK,
	QUEEN,
	KING,
	ACEH;


	public static Faces valueOf(int val) {
		Faces value = null;

		switch(val) {
			case 0:
				value = JOKER;
				break;
			case 1:
				value = ACEL;
				break;
			case 2:
				value = TWO;
				break;
			case 3:
				value = THREE;
				break;
			case 4:
				value = FOUR;
				break;
			case 5:
				value = FIVE;
				break;
			case 6:
				value = SIX;
				break;
			case 7:
				value = SEVEN;
				break;
			case 8:
				value = EIGHT;
				break;
			case 9:
				value = NINE;
				break;
			case 10:
				value = JACK;
				break;
			case 11:
				value = QUEEN;
				break;
			case 12:
				value = KING;
				break;
			case 13:
				value = ACEH;
				break;
			default:
				value = null;
				break;
		}

		return value;
	}
}
