package ach.test;

import ach.stack.ArrayStack;

/**
 * Test code for Stack.
 * Created by hayesj3 on 2/2/2016.
 */
public class StackTester {

	/**
	 * Testing driver for ArrayStack
	 * @param args commandline args
	 */
	public static void main(String[] args) {
		ArrayStack<String> strStack = new ArrayStack<>();
		strStack.push("Food is cool");
		System.out.println(strStack.peek());
		strStack.clear();
		System.out.println("isEmpty: " + strStack.isEmpty());
		strStack = new ArrayStack<>(5);
		strStack.push("Bacon");
		strStack.push("Waffles");
		strStack.push("Cheese Burger");
		strStack.push("Bacon Cheese Burger");
		strStack.push("Waffle-Bunned Bacon Cheese Burger");
		outputStackContents(strStack);

		ArrayStack<Integer> intStack = new ArrayStack<>(2);
		intStack.push(1);
		intStack.push(2);
		intStack.push(3);
		intStack.push(4);
		intStack.push(5);
		outputStackContents(intStack);
		System.out.println("Testing completed successfully!");
		}

	/**
	 * empties a stac kand outputs it's contents
	 * @param stack the stack to process
	 */
	private static void outputStackContents(ArrayStack stack) {
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
	}
}
}
