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
	public static final int CALLED = 3;
	public static final int PASSED = 4;


	private JPanel contentPane;

	private JPanel buttonPanel;
	private JButton passButton;
	private JButton betButton;
	private JButton checkButton;
	private JButton callButton;

	private JPanel handDisplayPanel;
	private CardRadioButton cardButton1;
	private CardRadioButton cardButton2;
	private CardRadioButton cardButton3;
	private CardRadioButton cardButton4;
	private CardRadioButton cardButton5;
	private CardRadioButton cardButton6;

	private JPanel displayPanel;
	private JLabel potLabel;
	private JLabel betLabel;
	private JLabel gameBetLabel;
	private JTextField currPot;
	private JTextField currBet;
	private JTextField gameCurrBet;


	private Player player;
	private Phases phase;
	private int numPicks;
	private int numSelected;
	private int lastAction;
	private CardButtonAction[] actions = new CardButtonAction[6];
	private ArrayList<Card> selectedCards = new ArrayList<>();
	private CardRadioButton[] cardButtons = new CardRadioButton[6];

	public enum Phases { wait, bet, pass, determineWinner }

	public PlayerGUI(Player player) {
		this.player = player;
		this.phase = Phases.wait;
		this.lastAction = NO_ACTION;

		setTitle("Player " + player.getPlayerID() + "'s Hand");
		setContentPane(contentPane);

		this.init();
		this.reset();
		setCardButtonsEnabled(false);
		setCheckButtonEnabled(false);
		setPassButtonEnabled(false);
		setBetButtonsEnabled(false);
		setCallButtonEnabled(false);
		this.phase = Phases.wait;
	}

	private void init() {
		cardButtons[0] = cardButton1;
		cardButtons[1] = cardButton2;
		cardButtons[2] = cardButton3;
		cardButtons[3] = cardButton4;
		cardButtons[4] = cardButton5;
		cardButtons[5] = cardButton6;

		for (int i = 0; i < cardButtons.length; i++) {
			actions[i] = new CardButtonAction(cardButtons[i]);
			cardButtons[i].setAction(actions[i]);
		}

		passButton.addActionListener(this::onPassPress);
		betButton.addActionListener(this::onBetPress);
		checkButton.addActionListener(this::onCheckPress);
		callButton.addActionListener(this::onCallPress);
	}

	public void reset() {
		this.numPicks = 3;
		this.numSelected = 0;
		this.selectedCards.clear();
		this.lastAction = NO_ACTION;
		this.phase = Phases.wait;
	}

	public void resetHandDisplay() {
		Hand h = player.getHand();

		for (int i = 0; i < cardButtons.length; i++) {
			cardButtons[i].setCard(h.getCard(i));
		}

		int i = 0;
		for (CardRadioButton b : cardButtons) {
			b.setCard(h.getCard(i));
			b.setToolTipText(b.getCard().toString());

			i++;
		}
	}

	private void onPassPress(ActionEvent e) {
		if (this.phase != Phases.pass) { return; }
		else if (selectedCards.size() != this.numPicks) {
			JOptionPane.showMessageDialog(this, "You must select " + numPicks + " cards to pass!", "Wrong number of selected cards!", JOptionPane.ERROR_MESSAGE);
		} else {
			passButton.setEnabled(false);
			numSelected = 0;
			lastAction = PASSED;
		}

	}
	private void onCheckPress(ActionEvent e) {
		if (this.phase != Phases.bet) { return; }
		setBetButtonsEnabled(false);
		setCheckButtonEnabled(false);
		lastAction = CHECKED;
	}
	private void onBetPress(ActionEvent e) {
		if (this.phase != Phases.bet) { return; }
		if(!validateBetValue() || getBetValue() <= 0) {
			currBet.setText("Invalid Bet amount!");
			currBet.setSelectionStart(0);
			currBet.setSelectionEnd(currBet.getText().length());
		} else {
			setBetButtonsEnabled(false);
			setCheckButtonEnabled(false);
			lastAction = RAISED;
		}
	}
	private void onCallPress(ActionEvent e) {
		if (this.phase != Phases.bet) { return; }
		lastAction = CALLED;
	}

	private void setBetButtonsEnabled(boolean enabled) {
		betButton.setEnabled(enabled);
		currBet.setEnabled(enabled);
	}
	private void setCardButtonsEnabled(boolean enabled) {
		for (CardRadioButton b : cardButtons) {
			b.setEnabled(enabled);
		}
	}
	public void setCheckButtonEnabled(boolean enabled) { checkButton.setEnabled(enabled); }
	public void setCallButtonEnabled(boolean enabled) { callButton.setEnabled(enabled); }
	public void setPassButtonEnabled(boolean enabled) { passButton.setEnabled(enabled); }

	public void nextPhase() {
		if(numPicks == 0) {
			phase = Phases.determineWinner;
			return;
		}
		switch(this.phase) {
			case wait: // advance to bet phase
				phase = Phases.bet;
				setPassButtonEnabled(false);
				setBetButtonsEnabled(true);
				setCallButtonEnabled(true);
				setCheckButtonEnabled(true);
				this.selectedCards.clear();
				break;
			case bet: // advance to pass phase
				phase = Phases.pass;
				setBetButtonsEnabled(false);
				setCallButtonEnabled(false);
				setCheckButtonEnabled(false);
				setPassButtonEnabled(false); // prevents users from passing no cards
				setCardButtonsEnabled(true);
				break;
			case pass: // advance to wait phase
				phase = Phases.wait;
				setPassButtonEnabled(false);
				setCardButtonsEnabled(false);
				setBetButtonsEnabled(false);
				setCallButtonEnabled(false);
				setCheckButtonEnabled(false);
				numPicks--;
				lastAction = NO_ACTION;
				break;
		}
	}

	public void deselect() {
		for (int i = 0; i < cardButtons.length; i++) {
			CardRadioButton b = cardButtons[i];
			Card c = b.getCard();
			if (b.isSelected()) { b.setSelected(false); }
		}
		this.selectedCards.clear();
	}

	public void addToPot(int val) { setPotValue(getPotValue() + val);}
	public void setPotValue(int val) { this.currPot.setText(String.valueOf(val)); }
	public void setGameBetValue(int val) { this.gameCurrBet.setText(String.valueOf(val)); }

	public int getPotValue() { return Integer.valueOf(this.currPot.getText()); }
	public int getBetValue() { return Integer.valueOf(this.currBet.getText()); }
	public int getGameBetValue() { return Integer.valueOf(this.gameCurrBet.getText()); }
	public int getAction() { return lastAction; }
	public Phases getPhase(Phases phase) { return phase; }

	public boolean validateBetValue() {
		boolean ret = true;
		String value = this.currBet.getText();
		for (Character c : value.toCharArray()) {
			ret = Character.isDigit(c);
			if (!ret) {
				break;
			}
		}
		return ret;
	}

	public Card[] getSelected() {
		if(lastAction != PASSED) { return null; }

		Object[] old = selectedCards.toArray();
		Card[] ret = new Card[selectedCards.size()];
		for (int i = 0; i < selectedCards.size(); i++) {
			ret[i] = (Card) old[i];
		}
		return ret;
	}

	private void createUIComponents() {
		cardButton1 = new CardRadioButton(null);
		cardButton2 = new CardRadioButton(null);
		cardButton3 = new CardRadioButton(null);
		cardButton4 = new CardRadioButton(null);
		cardButton5 = new CardRadioButton(null);
		cardButton6 = new CardRadioButton(null);
	}

	class CardButtonAction extends AbstractAction {
		CardRadioButton button;
		String tooltip;

		public CardButtonAction(CardRadioButton button) {
			this.button = button;

			Card c = this.button.getCard();
			this.tooltip = (c != null) ? c.toString() : "NULL";

			this.putValue(SHORT_DESCRIPTION, this.tooltip);
			this.putValue(SELECTED_KEY, false);
		}

		void updateToolTip(String newToolTip) {
			this.tooltip = newToolTip;
			this.putValue(SHORT_DESCRIPTION, tooltip);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof CardRadioButton) {
				CardRadioButton button = (CardRadioButton) e.getSource();
				if(button.isSelected()) {
					selectedCards.add(button.getCard());
					numSelected++;
				} else {
					selectedCards.remove(button.getCard());
					numSelected--;
				}

				if(numSelected == numPicks) {
					setPassButtonEnabled(true);
				} else {
					setPassButtonEnabled(false);
				}
			}
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
		gui.resetHandDisplay();
		gui.pack();
		gui.setVisible(true);
		for (int i = 0; i < 3; i++) {
			gui.nextPhase();
			Thread.sleep(5000);
		}
		Thread.sleep(3000);
		System.exit(0);
	}
}
