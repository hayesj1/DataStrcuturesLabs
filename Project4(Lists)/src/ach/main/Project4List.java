package ach.main;

import ach.game.Anaconda;

/**
 * Created by hayesj3 on 3/16/2016.
 */
public class Project4List {

	public static void main(String[] args) {
		boolean initialValsFromCLI = false;

		if(args.length != 1 && args.length != 3) {
			System.err.println("Usage: java main.Project4List <numPlayers> [ <startingPot> <startingStash> ]");
			return;
		} else if (args.length == 3) {
			initialValsFromCLI = true;
		}
		int numPlayers, startingPot, startingStash;

		numPlayers = Integer.valueOf(args[0]);
		if(numPlayers < 3 || numPlayers > 8) {
			System.err.println("Invalid number of players! The following must be true: 3 <= numPlayers <= 8");
			return;
		}

		startingPot = (initialValsFromCLI) ? Integer.valueOf(args[1]) : 0;
		startingStash = (initialValsFromCLI) ? Integer.valueOf(args[2]) : -1;

		System.out.println("Welcome to Anaconda!");
		Anaconda game = new Anaconda(numPlayers, startingPot, startingStash);
		game.init();
		int handNo = 1;
		while(!game.gameOver())
		game.start(handNo);
	}
}
