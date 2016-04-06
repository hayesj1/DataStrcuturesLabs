package ach.game;

import ach.card.Card;
import ach.card.Deck;
import ach.card.Ranking;
import ach.gui.PlayerGUI;

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

		System.out.println("Initializing Players!");
		for (Player player : players) {
			System.out.println("Giving player " + player + " initial chips...");
			if(initialStashSize == -1) { //true if initial stack isn't given on the commandline
				player.addChips(0,4,19,5,10,25); //Default is $1,000 in chips
			} else {
				player.addValueToStash(initialStashSize);
			}

			System.out.println("Assigning a GUI to player " + player + "...");
			player.setGui(new PlayerGUI(player));
		}
		System.out.println("Player Initialization Complete!");

		System.out.println("Game Initialization Complete!");
	}

	public void start(int handNo) {
		System.out.println("Starting Hand #" + handNo + ":");

		System.out.println("Shuffling Deck...");
		deck.shuffle(Deck.NUM_PASSES_ON_DEAL);
		System.out.println("Dealing Cards");
		deck.deal(players);

		for (Player p : players) {
			p.getGui().resetHandDisplay(); //prepare for gui display
			p.getGui().pack();
		}
		System.out.println("Beginning hand #" + handNo + "!");
		for (int i = 3; i > 0; i--) {
			nextGUIPhase(); // betting phase
			bet();
			nextGUIPhase(); // passing phase
			passCards(i);   // sets each player's gui to wait while remaining players pass
			for (Player player : players) {
				player.getGui().resetHandDisplay();
				player.getGui().setPotValue(this.pot.getTotalValue());
			}
		}

		Player winner = getHandWinner();
		ArrayList<Player> winners = null;
		int leftOvers = 0;

		if(winner != null) { // No tie
			winner.addValueToStash(pot.getTotalValue());
			System.out.println("Hand Complete! The Winner is: " + winner);
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
			winners.forEach(player -> player.addValueToStash(winnings));
			System.out.println("Hand Complete! The Winners of the Tie are: " + winners);
		}

		pot.reset();
		removeLosers(detectLosers());

		pot.addValue(leftOvers);
	}

	private void bet() {
		boolean checkable = true, firstBet = true;
		boolean checked = false, raised = false, called = false;
		int currBet = 0;

		System.out.println("Let the betting begin!");
		do {
			for (Player player : players) {
				PlayerGUI gui = player.getGui();
				gui.setPotValue(this.pot.getTotalValue());

				if(firstBet) {
					gui.setCheckButtonEnabled(false);
					gui.setCallButtonEnabled(false);
					firstBet = false;
				} else {
					gui.setCallButtonEnabled(true);

					if (checkable) { gui.setCheckButtonEnabled(true); }
					else { gui.setCheckButtonEnabled(false); }
				}

				gui.setVisible(true);

				int action;
				do {
					action = gui.getAction();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {}
				} while (action == PlayerGUI.NO_ACTION);

				if(action == PlayerGUI.RAISED) {
					checked = false;
					called = false;
					raised = true;
					checkable = false;

					currBet = gui.getBetValue();

				} else if (action == PlayerGUI.CALLED) {
					checked = false;
					called = true;
					raised = false;
					checkable = false;
					int diff = currBet - player.getBet();
					if (diff == 0) {
						pot.addValue(currBet);
						gui.addToPot(currBet);
					} else {
						player.addToBet(diff);
						pot.addValue(diff);
						gui.addToPot(diff);
					}

				} else if (action == PlayerGUI.CHECKED) {
					checked = true;
					called = false;
					raised = false;
					checkable = true;
				}

				gui.setVisible(false);
			}
		} while ((!checked && !called) || raised);
		System.out.println("Betting complete!");
	}

	private void passCards(int numCardsToPass) {
		Card[][] cardsToPass = new Card[players.length][numCardsToPass];
		for(int i = 0; i < players.length; i++) {
			PlayerGUI gui = players[i].getGui();
			gui.setVisible(true);
			System.out.println("Player " + players[i] + ", please select " + numCardsToPass + " cards to pass!");
			while(gui.getAction() != PlayerGUI.PASSED) { // Keep looping until the player has pressed the pass button
				try {
					Thread.sleep(500); //free up cpu while waiting
				} catch (InterruptedException e) {}
			}
			cardsToPass[i] = gui.getSelected();
			gui.setVisible(false);
			gui.deselect(); //deselects the selected cards
		}
		nextGUIPhase(); // make all guis wait

		System.out.println("Passing cards!");
		// Pass the cards stopping right before the last player passes
		for (int j = 0; j < numCardsToPass; j++) { // pass each card
			Card pass1 = cardsToPass[0][j]; // player 1's card to pass
			Card pass2 = cardsToPass[1][j]; // player 2's card to pass
			Card pass3 = cardsToPass[2][j]; // player 3's card to pass
			int cardPos1 = players[0].getHand().search(pass1); // player 1's passed card's position in their hand
			int cardPos2 = players[1].getHand().search(pass2); // player 2's passed card's position in their hand
			int cardPos3 = players[2].getHand().search(pass3); // player 3's passed card's position in their hand

			// swap the card at player 1's pass position with player 3's passed card,
			// then swap the returned card with the card at player 2's pass position,
			// and then returned card with the card at player 3's pass position
			players[2].getHand().swapCard(players[1].getHand().swapCard(players[0].getHand().swapCard(pass3, cardPos1), cardPos2), cardPos3);
		}
	}

	private void nextGUIPhase() {
		for (Player player : players) {
			player.getGui().nextPhase();
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
