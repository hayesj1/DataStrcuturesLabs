package ach.notation;


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
