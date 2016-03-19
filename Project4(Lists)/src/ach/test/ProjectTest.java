package ach.test;

import ach.card.Card;
import ach.card.CardPile;
import ach.card.Deck;
import ach.card.Hand;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by hayesj3 on 3/18/2016.
 */
public class ProjectTest {

	public static void main(String[] args) {
		Card[] cards = new Card[3];
		CardPile pile = new CardPile();
		CardPile pile2 = new CardPile(cards);
		Deck deck = new Deck();
		Deck deck2 = new Deck(pile2);
		Hand hand = new Hand(deck, deck2);

		Testable.test(cards[0]);
		Testable.test(pile);
		Testable.test(deck);
		Testable.test(hand);
	}
}
