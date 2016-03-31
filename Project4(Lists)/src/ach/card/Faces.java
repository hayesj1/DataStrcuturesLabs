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

	public static String toNumeralString(Faces face) {
		String ret = "";
		switch (face){
			case TWO:
				ret = "2";
				break;
			case THREE:
				ret = "3";
				break;
			case FOUR:
				ret = "4";
				break;
			case FIVE:
				ret = "5";
				break;
			case SIX:
				ret = "6";
				break;
			case SEVEN:
				ret = "7";
				break;
			case EIGHT:
				ret = "8";
				break;
			case NINE:
				ret = "9";
				break;
			case TEN:
				ret = "10";
				break;
			case JACK:
				ret = "jack";
				break;
			case QUEEN:
				ret = "queen";
				break;
			case KING:
				ret = "king";
				break;
			case ACE:
				ret = "ace";
				break;
		}
		return ret;
	}
}
