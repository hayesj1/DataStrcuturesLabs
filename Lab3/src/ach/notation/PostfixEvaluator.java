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
public class PostfixEvaluator
{
	public static double eval(String exp)
	{
		double sum=0.0;
		char operator;
		String expression = ""/* WHATEVER */;
		ArrayStack<Double> resultStack = new ArrayStack<>();
		double val1,val2;
		 for(int i = 0; i < exp.length(); i++)
	     {
	         operator = exp.charAt(i);
		     if (Character.isDigit(operator)) {
			     resultStack.push(Character.getNumericValue(operator) * 1.0);
		     }
	         switch (operator)
	         {
		         case '^' :
			         val2 = resultStack.pop();
			         val1 = resultStack.pop();
	                 resultStack.push(Math.pow(val1, val2));
		         case '+':
			         val2 = resultStack.pop();
			         val1 = resultStack.pop();
	                 resultStack.push(val1+val2);
		         case '-' :
                     val2 = resultStack.pop();
                     val1 = resultStack.pop();
                     resultStack.push(val1-val2);
		         case '*' :
                     val2 = resultStack.pop();
                     val1 = resultStack.pop();
                     resultStack.push(val1*val2);
		         case '/' :
                     val2 = resultStack.pop();
                     val1 = resultStack.pop();
                     resultStack.push(val1/val2);

		         default:
				         break;


	         }
	     }
		return resultStack.pop();
	}
}
