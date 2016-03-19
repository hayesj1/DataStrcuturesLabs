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

		if (this.face == null || (this.suit == Suits.JOKER && this.face != Faces.JOKER)) {
			throw new IllegalArgumentException("Invalid face, " + face + "/" + this.face + ", for Suit: " + this.getSuit());
		}
	}

	public Card(Suits suit, Faces face) {
		this.suit = suit;
		this.face = face;
	}

	@Override
	public String toString() {
		return (this.getFace() + " of " + this.getSuit() + "s");
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		else if (obj == this) { return true; }
		else if (!(obj instanceof Card)) { return false; }

		Card other = (Card) obj;
		return ((this.getSuit().equals(other.getSuit())) && (this.getFace().equals(other.getFace())));
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

		card.setFace(Faces.JOKER);
		card.setSuit(Suits.JOKER);
		System.out.println("card : " + card);
		System.out.println("card.getSuit() : " + card.getSuit());
		System.out.println("card.getFace() : " + card.getFace());

		Card copy = new Card(Suits.JOKER, Faces.JOKER);
		System.out.println("Joker == Joker : " + card.equals(copy));

		Card card2 = new Card(Suits.DIAMOND, Faces.FOUR);
		Card card3 = new Card(Suits.DIAMOND, Faces.FIVE);
		System.out.println("Card A : " + card2);
		System.out.println("Card B : " + card3);
		System.out.println("A.compareTo(B) : " + card2.compareTo(card3));
		System.out.println("A == B : " + card2.equals(card3));

		return true;
	}
}
