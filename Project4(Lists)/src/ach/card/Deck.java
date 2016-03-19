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
public class Deck extends CardPile implements Testable {

	public Deck() {
		for (Suits suit : Suits.values()) {
			for (Faces face : Faces.values()) {
				cards.add(new Card(suit, face));
			}
		}
	}

	public Deck(CardPile pile) {
		this(pile.cards.toArray());
	}
	public Deck(Card[] cards) {
		super(cards);
	}

	public Card drawCard() { return removeCard(); }
	public void discardCard(Card card) { addCard(card); }


	@Override
	public boolean test() {
		System.out.println("Testing class: Deck");
		//TODO add actual testing here

		return true;
	}
}
