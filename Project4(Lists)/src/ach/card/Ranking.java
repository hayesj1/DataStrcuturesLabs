package ach.card;

import java.util.Arrays;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by hayesj3 on 3/23/2016.
 */
public enum Ranking {
	High_Card,
	Pair,
	Two_Pair,
	Three_Kind,
	Straight,
	Flush,
	Full_House,
	Four_Kind,
	Straight_Flush,
	Royal_Flush;

	private Card high = null;
	public Card getHigh() { return this.high; }
	public void setHigh(Card card) { this.high = card; }

	static Ranking computeRanking(Hand hand) {
		Card[] cards = hand.toArray();
		Card high = getHigh(cards);
		Ranking rank;

		if(isRF(cards)) {
			rank = Royal_Flush;
		} else if(isSF(cards)) {
			rank = Straight_Flush;
		} else if(isFK(cards)) {
			rank = Four_Kind;
		} else if(isFH(cards)) {
			rank = Full_House;
		} else if(isF(cards)) {
			rank = Flush;
		} else if(isS(cards)) {
			rank = Straight;
		} else if(isTK(cards)) {
			rank = Three_Kind;
		} else if(isTP(cards)) {
			rank = Two_Pair;
		} else if(isP(cards)) {
			rank = Pair;
		} else {
			rank = High_Card;
		}
		rank.setHigh(getHigh(cards));
		return rank;
	}

	private static boolean isRF(Card[] cards) {
		boolean royalStraight = true, flush = (isF(cards));
		Card[] scards = deepCopyArray(cards);
		Arrays.sort(scards, (o1, o2) -> (o1.compareTo(o2)));
		Faces face = Faces.TEN;
		for (int i = 0; royalStraight && i < scards.length; i++) {
			if(scards[i].getFace() == face) {
				face = Faces.valueOf(face.ordinal()+3); //Two is the first Face, so its ordinal
														// is 0, so offset ordinal by +3; +2 for
														// the face value, and +1 for the next face
			} else if (i != 0) {
				royalStraight = false;
			}
		}
		return royalStraight && flush;
	}
	private static boolean isSF(Card[] cards) {
		return (isS(cards) && isF(cards));
	}
	private static boolean isFK(Card[] cards) {
		boolean ret = false;
		Card matcher, card2, card3, card4;
		for (Card card1 : cards) {
			matcher = card1;

			card2 = findMatch(matcher, cards);
			if (card2 == null) { continue; }

			Card[] cards2 = deepCopyArray(cards);
			for(Card c : cards2) {
				if(c.exactEquals(card1) || c.exactEquals(card2)) { c = null; }
			}

			card3 = findMatch(matcher, cards2);
			if(card3 == null) { continue; }

			Card[] cards3 = deepCopyArray(cards2);
			for(Card c : cards3) {
				if(c.exactEquals(card3)) { c = null; }
			}

			card4 = findMatch(matcher, cards3);
			if(card4 != null) {
				ret = true;
				break;
			}
		}
		return ret;
	}
	private static boolean isFH(Card[] cards) {
		boolean foundPair = false, foundThreeKind = false;
		Card matcher, matcher2, card2, card3;
		Card[] cards2 = deepCopyArray(cards);
		Card[] cards3;

		// find pair
		for (Card card1 : cards) {
			matcher = card1;

			card2 = findMatch(matcher, cards);
			if(card2 != null) {
				foundPair = true;
				for (Card c : cards2) {
					if (c.exactEquals(card1) || c.exactEquals(card2)) {
						c = null;
					}
				}
				break;
			}
		}

		// find three of a kind
		for (Card card1 : cards2) {
			matcher2 = card1;

			card2 = findMatch(matcher2, cards2);
			if (card2 == null) { continue; }

			cards3 = deepCopyArray(cards2);
			for (Card c : cards3) {
				if (c.exactEquals(card1) || c.exactEquals(card2)) { c = null; }
			}

			card3 = findMatch(matcher2, cards3);
			if(card3 != null) {
				foundThreeKind = true;
				break;
			}
		}

		return foundPair && foundThreeKind;
	}
	private static boolean isF(Card[] cards) {
		boolean sameSuit = true, firstCardValid = true, lastCardValid = true;
		Card[] scards = deepCopyArray(cards);
		Arrays.sort(scards, (o1, o2) -> (o1.compareTo(o2)));
		Card card1;
		for (int i = 0; sameSuit && i < scards.length-1; i++) {
			Card c1 = scards[i];
			Card c2 = scards[i+1];
			if(!c1.sameSuit(c2)) {
				if(i == 0 ) {
					firstCardValid = false;
				} else if(i == scards.length-1) {
					lastCardValid = false;
				} else {
					sameSuit = false;
				}
			}
		}

		return sameSuit && (firstCardValid || lastCardValid);
	}
	private static boolean isS(Card[] cards) {
		boolean consecutive = true, firstCardValid = true, lastCardValid = true;
		Card[] scards = deepCopyArray(cards);
		Arrays.sort(scards, (o1, o2) -> (o1.compareTo(o2)));
		Card card1;
		for (int i = 0; consecutive && i < scards.length-1; i++) {
			Faces f1 = scards[i].getFace();
			Faces f2 = scards[i+1].getFace();
			if(f1.ordinal()+1 != f2.ordinal()) {
				if(i == 0 ) {
					firstCardValid = false;
				} else if(i == scards.length-1) {
					lastCardValid = false;
				} else {
					consecutive = false;
				}
			}
		}

		return (consecutive && (firstCardValid || lastCardValid));
	}

