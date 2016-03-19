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
public class Hand extends CardPile implements Testable{
	Deck drawDeck;
	Deck discardDeck;

	public Hand(Deck drawDeck, Deck discardDeck) {
		this.drawDeck = drawDeck;
		this.discardDeck = discardDeck;
	}

	public void Draw() {
		addCard(drawDeck.drawCard());
	}
	public void Discard(Card card) {
		discardDeck.discardCard(removeCard(card));
	}

	@Override
	public String toString() {
		return ("Your Hand:\n" + super.toString());
	}

	protected Card removeCard(Card card) {
		int pos = search(card);

		if(pos == -1) {
			return null;
		} else {
			return cards.remove(pos);
		}
	}

	@Override
	public boolean test() {
		System.out.println("Testing class: Hand");
		//TODO add actual testing here

		return true;
	}
}
