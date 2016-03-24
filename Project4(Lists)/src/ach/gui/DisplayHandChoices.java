package ach.gui;

import ach.card.Card;
import ach.card.Hand;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplayHandChoices extends JDialog {
	private JPanel contentPane;
	private JButton buttonOK;
	private JPanel HandDisplay;
	private JRadioButton card1;
	private JRadioButton card2;
	private JRadioButton card3;
	private JRadioButton card4;
	private JRadioButton card5;
	private JRadioButton card6;

	private JRadioButton[] cardButtons;
	private ArrayList<Integer> cardSelection;
	private Hand hand;
	private int selected = 0;
	private int numPicks;

	public DisplayHandChoices(Hand hand, int numPicks) {
		setContentPane(contentPane);
		setModal(true);
		setTitle("Select " + numPicks + " Cards to Pass");
		getRootPane().setDefaultButton(buttonOK);
		this.cardButtons = new JRadioButton[6];
		this.cardSelection = new ArrayList<>();
		this.hand = hand;
		this.numPicks = numPicks;

		this.buttonOK.setEnabled(false);
		buttonOK.addActionListener(e -> onOK());
	}

	private void onOK() {
		for (int i = 0; i < 6; i++) {
			if (cardButtons[i].isSelected()) { cardSelection.add(i); }
		}

		dispose();
	}

	public Card[] getSelection() {
		return cardSelection.toArray(new Card[numPicks]);
	}
	public void setNumPicks(int numPicks) { this.numPicks = numPicks; }

	private void createUIComponents() {
		Card[] hand = this.hand.toArray();

		card1 = new JRadioButton(hand[0].toString());
		card2 = new JRadioButton(hand[1].toString());
		card3 = new JRadioButton(hand[2].toString());
		card4 = new JRadioButton(hand[3].toString());
		card5 = new JRadioButton(hand[4].toString());
		card6 = new JRadioButton(hand[5].toString());

		card1.addActionListener(e -> { if(card1.isSelected()) selected++; else selected--; } );
		card2.addActionListener(e -> { if(card2.isSelected()) selected++; else selected--; } );
		card3.addActionListener(e -> { if(card3.isSelected()) selected++; else selected--; } );
		card4.addActionListener(e -> { if(card4.isSelected()) selected++; else selected--; } );
		card5.addActionListener(e -> { if(card5.isSelected()) selected++; else selected--; } );
		card6.addActionListener(e -> { if(card6.isSelected()) selected++; else selected--; } );

		cardButtons[0] = card1;
		cardButtons[1] = card2;
		cardButtons[2] = card3;
		cardButtons[3] = card4;
		cardButtons[4] = card5;
		cardButtons[5] = card6;

	}

	@Override
	public void update(Graphics g) {
		super.update(g);
		if(selected == numPicks && !buttonOK.isEnabled()) {
			buttonOK.setEnabled(true);
		} else if(selected != numPicks && buttonOK.isEnabled()) {
			buttonOK.setEnabled(false);
		}
	}

	public static void main(String[] args) {
		Hand hand = new Hand();
		DisplayHandChoices dialog = new DisplayHandChoices(hand, 3);
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}