	private static boolean isTK(Card[] cards) {
		boolean ret = false;
		Card matcher, card2, card3;
		for (Card card1 : cards) {
			matcher = card1;

			card2 = findMatch(matcher, cards);
			if (card2 == null) { continue; }

			Card[] cards2 = deepCopyArray(cards);
			for(Card c : cards2) {
				if(c.exactEquals(card1) || c.exactEquals(card2)) { c = null; }
			}

			card3 = findMatch(matcher, cards2);
			if(card3 != null) {
				ret = true;
				break;
			}
		}
		return ret;
	}
	private static boolean isTP(Card[] cards) {
		boolean ret = false;
		boolean firstpairFound = false;
		Card matcher1 = null,  matcher2, card1, card2;

		for(Card card : cards) {
			matcher1 = card;
			card1 = findMatch(matcher1, cards);
			if(card1 != null) {
				firstpairFound = true;
			}
			if(firstpairFound) { break; }
		}

		if(!firstpairFound) {
			return false;
		}
		for(Card card : cards) {
			matcher2 = card;
			if(matcher2.equals(matcher1)) {	continue; }

			card2 = findMatch(matcher2, cards);
			if (card2 != null) {
				ret = true;
			}
			if (ret) { break; }
		}
		return ret;
	}
	private static boolean isP(Card[] cards) {
		boolean ret = false;
		Card card1, card2;

		for (int i = 0;!ret && i < cards.length; i++) {
			card1 = cards[i];
			card2 = findMatch(card1, cards);
			if (card2 != null) {
				ret = true;
			}
		}
		return ret;
	}
	private static Card getHigh(Card[] cards) {
		Card high = cards[0];
		for (int i = 1; i < cards.length; i++) {
			if(cards[i].compareTo(high) > 0) {
				high = cards[i];
			}
		}
		return high;
	}

	/**
	 * Searches for a card with the same denomination as <code>matcher</code> in <code>cards</code>
	 * @param matcher The card to look for
	 * @param cards the array of cards to search in
	 * @return the matching card if found; null otherwise
	 */
	private static Card findMatch(Card matcher, Card[] cards) {
		boolean matchFound = false;
		Card card, ret = null;
		for (int i = 0; !matchFound && i < cards.length; i++) {
			card = cards[i];
			if(card == null) { continue; } //skip null cards
			if (card != matcher && card.equals(matcher)) { //make sure card and matcher aren't the same object, but are equal objects
				matchFound = true;
				ret = card;
			}
		}
		return ret;
	}

	private static Card[] deepCopyArray(Card[] src) {
		Card[] ret = new Card[src.length];

		for (int i = 0; i < src.length; i++) {
			Card card = new Card(src[i].getSuit(), src[i].getFace());
			ret[i] = card;
		}

		return ret;
	}
}
