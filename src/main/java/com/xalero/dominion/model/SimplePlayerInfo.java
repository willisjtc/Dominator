package com.xalero.dominion.model;

import java.util.Random;

import com.xalero.dominion.ai.IDominionAI;


public class SimplePlayerInfo {

	private long identifier;
	private String displayName;
    private PlayerType playerType;
    private IDominionAI ai;
    
    private static Random rand = new Random();

    public SimplePlayerInfo(long identifier, String displayName, PlayerType playerType) {
    	this(displayName, identifier, playerType, null);
    }
    
    public SimplePlayerInfo(String displayName, long identifier, PlayerType playerType, IDominionAI ai) {
    	this.identifier = identifier;
    	this.displayName = displayName;
        this.playerType = playerType;
        this.ai = ai;
    }

    /**
     * Gets the identifier associated with this user.
     * 
     * @return the sting identifier associated with this user.
     */
    public long getIdentifier() {
    	return identifier;
    }
    
    public void setIdentifier(long identifier) {
    	this.identifier = identifier;
    }
    
    /**
     * @param displayName The name that will be displayed in the game
     */
    public void setDisplayName(String displayName) {
    	this.displayName = displayName;
    }
    
    /**
     * @return the name associated with the user
     */
    public String getDisplayName() {
    	return displayName;
    }

    /**
     * @return A player type of HUMAN or COMPUTER
     */
    public PlayerType getPlayerType() {
        return playerType;
    }

    /**
     * Sets the player type of HUMAN or COMPUTER
     * 
     * @param playerType
     */
    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    /**
     * Retrieves the AI interface for this player.
     * 
     * @return null if a human player and a non-null object otherwise
     */
    public IDominionAI getAi() {
        return ai;
    }

    /**
     * Sets the ai for this player.
     * 
     * @param ai the IDominionAI implementation.
     */
    public void setAi(IDominionAI ai) {
        this.ai = ai;
    }
}
