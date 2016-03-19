package ach.game;

import ach.card.Hand;
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

	private Hand hand;

	public Player(Hand hand) {
		this.hand = hand;
	}

	@Override
	public boolean test() {
		System.out.println("Testing class: Player");
		//TODO add actual testing here

		return true;
	}
}
