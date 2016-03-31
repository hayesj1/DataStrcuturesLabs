package ach.gui;

import ach.card.Card;
import ach.card.Faces;
import ach.card.Hand;
import ach.card.Suits;
import ach.game.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Created by hayesj3 on 3/29/2016.
 */
public class PlayerGUI extends JFrame {
	public static final int NO_ACTION = 0;
	public static final int CHECKED = 1;
	public static final int RAISED = 2;

	private JPanel contentPane;
	private JButton passButton;
	private JButton betButton;
	private JButton raiseButton;
	private JButton checkButton;

	private JPanel handDisplayPanel;
	private JRadioButton cardButton1;
	private JRadioButton cardButton2;
	private JRadioButton cardButton3;
	private JRadioButton cardButton4;
	private JRadioButton cardButton5;
	private JRadioButton cardButton6;

	private JLabel cardLabel1;
	private JLabel cardLabel2;
	private JLabel cardLabel3;
	private JLabel cardLabel4;
	private JLabel cardLabel5;
	private JLabel cardLabel6;

	private JLabel potLabel;
	private JLabel betLabel;
	private JTextField currPot;
	private JTextField currBet;

	private Player player;
	private Phases phase = Phases.wait;
	private int numPicks;
	private int numSelected;
	private ArrayList<Card> selectedCards;
	private int lastAction = NO_ACTION;
	private JLabel[] cardLabels;
	private JRadioButton[] cardButtons;

	public enum Phases { wait, bet, pass, determineWinner }

	public PlayerGUI(Player player) {
		this.player = player;
		this.cardLabels = new JLabel[6];
		this.cardButtons = new JRadioButton[6];
		this.selectedCards = new ArrayList<>();

		setTitle("Player " + player.getPlayerID() + "'s Hand");
		setContentPane(contentPane);
		this.init();
		this.reset();
		this.resetHandDisplay();

	}

	private void init() {
		cardButton1.addActionListener(this::onCardPress);

		cardButtons[0] = cardButton1;
		cardButtons[1] = cardButton2;
		cardButtons[2] = cardButton3;
		cardButtons[3] = cardButton4;
		cardButtons[4] = cardButton5;
		cardButtons[5] = cardButton6;

		cardLabels[0] = cardLabel1;
		cardLabels[1] = cardLabel2;
		cardLabels[2] = cardLabel3;
		cardLabels[3] = cardLabel4;
		cardLabels[4] = cardLabel5;
		cardLabels[5] = cardLabel6;
	}

	public void reset() {
		this.numPicks = 3;
		this.numSelected = 0;
	}

	private void resetHandDisplay() {
		Hand h = player.getHand();
		int i = 0;
		for (JRadioButton l : cardButtons) {
			System.out.println(i);
			System.out.println(h.getCard(i));
			ImageIcon defaultIcon = new ImageIcon("gui_resources/default/" + h.getCard(i).toFileName() + ".png");
			//ImageIcon rolloverIcon = new ImageIcon("gui_resources/rollover/" + h.getCard(i).toFileName() + ".png");
			ImageIcon selectedIcon = new ImageIcon("gui_resources/selected/" + h.getCard(i).toFileName() + ".png");
			//ImageIcon disabledIcon = new ImageIcon("gui_resources/disabled/" + h.getCard(i).toFileName() + ".png");

			l.setIcon(defaultIcon);
			//l.setRolloverIcon(rolloverIcon);
			l.setSelectedIcon(selectedIcon);
			//l.setDisabledIcon(disabledIcon);
			i++;
		}
	}

	private void onCardPress(ActionEvent e) {
		if (this.phase != Phases.pass) { return; }
		if (e.getSource() instanceof JRadioButton) {
			JRadioButton button = (JRadioButton) e.getSource();
			if(!button.isSelected()) {
				button.setSelected(numSelected < numPicks);
			} else {
				int pos;
				for (pos = 0; pos < cardButtons.length && !(cardButtons[pos].equals(button)); pos++);
				selectedCards.remove(this.player.getHand().getCard(pos));
			}

			if(button.isSelected()) {
				int pos;
				for (pos = 0; pos < cardButtons.length && !(cardButtons[pos].equals(button)); pos++);
				selectedCards.add(this.player.getHand().getCard(pos));
				numSelected++;
			} else {
				passButton.setEnabled(true);
			}
		}
	}

	private void onPassPress() {
		if (this.phase != Phases.pass) { return; }
		passButton.setEnabled(false);
		this.setVisible(false);
	}
	private void onCheckPress() {
		if (this.phase != Phases.bet) { return; }
		setBetButtonsEnabled(false);
		setCheckButtonEnabled(false);
		lastAction = CHECKED;
	}
	private void onBetPress() {
		if (this.phase != Phases.bet) { return; }
		if(getBetValue() <= 0) {
			currBet.setText("Invalid Bet amount!");
			currBet.setSelectionStart(0);
			currBet.setSelectionEnd(currBet.getText().length());
		} else {
			setBetButtonsEnabled(false);
			setCheckButtonEnabled(false);
		}
		lastAction = RAISED;
	}
	private void setBetButtonsEnabled(boolean enabled) {
		betButton.setEnabled(enabled);
		raiseButton.setEnabled(enabled);
		currBet.setEnabled(enabled);
	}
	public void setCheckButtonEnabled(boolean enabled) { checkButton.setEnabled(enabled); }
	public int getAction() { return lastAction; }

	public void nextPhase() {
		if(numPicks == 0) {
			phase = Phases.determineWinner;
			return;
		}
		switch(this.phase) {
			case wait:
				phase = Phases.bet;
				passButton.setEnabled(false);
				setBetButtonsEnabled(true);
				setCheckButtonEnabled(true);
				break;
			case bet:
				phase = Phases.pass;
				setBetButtonsEnabled(false);
				checkButton.setEnabled(false);
				numPicks--;
				passButton.setEnabled(true);
				break;
			case pass:
				phase = Phases.wait;
				passButton.setEnabled(false);
				setBetButtonsEnabled(false);
				checkButton.setEnabled(false);
				break;
		}
	}

	private void deselect() {
		for (int i = 0; i < cardButtons.length; i++) {
			if (cardButtons[i].isSelected()) { cardButtons[i].setSelected(false); }
		}
	}

	public void setPotValue(int val) {
		this.currPot.setText("$" + val);
	}
	public int getBetValue() { return Integer.valueOf(this.currBet.getText()); }
	public Phases getPhase(Phases phase) { return phase; }
	public Card[] getSelected() {
		if (selectedCards.size() != numPicks) {
			return null;
		} else {
			deselect();
			return selectedCards.toArray(new Card[numPicks]);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Player p = new Player("Bob");
		Card[] cards = new Card[6];

		cards[0] = new Card(Suits.CLUB, Faces.ACE);
		cards[1] = new Card(Suits.DIAMOND, Faces.ACE);
		cards[2] = new Card(Suits.HEART, Faces.ACE);
		cards[3] = new Card(Suits.SPADE, Faces.ACE);
		cards[4] = new Card(Suits.HEART, Faces.QUEEN);
		cards[5] = new Card(Suits.SPADE, Faces.KING);

		for (int i = 0; i < cards.length; i++) {
			p.addCardToHand(cards[i]);
		}

		PlayerGUI gui = new PlayerGUI(p);
		gui.pack();
		gui.setVisible(true);
		for (int i = 0; i < 3; i++) {
			gui.nextPhase();
			Thread.sleep(7500);
		}
		Thread.sleep(3000);
		System.exit(0);
	}
}
