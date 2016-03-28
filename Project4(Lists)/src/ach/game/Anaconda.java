package ach.game;

import ach.card.Card;
import ach.card.Deck;
import ach.card.Ranking;
import ach.gui.DisplayHandChoices;

import java.util.ArrayList;
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

	private int remainingPlayers;

	public Anaconda(int numPlayers, int startingPotSize, int startingStashSize) {
		assert (numPlayers >= 3 && numPlayers <= 8) : ("Too " + ((numPlayers < 3) ? "few" : "many") + " players!");
		Scanner s = new Scanner(System.in);

		this.initialPotSize = startingPotSize;
		this.initialStashSize = startingStashSize;
		this.remainingPlayers = numPlayers;

		this.players = new Player[numPlayers];
		this.pot = new Stash();
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
		System.out.println("Starting Hand #" + handNo + ":");

		System.out.println("Shuffling Deck...");
		deck.shuffle(Deck.NUM_PASSES_ON_DEAL);
		System.out.println("Dealing Cards");
		deck.deal(players);
		bet();

		for (int i = 3; i > 0; i--) {
			passCards(i);
			bet();
		}

		Player winner = getHandWinner();
		ArrayList<Player> winners = null;
		int leftOvers = 0;

		if(winner != null) { // No tie
			winner.addWinnings(pot.getTotalValue());
		} else { // There was a tie
			winners = new ArrayList<>();
			Ranking win = players[0].getCurrRank();
			for (int i = 1; i < players.length; i++) { // Get the winning rank
				if(players[i].getCurrRank().compareTo(win) > 0) {
					win = players[i].getCurrRank();
				}
			}
			for(Player p : players) { // Players with the winning rank are added to winners
				int compRank = p.getCurrRank().compareTo(win);
				if(compRank == 0) {
					winners.add(p);
				}
			}

			int numWinners = winners.size();
			int potSize = pot.getTotalValue();
			int winnings = potSize / numWinners;
			leftOvers = potSize % numWinners;
		}

		pot.reset();
		removeLosers(detectLosers());

		System.out.println("Hand Complete! The Winner is: " + (winners == null ? winner : winners));
	}

	private void bet() {

	}

	private void passCards(int numCardsToPass) {
		Card[][] cardsToPass = new Card[players.length][numCardsToPass];
		for(int i = 0; i < players.length; i++) {
			DisplayHandChoices dialog = new DisplayHandChoices(players[i].getHand(), numCardsToPass);
			dialog.setVisible(true);
			do {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {}
				cardsToPass[i] = dialog.getSelection();
			} while(cardsToPass[i] == null); // Keep looping until the player has selected the correct number of cards
		}
		// Pass the cards stopping right before the last player passes
		for (int i = 0; i < players.length - 1; i++) {
			for (int j = 0; j < numCardsToPass; j++) {
				Card pass = players[i].passCard(cardsToPass[i][j]);
				players[i+1].addCardToHand(pass);
			}
		}
		// Pass last player's cards to the first player
		for (int i = 0; i < numCardsToPass; i++) {
			Card pass = players[players.length-1].passCard(cardsToPass[players.length-1][i]);
			players[0].addCardToHand(pass);
		}
	}

	/**
	 * Gets the winner of a hand round
	 * @return the winner; or null if there was a tie
	 */
	private Player getHandWinner() {
		Player winner = players[0];
		for (Player p : players) {
			if(p.equals(winner)) { continue; }
			int compRank = p.getCurrRank().compareTo(winner.getCurrRank());
			if(compRank > 0) {
				winner = p;
			} else if(compRank == 0) {
				int compHigh = p.getCurrRank().getHigh().compareTo(winner.getCurrRank().getHigh());
				if(compHigh > 0) {
					winner = p;
				} else if(compHigh == 0) {
					return null;
				}
			}
		}

		return winner;
	}


	private Player[] detectLosers() {
		ArrayList<Player> losers = new ArrayList<>();
		for (Player p : players) {
			if(p.getStashValue() <= 0) {
				losers.add(p);
				remainingPlayers--;
			}
		}
		return losers.toArray(new Player[players.length - remainingPlayers]);
	}

	private void removeLosers(Player[] losers) {
		for (Player p : losers) {
			for (int i = 0; i < players.length; i++) {
				if(players[i].equals(p)) {
					players[i] = null;
				}
			}
		}
	}

	public boolean gameOver() { return remainingPlayers == 1; }

	/**
	 * Gets the winner; the last player remaining
	 * @return the player who won if the game is over; null otherwise
	 */
	public Player getGameWinner() {
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
