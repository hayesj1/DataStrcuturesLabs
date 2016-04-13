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
		this(pile.getCards().toArray(new Card[pile.getCards().getLength()]));
	}
	public Deck(Card[] cards) {
		super(cards);
	}

	public void deal(Player[] players) { this.deal(players, Anaconda.NUM_CARDS_DEALT); }
	public void deal(Player[] players, int numDealt) {
		for (int i = 0; i < numDealt; i++) {
			for (Player p : players) {
				p.addCardToHand(dealCard());
			}
		}
	}
	protected Card dealCard() { return removeCard(); }


	@Override
	public boolean test() {
		System.out.println("Testing class: Deck");
		Deck deck1 = new Deck();
		Deck deck2 = new Deck(new Card[] { new Card(Suits.DIAMOND, Faces.ACE), new Card(Suits.DIAMOND, Faces.TWO), new Card(Suits.DIAMOND, Faces.THREE) });
		Player[] players = new Player[] { new Player("1"), new Player("2"), new Player("3") };

		System.out.println("Before Dealing:");
		displayTestHands(players);


		deck1.deal(players);
		System.out.println("After Deal 1:");
		displayTestHands(players);

		deck2.deal(players, 1);
		System.out.println("After Deal 2:");
		displayTestHands(players);

		return true;
	}

	private void displayTestHands(Player[] players) {
		for (Player p : players) {
			System.out.println(p + "\'s hand: " + p.getHand());
		}
	}
}
