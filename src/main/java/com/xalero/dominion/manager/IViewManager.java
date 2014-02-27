package com.xalero.dominion.manager;

import javafx.scene.layout.AnchorPane;

public interface IViewManager {
	public void authenticated();
	public void startGame();
	public void exitGame();
	public void logout();
	public void exitApplication();
	public void setDisplay(AnchorPane pane);
	public void requestFocus();
}
