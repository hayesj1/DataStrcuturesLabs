package ach.card;

import ach.test.Testable;

import java.util.Arrays;
import java.util.Iterator;

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

	public Hand() {}

	public void receiveCard(Card card) {
		addCard(card);
	}
	public Card removeCard(Card card) {
		int pos = search(card);

		if(pos == -1) {
			return null;
		} else {
			return cards.remove(pos);
		}
	}

	public Ranking computeRanking() {
		return Ranking.computeRanking(this);
	}

	public Card[] toArray() {
		Card[] ret = new Card[cards.getLength()];
		Iterator<Card> iterator = cards.iterator();
		System.out.println(cards.getEntry(0));
		for (int i = 0; i < ret.length; i++) {
			Card c = iterator.next();
			ret[i] = c;
			System.out.println(ret[i] + ", " + c);
		}
		return ret;
	}

	@Override
	public String toString() {
		return ("Your Hand:\n" + super.toString());
	}


	@Override
	public boolean test() {
		System.out.println("Testing class: Hand");

		Hand testHand = new Hand();
		Card testCard = new Card( Suits.CLUB,Faces.TWO );
		Card testCard2 = new Card(Suits.DIAMOND, Faces.KING);

		System.out.println(testHand.toString());
		testHand.addCard(testCard);
		testHand.removeCard();
		System.out.println(testHand.toString());

		testHand.addCard(testCard);
		testHand.addCard(testCard2);
		System.out.println(testHand.toString());

		testHand.removeCard(testCard);
		System.out.println(testHand.toString());

		testHand.addCard(testCard);
		testHand.computeRanking();

		System.out.println(Arrays.toString(testHand.toArray()));


		return true;
	}
}
