package com.xalero.dominion.cards;

import com.xalero.dominion.cards.treasure.TreasureBase;
import com.xalero.dominion.cards.victory.VictoryBase;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;

public abstract class Card implements CardBase, VictoryBase, TreasureBase {
	
	protected int id;
	private final SimpleBooleanProperty selected = new SimpleBooleanProperty(false);
	protected Image cardImage;
	
	public boolean getSelected() {
        return selected.get();
	}

	public void setSelected(boolean selected) {
		this.selected.set(selected);
	}	
	
	public abstract int getCost();

	@Override
	public int getPoints() throws NoSuchMethodException {
		if (!this.isVictory()) {
			throw new NoSuchMethodException();
		}
		return this.getPoints();
	}

	@Override
	public int getValue() throws NoSuchMethodException {
		if (!this.isVictory()) {
			throw new NoSuchMethodException();
		}
		return this.getValue();
	}

	@Override
	public boolean isAction() {
		return false;
	}

	@Override
	public boolean isAttack() {
		return false;
	}

	@Override
	public boolean isReaction() {
		return false;
	}

	@Override
	public boolean isTreasure() {
		return false;
	}

	@Override
	public boolean isVictory() {
		return false;
	}
	
	@Override
	public boolean isInteractive() {
		return false;
	}
	
	public Image getCardImage() {
		return cardImage;
	}
	
	public void setCardImage(Image cardImage) {
		this.cardImage = cardImage;
	}

	public SimpleBooleanProperty selectedProperty() {
        return selected;
	}

}
