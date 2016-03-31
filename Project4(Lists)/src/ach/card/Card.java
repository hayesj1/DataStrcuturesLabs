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

	private Suits suit;
	private Faces face;

	private Card() {}
	public Card(Suits suit, int face) {
		this.suit = suit;
		this.face = Faces.valueOf(face);

		if (this.face == null) {
			throw new IllegalArgumentException("Invalid face, " + face);
		}
	}

	public Card(Suits suit, Faces face) {
		this.suit = suit;
		this.face = face;
	}

	public boolean sameSuit(Card c) {
		return this.getSuit().equals(c.getSuit());
	}

	public boolean exactEquals(Card c) {
		return (this.sameSuit(c) && this.equals(c));
	}

	public String toFileName() {
		return (Faces.toNumeralString(this.getFace()) + "_of_" + this.getSuit().toString().toLowerCase() + "s");
	}

	@Override
	public String toString() {
		return (Faces.toNumeralString(this.getFace()) + " of " + this.getSuit().toString().toLowerCase() + "s");
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		else if (obj == this) { return true; }
		else if (!(obj instanceof Card)) { return false; }

		Card other = (Card) obj;
		return (this.getFace().equals(other.getFace()));
	}

	@Override
	public int compareTo(Card other) {
		return this.getFace().compareTo(other.getFace());
	}

	public Suits getSuit() { return suit; }
	public Faces getFace() { return face; }

	public void setSuit(Suits suit) { this.suit = suit; }
	public void setFace(Faces face) { this.face = face; }


	@Override
	public boolean test() {
		System.out.println("Testing class: Card");
		Card card = new Card(Suits.SPADE, Faces.KING);
		System.out.println(card);

		card.setFace(Faces.TWO);
		card.setSuit(Suits.HEART);
		System.out.println(card);
		System.out.println(card + ", Suit = " + card.getSuit());
		System.out.println(card + ", Face = " + card.getFace());

		Card copy = new Card(Suits.DIAMOND, Faces.TWO);
		System.out.println(card + " == " + copy + " : " + card.equals(copy));

		Card card2 = new Card(Suits.DIAMOND, Faces.FOUR);
		Card card3 = new Card(Suits.DIAMOND, Faces.FIVE);
		System.out.println(card2);
		System.out.println(card3);
		System.out.println(card2 + " compareTo " + card3 + " : " + card2.compareTo(card3));
		System.out.println(card2 + " == " + card3 + " : " + card2.equals(card3));

		return true;
	}
}
