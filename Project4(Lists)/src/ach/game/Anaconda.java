package ach.game;

import ach.card.Deck;

import java.util.Scanner;

/**
 *
 * This main class for the card game;
 *
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by hayesj3 on 3/23/2016.
 */
public class Anaconda {

	public static final int NUM_CARDS_DEALT = 6;

	private int initialPotSize;
	private int initialStashSize;
	private Stash pot;
	private Player[] players;
	private Deck deck;

	public Anaconda(int numPlayers, int startingPotSize, int startingStashSize) {
		assert (numPlayers >= 3 && numPlayers <= 8) : ("Too " + ((numPlayers < 3) ? "few" : "many") + " players!");
		Scanner s = new Scanner(System.in);

		this.initialPotSize = startingPotSize;
		this.initialStashSize = startingStashSize;
		this.players = new Player[numPlayers];
		this.deck = new Deck();

		deck.shuffle();

		for (int i = 0; i < numPlayers; i++) {
			System.out.println("Enter Player " + (i+1) + "'s name: ");
			players[i] = new Player(s.nextLine());
		}
	}

	public void init() {
		System.out.println("Initializing Game!");

		System.out.println("Adding initial chips to pot...");
		pot.addValue(initialPotSize);

		System.out.println("Giving players initial chips...");
		if(initialStashSize == -1) { //true if initial stack isn't given on the commandline
			for (Player player : players) { player.addChips(0,4,19,5,10,25); } //Default is $1,000 in chips
		} else {
			for (Player player : players) { player.addWinnings(initialStashSize); }
		}

		System.out.println("Initialization Complete!");
	}

	public void start(int handNo) {
		System.out.println("Starting Hand#" + handNo + ":");

		System.out.println("Shuffling Deck...");
		deck.shuffle(Deck.NUM_PASSES_ON_DEAL);
		System.out.println("Dealing Cards");
		deck.deal(players);

		for (int i = 3; i > 0; i--) {
			beginRound(i);
		}

		System.out.println("Hand Complete! The Winner is: " + getWinner());
	}

	private void beginRound(int numCardsToPass) {
		//betRound();
		for(int i = 0; i < players.length; i++) {
			//players[i].showHand();
			//players[i].getSelection();
			//players[i].hideHand();
		}
	}

	public boolean gameOver() {
		int count = 0;
		for (int i = 0; i < players.length; i++) {
			if(players[i] != null) { count++; }
		}
		return count == 1;
	}

	/**
	 * Gets the winner; the last player remaining
	 * @return the player who won if the game is over; null otherwise
	 */
	public Player getWinner() {
		Player winner = null;
		if(gameOver()) {
			for (int i = 0; i < players.length; i++) {
				if (players[i] != null) {
					winner = players[i];
					break;
				}
			}
		}
		return winner;
	}
}
