package ach.main.gui;

import ach.stack.ArrayStack;

import javax.swing.*;
import javax.swing.text.BadLocationException;

/**
 * Created by hayesj3 on 2/1/2016.
 */
public class Calculator extends JFrame {

	private JPanel background;
	private JTextArea output;

	private JButton buttonQuit;
	private JButton buttonClear;
	private JButton buttonBack;
	private JButton buttonDiv;
	private JButton buttonMult;
	private JButton buttonSub;
	private JButton buttonAdd;
	private JButton buttonEqual;
	private JButton buttonOpenParan;
	private JButton buttonCloseParan;
	private JButton button0;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	private JButton button9;

	public enum EnumCharacters { number, operator, openingParan, closingParan }

	public Calculator() throws BadLocationException {
		buttonQuit.addActionListener(e -> quit());
		buttonClear.addActionListener(e -> output.setText(""));
		buttonBack.addActionListener(e -> output.setText(output.getText().substring(0, output.getText().length()-1)));

		buttonDiv.addActionListener(e -> output.append("/"));
		buttonMult.addActionListener(e -> output.append("*"));
		buttonSub.addActionListener(e -> output.append("-"));
		buttonAdd.addActionListener(e -> output.append("+"));
		buttonEqual.addActionListener(e -> parse(toPostFix(output.getText())));

		buttonOpenParan.addActionListener(e -> output.append("("));
		buttonCloseParan.addActionListener(e -> output.append(")"));

		button0.addActionListener(e -> output.append("0"));
		button1.addActionListener(e -> output.append("1"));
		button2.addActionListener(e -> output.append("2"));
		button3.addActionListener(e -> output.append("3"));
		button4.addActionListener(e -> output.append("4"));
		button5.addActionListener(e -> output.append("5"));
		button6.addActionListener(e -> output.append("6"));
		button7.addActionListener(e -> output.append("7"));
		button8.addActionListener(e -> output.append("8"));
		button9.addActionListener(e -> output.append("9"));

		this.pack();
		this.setVisible(true);
	}

	private String toPostFix(String infix) {
		StringBuilder ret = new StringBuilder();
		ArrayStack<Character> operatorStack = new ArrayStack<>();
		int pos = 0;
		EnumCharacters state;
		Operator opN = null;
		Operator opS = null;
		while (pos < infix.length()) {
			Character next = new Character(infix.charAt(pos));
			if (Character.isDigit(next)) {
				state = EnumCharacters.number;
			} else if (next.charValue() == '+' || next.charValue() == '-') {
				state = EnumCharacters.operator;
				opN = new Operator((next.charValue() == '+' ? '+' : '-'), Operator.PrecedenceStates.AddSub);
			} else if (next.charValue() == '*' || next.charValue() == '/') {
				state = EnumCharacters.operator;
				opN = new Operator((next.charValue() == '*' ? '+' : '/'), Operator.PrecedenceStates.MultDiv);
			} else if (next.charValue() == '(') {
				state = EnumCharacters.openingParan;
			} else if (next.charValue() == '(') {
				state = EnumCharacters.closingParan;
			} else {
				state = null;
			}
			switch (state)
			{
				case number:
					ret.append(next);
					break;
				case operator :
					char ch = operatorStack.peek();
					opS = new Operator(ch, (ch == '+' || ch == '-' ? Operator.PrecedenceStates.AddSub : Operator.PrecedenceStates.MultDiv));
					while (!operatorStack.isEmpty() && (opN.getState().ordinal() <= opS.getState().ordinal())) {
						ret.append(ch);
						operatorStack.pop();
					}
				operatorStack.push(next);
				break;
				case openingParan:
					operatorStack.push(next);
					break;
				case closingParan: // the stack is not empty
					Character topOperator = operatorStack.pop();
					while (topOperator.charValue() != '(')
					{
						ret.append(topOperator);
						topOperator = operatorStack.pop();
					}
					break;
				default:
					break; // ignore unexpected characters
			}
		}

		while (!operatorStack.isEmpty())
		{
			Character topOperator = operatorStack.pop();
			ret.append(topOperator);
		}
		return ret.toString();
	}

	private int parse(String postFix) {
		int temp = 0;
		int result = 0;
		int pos = 0;
		Character operand1, operand2, operator;

		ArrayStack<Character> stack = new ArrayStack<>();
		while (pos < postFix.length()) {
			while (!isOperator(postFix.charAt(pos))) {
				stack.push(postFix.charAt(pos));
				pos++;
			}
			operand2 = stack.pop();
			operand1 = stack.pop();
			operator = postFix.charAt(pos++);
			switch (operator) {
				case '+':
					temp = Integer.valueOf(operand1) + Integer.valueOf(operand2);
					break;
				case '-':
					temp = Integer.valueOf(operand1) - Integer.valueOf(operand2);
					break;
				case '*':
					temp = Integer.valueOf(operand1) * Integer.valueOf(operand2);
					break;
				case '/':
					temp = Integer.valueOf(operand1) / Integer.valueOf(operand2);
					break;
			}
			while (pos < postFix.length() && isOperator(postFix.charAt(pos))) {
				operator = postFix.charAt(pos++);
				switch (operator) {
					case '+':
						temp += stack.pop();
						break;
					case '-':
						temp -= stack.pop();
						break;
					case '*':
						temp *= stack.pop();
						break;
					case '/':
						temp /= stack.pop();
						break;
				}
			}
		}


		return temp;
	}

	private boolean isOperator(char ch) {
		return (ch == '+' || ch == '-' || ch == '*' ||ch == '/');
	}
	private void quit() {
		this.dispose();

	}
}
class Operator {

	enum PrecedenceStates { AddSub, MultDiv }

	char theOp;
	PrecedenceStates state;

	public Operator(char theOp, PrecedenceStates state) {
		this.theOp = theOp;
		this.state = state;
	}

	public char getOperator() { return this.theOp; }
	public PrecedenceStates getState() { return this.state;	}
}