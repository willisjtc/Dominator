package dominion.application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import dominion.application.IObserver;
import dominion.application.RemoveRowHandler;
import dominion.application.manager.UserManager;
import dominion.application.model.PlayerType;
import dominion.application.model.SimplePlayerInfo;
import dominion.application.model.GameSettings;
import dominion.game.user.User;

public class PlayersTab extends Tab implements IObserver {

    @FXML
    private AnchorPane content;
    @FXML
    private VBox playerTable;
    @FXML
    private Button addHuman;
    @FXML
    private Button addComputer;

    private GameSettings gameSettings;

    public PlayersTab() {
    }

    public void initializeController(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
        this.gameSettings.registerObserver(this);

        initView();
    }

    private void initView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("players_tab.fxml"));
        fxmlLoader.setRoot(content);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        this.setText("Players");
        this.setContent(content);

        // TODO: add default players
        SimplePlayerInfo humanPlayer = new SimplePlayerInfo(UserManager.instance.getCurrentUser(), PlayerType.HUMAN, null);
        SimplePlayerInfo computer1 = new SimplePlayerInfo(null, PlayerType.COMPUTER, null /* Insert some AI */);
        SimplePlayerInfo computer2 = new SimplePlayerInfo(null, PlayerType.COMPUTER, null /* Insert some AI */);
        SimplePlayerInfo computer3 = new SimplePlayerInfo(null, PlayerType.COMPUTER, null /* Insert some AI */);
        this.gameSettings.addPlayer(humanPlayer);
        this.gameSettings.addPlayer(computer1);
        this.gameSettings.addPlayer(computer2);
        this.gameSettings.addPlayer(computer3);
    }

    @FXML
    public void addHumanPlayer() {
        // TODO: Clarify SimplePlayerInfo
        if (gameSettings.humanPlayerExists()) {
            return;
        }
        SimplePlayerInfo playerInfo = new SimplePlayerInfo(new User(), PlayerType.HUMAN, null);
        gameSettings.addPlayer(playerInfo);
    }

    @FXML
    public void addComputerPlayer() {
        // TODO: Clarify SimplePlayerInfo
        if (gameSettings.getPlayerInfos().size() >= 4) {
            return;
        }
        SimplePlayerInfo computer = new SimplePlayerInfo(null, PlayerType.COMPUTER, null);
        gameSettings.addPlayer(computer);
    }

    @Override
    public void update() {
        updatePlayerTable();
    }

    public void updatePlayerTable() {
        if (playerTable != null) {
            playerTable.getChildren().clear();
        }

        int computerCount = 0;
        for (final SimplePlayerInfo playerInfo : gameSettings.getPlayerInfos()) {
            if (playerInfo.getPlayerType().equals(PlayerType.COMPUTER)) {
                computerCount++;
            }
            if (playerTable != null) {
                playerTable.getChildren().add(new PlayerEditableRow(playerInfo, new RemoveRowHandler() {
                    @Override
                    public void removeRow() {
                        if (gameSettings.getPlayerInfos().size() < 3) {
                            return;
                        }
                        gameSettings.removePlayer(playerInfo);
                    }
                }));
            }
        }
        if (gameSettings.humanPlayerExists()) {
            addHuman.disableProperty().set(true);
        } else {
            addHuman.disableProperty().set(false);
        }

        if (gameSettings.getPlayerInfos().size() == 4 && (computerCount == 3 || computerCount == 4)) {
            addComputer.disableProperty().set(true);
        } else {
            addComputer.disableProperty().set(false);
        }
    }
}
