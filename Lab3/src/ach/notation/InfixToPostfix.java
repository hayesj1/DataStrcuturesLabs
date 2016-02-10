package ach.notation;

import ach.stack.ArrayStack;

/**
 *
 * Group Members: Christian Abate-Wong, Karen Camaso, Jacob Hayes
 *
 * @author Christian Abate-Wong
 * @author Karen Camaso
 * @author Jacob Hayes
 *
 * Created by camasok on 2/4/2016.
 */
public class InfixToPostfix
{
	/**
	 * Takes in an infix expression and converts it to postfix
	 * @param infix the expression to convert
	 * @return a postfix version of infix
	 */
	public static String convert(String infix) {
		ArrayStack<Character> operatorStack = new ArrayStack<>();
		StringBuilder postfix = new StringBuilder("");
		int pos = 0;
		while (pos < infix.length())
		{
			while(Character.isWhitespace(infix.charAt(pos))) {
				pos++;
			}
			char next = infix.charAt(pos);

			if (Character.isAlphabetic(next) || Character.isDigit(next)) {
				postfix.append(next);
			} else {
				switch (next) {
					case '^':
						operatorStack.push(next);
						break;
					case '+':
					case '-':
					case '*':
					case '/':
						while (!operatorStack.isEmpty() && (precedence(next) <= precedence(operatorStack.peek()))) {
							postfix.append(operatorStack.pop());
						}
						operatorStack.push(next);
						break;
					case '(':
						operatorStack.push(next);
						break;
					case ')': // the stack is not empty
						char topOperator = operatorStack.pop();
						while (topOperator != '(') {
							postfix.append(topOperator);
							topOperator = operatorStack.pop();
						}
						break;
					default:
						break; // ignore unexpected characters
				}
			}
			pos++;
		}
		while (!operatorStack.isEmpty())
		{
			char topOperator = operatorStack.pop();
			postfix.append(topOperator);
		}
		return postfix.toString();
	}

	/**
	 * Returns 1 for addition or subtraction, and 2 for multiplication and division, and 0 if op is not an operator
	 * @param op the operator to test
	 * @return 0,1,2 depending on the value of op
	 */
	private static int precedence(char op) {
		return (
				(op == '+') ? 1 : (op == '-') ? 1: (op == '*') ? 2 : (op == '/') ? 2 : 0);
	}
}
