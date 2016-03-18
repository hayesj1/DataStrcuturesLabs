package ach.card;

import ach.list.DoubleLinkedList;
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
public class CardPile implements Testable {
	DoubleLinkedList<Card> cards;

	public CardPile() {
		cards = new DoubleLinkedList<>();
	}
	public CardPile(Card[] cards) {
		this();
		for (Card card : cards) { this.cards.add(card); }
	}

	/** Returns the top-most card in the pile **/
	public Card drawCard() { return cards.remove(0); }
	/** Adds a card to the bottom of the pile **/
	public void discard(Card card) { cards.add(card);}

	/**
	 * Spilts the pile into two halves
	 * <p>
	 * In the case of an odd number of cards in the original pile, \n
	 * the second half will have one more card than the first.\n
	 * In every other case, both havles will have the same length
	 *
	 * @return an array of CardPiles with length 2. Index 0 is the first half, and index 1 is the second half
	 */
	public CardPile[] split() {
		int middle = cards.getLength()/2;
		CardPile[] ret = new CardPile[2];
		Card[] cards1 = new Card[middle];
		Card[] cards2 = new Card[cards.getLength()-middle];
		int i;
		for (i = 0; i < middle; i++) {
			cards1[i] = this.cards.getEntry(i);
		}
		for (int j = 0; j < this.cards.getLength()-middle; j++, i++) {
			cards2[j] = this.cards.getEntry(i);
		}
		ret[1] = new CardPile(cards1);
		ret[2] = new CardPile(cards2);

		return ret;
	}

	public Card getTopCard() { return cards.getEntry(0); }
	public Card getBottomCard() { return cards.getEntry(cards.getLength()); }


	@Override
	public boolean test() {
		return true;
	}
}
