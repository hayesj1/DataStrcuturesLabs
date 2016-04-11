package ach.card;

import ach.game.Anaconda;
import ach.game.Player;
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

	public static final int NUM_PASSES_ON_DEAL = 4;
	
	public Deck() {
		for (Suits suit : Suits.values()) {
			for (Faces face : Faces.values()) {
				Card temp = new Card(suit, face);
				cards.add(temp);
			}
		}
	}

	public Deck(CardPile pile) {
		this(pile.cards.toArray());
	}
	public Deck(Card[] cards) {
		super(cards);
	}

	public void deal(Player[] players) {
		for (int i = 0; i < Anaconda.NUM_CARDS_DEALT; i++) {
			for (Player p : players) {
				p.addCardToHand(dealCard());
			}
		}
	}
	
	protected Card dealCard() { return removeCard(); }


	@Override
	public boolean test() {
		System.out.println("Testing class: Deck");


		return true;
	}
}
