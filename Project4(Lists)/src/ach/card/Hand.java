package ach.card;

import ach.game.Stash;
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

	Stash stash;

	public Hand() {
		this.stash = new Stash();
	}

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

	protected void computeRanking() {

	}

	public Card[] toArray() {
		return cards.toArray();
	}

	@Override
	public String toString() {
		return ("Your Hand:\n" + super.toString());
	}


	@Override
	public boolean test() {
		System.out.println("Testing class: Hand");
		//TODO add actual testing here
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



		return true;
	}
}
