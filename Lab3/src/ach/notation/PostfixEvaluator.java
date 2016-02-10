package ach.notation;


import ach.stack.ArrayStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
public class PostfixEvaluator
{
	/**
	 * holds the previously encountered variables during evaluation, so the user doesn't have to enter values every time a given varialbe is encountered
	 */
	private static Map<Character, Double> vars = new HashMap<>();
	/**
	 * Evaluates a postfix expression using floating point arithmetic, and results in a floating point number.
	 * Supports single character variables and gets a value from the user at run-time. Repeating variables will use a persistent value.
	 * ex: XX+, if the user inputs 2 for X, the second X will automatically use that value.
	 * @param exp the postfix expression to evaluate
	 * @return the floating point result of the expression
	 */
	public static double eval(String exp)
	{
		char operator;
		ArrayStack<Double> resultStack = new ArrayStack<>();
		double val1,val2;
		 for(int i = 0; i < exp.length(); i++)
	     {
	         operator = exp.charAt(i);
		     if (Character.isDigit(operator)) {
			     resultStack.push(Character.getNumericValue(operator) * 1.0);
		     } else if (Character.isAlphabetic(operator)) {
			     if (vars.containsKey(operator)) {
					resultStack.push(vars.get(operator));
			     } else {
				     Scanner user = new Scanner(System.in);
				     System.out.println("Enter a numerical value for: " + operator);
				     Double userval = user.nextDouble();
				     vars.put(operator, userval);
				     resultStack.push(userval);
			     }
	        }

	         switch (operator)
	         {
		         case '^' :
			         val2 = resultStack.pop() * 1.0;
			         val1 = resultStack.pop() * 1.0;
	                 resultStack.push(Math.pow(val1, val2));
			         break;
		         case '+':
			         val2 = resultStack.pop() * 1.0;
			         val1 = resultStack.pop() * 1.0;
	                 resultStack.push(val1+val2);
			         break;
		         case '-' :
					 val2 = resultStack.pop() * 1.0;
					 val1 = resultStack.pop() * 1.0;
					 resultStack.push(val1-val2);
			         break;
		         case '*' :
					 val2 = resultStack.pop() * 1.0;
					 val1 = resultStack.pop() * 1.0;
					 resultStack.push(val1*val2);
			         break;
		         case '/' :
					 val2 = resultStack.pop() * 1.0;
					 val1 = resultStack.pop() * 1.0;
					 resultStack.push(val1/val2);
			         break;
		         default:
			         break;


	         }
	     }
		return resultStack.pop();
	}
}
