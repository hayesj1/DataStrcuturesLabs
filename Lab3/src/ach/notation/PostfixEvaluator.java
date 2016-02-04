package ach.notation;


/**
 * Created by camasok on 2/4/2016.
 */
public class PostfixEvaluator
{
   static double sum=0.0;
   char operator = '';
   String expression = {}/* WHATEVER */;
 public static double eval(String exp)
 {
     expression=exp;

     for(int i = 0; i < expression.length(); i++)
     {
         operator = exp.charAt(i);

         switch (operator)
         {
             case operator = '^' ;
             case operator = '+' ;
             case operator = '-' ;
             case operator = '*' ;
             case operator = '/' ;
             case operator = '(' ;
             case operator = ')' ;
             case operator

         }
     }
 }
}
