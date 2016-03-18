package ach.card;

import ach.test.Testable;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by hayesj3 on 3/18/2016.
 */
public class Card implements Comparable<Card>, Testable{

	private Suit suit;
	private Values value;

	private Card() {}
	public Card(Suit suit,  int value) {
		this.suit = suit;
		this.value = Values.valueOf(value);
		if (this.value == null || (this.suit == Suit.JOKER && this.value != Values.JOKER)) {
			throw new IllegalArgumentException("Invalid value, " + value + "/" + this.value + ", for Suit: " + this.getSuit());
		}
	}

	@Override
	public String toString() {
		return (this.getValue() + " of " + this.getSuit() + "s");
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		else if (obj == this) { return true; }
		else if (!(obj instanceof Card)) { return false; }

		Card other = (Card) obj;
		return ((this.getSuit().equals(other.getSuit())) && (this.getValue().equals(other.getValue())));
	}

	@Override
	public int compareTo(Card other) {
		return this.getValue().compareTo(other.getValue());
	}

	public Suit getSuit() {	return suit; }
	public Values getValue() { return value; }

	public void setSuit(Suit suit) { this.suit = suit; }
	public void setValue(Values value) { this.value = value; }


	@Override
	public boolean test() {
		return true;
	}
}
