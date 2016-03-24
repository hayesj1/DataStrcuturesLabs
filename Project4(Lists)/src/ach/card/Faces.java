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
	TWO,
	THREE,
	FOUR,
	FIVE,
	SIX,
	SEVEN,
	EIGHT,
	NINE,
	TEN,
	JACK,
	QUEEN,
	KING,
	ACE;


	public static Faces valueOf(int val) {
		Faces value;

		switch(val) {
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
				value = TEN;
				break;
			case 11:
				value = JACK;
				break;
			case 12:
				value = QUEEN;
				break;
			case 13:
				value = KING;
				break;
			case 14:
				value = ACE;
			default:
				value = null;
				break;
		}

		return value;
	}
}
