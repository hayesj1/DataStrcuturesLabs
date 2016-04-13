package ach.game;

import ach.test.Testable;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

/**
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *         <p>
 *         Created by hayesj3 on 3/23/2016.
 */
public class Stash implements Testable {
	EnumMap<Chips, Integer> stash;

	public Stash() {
		this.stash = new EnumMap<>(Chips.class);
		for (Chips c : Chips.reverseValues()) {
			this.stash.put(c, 0);
		}
	}

	public void addChips(Chips chip, int count) {
		int oldCount = stash.get(chip);
		stash.replace(chip, count + oldCount);
	}

	/**
	 * Removes <code>count</code> number of chips of type <code>chip</code> from the stash
	 * <p>
	 *     This function will never remove more chips of a given type then the number of those
	 *     chips already in the stash from the stash
	 * </p>
	 *
	 * @param chip the chip type to remove
	 * @param count the amount to remove
	 * @return 0 if <code>count</code> number of chips was removed, the number of chips leftover otherwise
	 */
	public int removeChips(Chips chip, int count) {
		int oldValue = stash.get(chip);
		int rem;

		if(oldValue <= count) {
			rem = oldValue;
		} else {
			rem = count;
		}

		stash.replace(chip, oldValue - rem);
		return count - rem;
	}

	public void addValue(int value) {
		Chips[] chips = Chips.reverseValues();
		int numChips = 0;
		int dividend = value;
		int divisor, quotient, remainder;

		int i;
		for (i = 0, quotient = 0; i < chips.length && (i == 0 || quotient != 0); i++, dividend = quotient) {
			divisor = chips[i].getValue();
			if(divisor >= dividend) { continue; }
			quotient = dividend / divisor;
			remainder = dividend % divisor;
			addChips(chips[i], (remainder == 0) ? quotient : remainder);
		}
	}
	public int removeValue(int value) {
		Chips[] chips = Chips.reverseValues();
		int numChips = 0;
		int dividend = value;
		int divisor, quotient, remainder, extra;

		int i;
		for (i = 0, quotient = 0, extra = 0; i < chips.length && (i == 0 || quotient != 0); i++, dividend = quotient + extra) {
			divisor = chips[i].getValue();
			if(divisor >= dividend) { continue; }

			quotient = dividend / divisor;
			remainder = dividend % divisor;
			extra = removeChips(chips[i],  (remainder == 0) ? quotient : remainder);
			extra *= (extra != 0) ? divisor : 1;
		}
		return extra;
	}

	public int getTotalChipValue(Chips chip) {
		int count = stash.get(chip);
		return count * chip.getValue();
	}
	public int getTotalValue() {
		int total = 0;
		Set<Map.Entry<Chips, Integer>> entries =  stash.entrySet();
		for (Map.Entry<Chips, Integer> entry : entries) {
			total += (entry.getKey().getValue() * entry.getValue());
		}
		return total;
	}

	public void reset() {
		stash.forEach((chips, integer) -> integer = 0 );
	}

	@Override
	public String toString() {
		StringBuilder blder = new StringBuilder();
		for (Map.Entry<Chips, Integer> entry : stash.entrySet()) {
			blder.append(entry.getKey());
			blder.append(": ");
			blder.append(entry.getValue());
			if(!entry.getKey().equals(Chips.Purple)) { blder.append(" | "); }
		}
		return blder.toString();
	}


	@Override
	public boolean test() {
		System.out.println("Testing class: Stash");

		Stash testStash = new Stash();

		testStash.addValue(100);
		System.out.println(testStash.toString());
		testStash.removeValue(25);

		System.out.println(testStash.toString());

		testStash.addChips(Chips.Black, 20);
		System.out.println(testStash.getTotalValue());
		testStash.removeValue(5);

		System.out.println(testStash.getTotalValue());
		testStash.reset();
		System.out.println(testStash.getTotalValue());

		System.err.println("Chip-Value Conversion Algorithm NYI!\nChip-Value Conversion Algorithm probably broken! Incorrect values probably displayed");

		return true;
	}
}
