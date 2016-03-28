package ach.game;

import ach.card.Card;
import ach.card.Hand;
import ach.card.Ranking;
import ach.test.Testable;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by hayesj3 on 3/19/2016.
 */
public class Player implements Testable{

	private static int numPlayers = 0;

	private int playerID;
	private String name;
	private Hand hand;
	private Stash stash;
	private Ranking currRank;

	public Player(String name) {
		this.playerID = ++numPlayers;
		this.name = name;
		this.hand = new Hand();
		this.stash = new Stash();
	}

	public void computeRank() {
		this.currRank = this.hand.computeRanking();
	}

	public void addCardToHand(Card c) {
		this.hand.receiveCard(c);
	}
	public Card passCard(Card c) {
		return this.hand.removeCard(c);
	}

	public void addChips(int orange, int black, int green, int blue, int red, int white) {
		this.stash.addChips(Chips.Purple, orange);
		this.stash.addChips(Chips.Black, black);
		this.stash.addChips(Chips.Green, green);
		this.stash.addChips(Chips.Blue, blue);
		this.stash.addChips(Chips.Red, red);
		this.stash.addChips(Chips.White, white);
	}

	public void addWinnings(int winnings) {
		this.stash.addValue(winnings);
	}
	public int getStashValue() { return stash.getTotalValue(); }

	public Ranking getCurrRank() { return currRank; }
	public Hand getHand() { return hand; }

	@Override
	public String toString() {
		return ("Player " + name + "'s stash has a value of " +
				stash.getTotalValue() + ", and chips:\n" + stash.toString());
	}


	@Override
	public boolean test() {
		System.out.println("Testing class: Player");
		//TODO add actual testing here

		return true;
	}
}
