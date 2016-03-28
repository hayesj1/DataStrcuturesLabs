package ach.card;

import ach.list.DoubleLinkedList;
import ach.test.Testable;

import java.util.Iterator;
import java.util.Random;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by hayesj3 on 3/18/2016.
 */
public class CardPile implements Testable {

	public static final int MINIMUM_PASSES = 2;

	protected DoubleLinkedList<Card> cards;

	public CardPile() {
		cards = new DoubleLinkedList<>();
	}
	public CardPile(Card[] cards) {
		this();
		if(cards != null) {
			for (Card card : cards) {
				this.cards.add(card);
			}
		} else {
			System.err.println("Null card[] provided to Deck constructor!");
		}
	}

	/** Returns the top-most card in the pile **/
	protected Card removeCard() { return cards.remove(0); }
	/** Adds a card to the bottom of the pile **/
	protected void addCard(Card card) { cards.add(card);}

	/**
	 * Finds the position of the given card in the pile
	 * @param c the card to search for
	 * @return the position of the card if found; -1 otherwise
	 */
	public int search(Card c) {
		if (!cards.contains(c)) {
			return -1;
		} else {
			int pos;
			Iterator<Card> it = cards.iterator();
			for (pos = 0; !(it.next().equals(c)) && pos < cards.getLength(); pos++);
			return pos;
		}
	}

	/**
	 * Spilts the pile into two halves
	 * <p>
	 * In the case of an odd number of cards in the original pile, \n
	 * the second half will have one more card than the first.\n
	 * In every other case, both havles will have the same length
	 *
	 * @return an array of CardPiles with length 2. Index 0 is the first half, and index 1 is the second half
	 */
	public CardPile[] split() {
		int middle = cards.getLength()/2;
		CardPile[] ret = new CardPile[2];
		Card[] cards1 = new Card[middle];
		Card[] cards2 = new Card[cards.getLength()-middle];
		int i;
		for (i = 0; i < middle; i++) {
			cards1[i] = this.cards.getEntry(i);
		}
		for (int j = 0; j < this.cards.getLength()-middle; j++, i++) {
			cards2[j] = this.cards.getEntry(i);
		}
		ret[1] = new CardPile(cards1);
		ret[2] = new CardPile(cards2);

		return ret;
	}

	/**
	 * Shuffles the cards in this CardPile
	 */
	public void shuffle() {
		this.shuffle(MINIMUM_PASSES);
	}
	/**
	 * Shuffles the cards in this CardPile
	 * @param passes the number of times to shuffle; a minimum of 2 passes will always be executed
	 */
	public void shuffle(int passes) {
		if (passes < MINIMUM_PASSES) { passes = MINIMUM_PASSES; }

		Random rand = new Random(System.currentTimeMillis());
		for (int i = 0; i < passes; i++) {
			for (int j = 0; j < cards.getLength()/2; j++) {
				int pos = j + rand.nextInt(cards.getLength() - j);
				Card first = cards.getEntry(j);
				Card second = cards.replace(pos, first);
				cards.replace(j, second);

			}
		}
	}

	public Card getTopCard() { return cards.getEntry(0); }
	public Card getBottomCard() { return cards.getEntry(cards.getLength()); }

	@Override
	public String toString() {
		StringBuilder strBlder = new StringBuilder();
		for (Card c : cards) {
			strBlder.append(c);
			strBlder.append('\n');
		}
		return strBlder.toString();
	}

	@Override
	public boolean test() {
		System.out.println("Testing class: CardPile");
		Card testCard1 = new Card(Suits.CLUB, Faces.KING);
		Card testCard2 = new Card(Suits.DIAMOND, Faces.ACE);
		Card testCard3 = new Card(Suits.HEART, Faces.TEN);
		Card testCard4 = new Card(Suits.SPADE, Faces.NINE);
		Card testCard5 = new Card(Suits.CLUB, Faces.EIGHT);

		Card[] cArray = {testCard1,testCard2,testCard3,testCard4,testCard5};
		CardPile testPile = new CardPile(cArray);

		System.out.println(testPile.removeCard().toString());
		testPile.addCard(testCard3);
		System.out.println(testPile.getTopCard().toString());
		System.out.println(testPile.getBottomCard().toString());

		System.out.println(testPile.search(testCard3));

		testPile.shuffle();
		System.out.println(testPile.getTopCard().toString());
		System.out.println(testPile.getBottomCard().toString());

		testPile.shuffle();
		System.out.println(testPile.getTopCard().toString());
		System.out.println(testPile.getBottomCard().toString());

		return true;
	}
}
