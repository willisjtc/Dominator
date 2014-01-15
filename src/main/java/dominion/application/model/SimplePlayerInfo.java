package dominion.application.model;

import dominion.application.manager.UserManager;
import dominion.game.ai.IDominionAI;
import dominion.game.user.User;

public class SimplePlayerInfo {

    private User user;
    private PlayerType playerType;
    private IDominionAI ai;

    public SimplePlayerInfo() {
        this.user = null;
        this.playerType = null;
        this.ai = null;
    }

    public SimplePlayerInfo(User user, PlayerType playerType, IDominionAI ai) {
        this.user = user == null ? new User() : user;
        this.playerType = playerType;
        this.ai = ai;
        
        if (playerType.equals(PlayerType.COMPUTER)) {
            this.user.setUsername(UserManager.instance.getRandomName());
        }
    }

    /**
     * @return The user associated to the player
     */
    public User getUser() {
        return user;
    }

    /**
     * @return the name associated with the user
     */
    public String getName() {
        return user.getName();
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public IDominionAI getAi() {
        return ai;
    }

    public void setAi(IDominionAI ai) {
        this.ai = ai;
    }
}
