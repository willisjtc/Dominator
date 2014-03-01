package com.xalero.dominion.utils;

/**
 * The result object is a way for methods to convey information in 
 * addition to a boolean. For example, if a player can't play a card
 * the method can return a result object explaing whether the player
 * can't play a card due to insufficient money, the player doesn't have
 * the card, or it's not the player's turn.
 * 
 * @author jonathan
 */
public class Result {

	private boolean success;
	private String message;

	public Result(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	/**
	 * @return a boolean for whether a method was successful
	 * or not.
	 */
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return a String object conveying extra information
	 */
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
