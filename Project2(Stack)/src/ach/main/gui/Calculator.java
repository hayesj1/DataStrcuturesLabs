package ach.main.gui;

import ach.stack.ArrayStack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

	public Calculator() {
		this.setTitle("Optimus Calculator");
		this.setPreferredSize(new Dimension(300, 300));
		this.setSize(300, 300);

		KeyAdapter kl = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
					case KeyEvent.VK_0:
					case KeyEvent.VK_1:
					case KeyEvent.VK_2:
					case KeyEvent.VK_3:
					case KeyEvent.VK_4:
					case KeyEvent.VK_5:
					case KeyEvent.VK_6:
					case KeyEvent.VK_7:
					case KeyEvent.VK_8:
					case KeyEvent.VK_9:
					case KeyEvent.VK_LEFT_PARENTHESIS:
					case KeyEvent.VK_RIGHT_PARENTHESIS:
					case KeyEvent.VK_MINUS:
					case KeyEvent.VK_ASTERISK:
					case KeyEvent.VK_SLASH:
						handleChar(String.valueOf(e.getKeyChar()));
						break;
					case KeyEvent.VK_EQUALS:
						if(e.isShiftDown()) {
							handleChar("+");
						} else {
							handleEquals();
						}
						break;
					case KeyEvent.VK_Q:
						handleQuit();
						break;
					case KeyEvent.VK_C:
						handleClear();
						break;
					case KeyEvent.VK_BACK_SPACE:
						handleBackspace();
						break;
					default:
						break;
				}
			}

		};

		addActionListners();
		output.addKeyListener(kl);
		this.add(background);

		this.pack();
	}

	private String toPostFix(String infix) {
		StringBuilder ret = new StringBuilder();
		ArrayStack<Character> operatorStack = new ArrayStack<>();
		int pos = 0;
		EnumCharacters state;
		Operator opN = null;
		Operator opS = null;
		while (pos < infix.length()) {
			Character next = new Character(infix.charAt(pos++));
			if (Character.isDigit(next)) {
				state = EnumCharacters.number;
			} else if (next.charValue() == '+' || next.charValue() == '-') {
				state = EnumCharacters.operator;
				opN = new Operator((next.charValue() == '+' ? '+' : '-'), Operator.PrecedenceStates.AddSub);
			} else if (next.charValue() == '*' || next.charValue() == '/') {
				state = EnumCharacters.operator;
				opN = new Operator((next.charValue() == '*' ? '*' : '/'), Operator.PrecedenceStates.MultDiv);
			} else if (next.charValue() == '(') {
				state = EnumCharacters.openingParan;
			} else if (next.charValue() == ')') {
				state = EnumCharacters.closingParan;
			} else {
				state = null;
			}
			switch (state)
			{
				case number:
					ret.append(next.charValue());
					break;
				case operator :
					if(!operatorStack.isEmpty()) {
						char ch = operatorStack.peek();
						opS = new Operator(ch, (ch == '+' || ch == '-' ? Operator.PrecedenceStates.AddSub : Operator.PrecedenceStates.MultDiv));
						while (!operatorStack.isEmpty() && (opN.getState().ordinal() <= opS.getState().ordinal())) {
							if(ch == '(') { break; }
							ret.append(ch);
							ch = operatorStack.pop();
							opS = new Operator(ch, (ch == '+' || ch == '-' ? Operator.PrecedenceStates.AddSub : Operator.PrecedenceStates.MultDiv));
						}
					}
					operatorStack.push(next);
					break;
				case openingParan:
					operatorStack.push(next);
					break;
				case closingParan:
					Character topOperator = operatorStack.pop();
					while (!operatorStack.isEmpty() && topOperator.charValue() != '(')
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
		int result = 0;
		int pos = 0;
		int operand1, operand2;
		char ch;

		ArrayStack<Integer> stack = new ArrayStack<>();
		while (pos < postFix.length()) {
			ch = postFix.charAt(pos);
			while (Character.isDigit(ch)) {
				stack.push(Character.getNumericValue(ch));
				ch = postFix.charAt(++pos);
			}
			operand2 = stack.pop();
			operand1 = stack.pop();

			switch (ch) {
				case '+':
					result = operand1 + operand2;
					break;
				case '-':
					result = operand1 - operand2;
					break;
				case '*':
					result = operand1 * operand2;
					break;
				case '/':
					result = operand1 / operand2;
					break;
			}
			stack.push(result);
			pos++;
		}


		return result;
	}

	private boolean isOperator(char ch) {
		return (ch == '+' || ch == '-' || ch == '*' ||ch == '/');
	}

	private void handleQuit() {
		quit();
	}
	private void handleClear() {
		output.setText("");
	}
	private void handleBackspace() {
		String str = output.getText().substring(0, output.getText().length()-1);
		output.setText(str);
	}
	private void handleEquals() {
		String postFix = toPostFix(output.getText());
		int result = parse(postFix);
		output.setText(String.valueOf(result));
	}
	private void handleChar(String ch) {
		output.append(ch);
	}

	private void addActionListners() {
		buttonQuit.addActionListener(e -> handleQuit());
		buttonClear.addActionListener(e -> handleClear());
		buttonBack.addActionListener(e -> handleBackspace());

		buttonEqual.addActionListener(e -> handleEquals());
		buttonDiv.addActionListener(e -> handleChar("/"));
		buttonMult.addActionListener(e -> handleChar("*"));
		buttonSub.addActionListener(e -> handleChar("-"));
		buttonAdd.addActionListener(e -> handleChar("+"));


		buttonOpenParan.addActionListener(e -> handleChar("("));
		buttonCloseParan.addActionListener(e -> handleChar(")"));

		button0.addActionListener(e -> handleChar("0"));
		button1.addActionListener(e -> handleChar("1"));
		button2.addActionListener(e -> handleChar("2"));
		button3.addActionListener(e -> handleChar("3"));
		button4.addActionListener(e -> handleChar("4"));
		button5.addActionListener(e -> handleChar("5"));
		button6.addActionListener(e -> handleChar("6"));
		button7.addActionListener(e -> handleChar("7"));
		button8.addActionListener(e -> handleChar("8"));
		button9.addActionListener(e -> handleChar("9"));
	}

	void quit() {
		this.dispose();
		System.out.println("Thanks for using the Optimus Calculator! Goodbye!");
		System.exit(0);
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