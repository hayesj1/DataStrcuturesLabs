package ach.notation;

import ach.stack.ArrayStack;

/**
 * Created by camasok on 2/4/2016.
 */
public class InfixToPostfix
{
	public String convert(String infix) {
		ArrayStack<Character> operatorStack = new ArrayStack<>();
		StringBuilder postfix = new StringBuilder("");
		int pos = 0;
		while (pos < infix.length())
		{
			while(Character.isWhitespace(infix.charAt(pos++)));
			char next = infix.charAt(pos);

			if (Character.isAlphabetic(next)) {

			}
			/*switch (next)
			{
				case '^':
					operatorStack.push(nextCharacter)
					break
				case '+' or '-' or '*' or '/' :
					while (operatorStack is not empty and
					precedence (nextCharacter <= precedence of operatorStack.peek())
				{
					Append operatorStack.peek() to postfix
					operatorStack.pop()
				}
				operatorStack.push (nextCharacter)
				break
				case '(':
					operatorStack.push (nextCharacter)
					break;
				8
				case ')' : // the stack is not empty
					topOperator = operatorStack.pop()
					while (topOperator != '(')
					{
						append topOperator to postfix
						topOperator = operatorStack.pop()
					}
					break;
				default:
					break // ignore unexpected characters
			} */
			pos++;
		}
		while (!operatorStack.isEmpty())
		{
			char topOperator = operatorStack.pop();
			postfix.append(topOperator);
		}
		return postfix.toString();
	}
}
