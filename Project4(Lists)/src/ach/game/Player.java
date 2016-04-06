package ach.game;

import ach.card.Card;
import ach.card.Hand;
import ach.card.Ranking;
import ach.gui.PlayerGUI;
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
	private int betAmount;
	private Ranking currRank;
	private PlayerGUI gui;


	public Player(String name) {
		this.playerID = ++numPlayers;
		this.name = name;
		this.hand = new Hand();
		this.stash = new Stash();
		betAmount = 0;
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

	public void removeValueFromStash(int value) { this.stash.removeValue(value); }
	public void addValueToStash(int value) { this.stash.addValue(value); }
	public int getStashValue() { return stash.getTotalValue(); }

	/**
	 * Adds val to this player's bet, while removing val from this player's stash
	 * <p>NOTE: if <code>val > this.stash.getTotalValue()</code> then the player's bet's value is increased by <code>val - this.stash.getTotalValue()</code></p>
	 *
	 * @param val the amount this player should add to his/her bet
	 */
	public void addToBet(int val) {
		int extraVal = stash.removeValue(val); // leftover chip value if val is greater than the player's stash value
		betAmount += (extraVal == 0) ? val : val - extraVal;
	}

	public int getBet() { return betAmount; }
	public int getPlayerID() { return playerID; }
	public Ranking getCurrRank() { computeRank(); return currRank; }
	public Hand getHand() { return hand; }
	public PlayerGUI getGui() { return gui; }

	public void setBetAmount(int newBet) {
		int oldBet = betAmount;
		this.stash.addValue(oldBet);
		this.stash.removeValue(newBet);
		this.betAmount = newBet;
	}
	public void setGui(PlayerGUI gui) { this.gui = gui; }

	@Override
	public String toString() {
		return name;
	}


	@Override
	public boolean test() {
		System.out.println("Testing class: Player");
		//TODO add actual testing here

		return true;
	}
}
