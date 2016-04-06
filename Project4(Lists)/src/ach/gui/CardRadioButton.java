package ach.gui;

import ach.card.Card;

import javax.swing.*;

/**
 * Created by hayesj3 on 4/6/2016.
 */
public class CardRadioButton extends JRadioButton {

	private Card card;

	public CardRadioButton(Card card) {
		this.card = card;
		if(this.card != null) { this.setIcons(); }
	}

	private void setIcons() {
		ImageIcon defaultIcon = new ImageIcon("gui_resources/default/" + card.toFileName() + ".png");
		ImageIcon selectedIcon = new ImageIcon("gui_resources/selected/" + card.toFileName() + ".png");
		//ImageIcon disabledIcon = new ImageIcon("gui_resources/disabled/" + card.toFileName() + ".png"); // too obscuring of the card denomination;

		setIcon(defaultIcon);
		setSelectedIcon(selectedIcon);
		//setDisabledIcon(disabledIcon); // too obscuring of the card denomination;
	}

	public Card getCard() { return card; }

	public void setCard(Card card) {
		this.card = card;

		if(this.card != null) {
			this.setIcons();
			Action a = this.getAction();
			String newTip = this.getCard().toString();
			if(a != null && a instanceof PlayerGUI.CardButtonAction && a.getValue(Action.SHORT_DESCRIPTION) != newTip) {
				PlayerGUI.CardButtonAction cba = (PlayerGUI.CardButtonAction) a;
				cba.updateToolTip(newTip);
			}
		}
	}
}
