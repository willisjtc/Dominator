package com.xalero.dominion.controller.settings;

import com.xalero.dominion.IObserver;
import com.xalero.dominion.RemoveRowHandler;
import com.xalero.dominion.manager.UserManager;
import com.xalero.dominion.server.model.GameSettings;
import com.xalero.dominion.server.model.IdGenerator;
import com.xalero.dominion.server.model.PlayerType;
import com.xalero.dominion.server.model.SimplePlayerInfo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PlayersTab extends Tab implements IObserver {

    @FXML
    private AnchorPane content;
    @FXML
    private VBox playerTable;
    @FXML
    private Button addComputer;


    public PlayersTab() {
    }

    public void initializeController(GameSettings gameSettings) {
        GameSettings.INSTANCE.registerObserver(this);

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
        GameSettings.INSTANCE.setCurrentPlayerId(IdGenerator.getUniquePlayerId());
        System.out.println("players tab player id: " + GameSettings.INSTANCE.getCurrentPlayerId());
        SimplePlayerInfo humanPlayer = new SimplePlayerInfo(GameSettings.INSTANCE.getCurrentPlayerId(), UserManager.INSTANCE.getCurrentUser().getUsername(), PlayerType.HUMAN);
        SimplePlayerInfo computer1 = new SimplePlayerInfo(IdGenerator.getUniquePlayerId(), null, PlayerType.COMPUTER);
        SimplePlayerInfo computer2 = new SimplePlayerInfo(IdGenerator.getUniquePlayerId(), null, PlayerType.COMPUTER);
        SimplePlayerInfo computer3 = new SimplePlayerInfo(IdGenerator.getUniquePlayerId(), null, PlayerType.COMPUTER);
        GameSettings.INSTANCE.addPlayer(humanPlayer);
        GameSettings.INSTANCE.addPlayer(computer1);
        GameSettings.INSTANCE.addPlayer(computer2);
        GameSettings.INSTANCE.addPlayer(computer3); 
    }

    @FXML
    public void addComputerPlayer() {
        // TODO: Clarify SimplePlayerInfo
        if (GameSettings.INSTANCE.getPlayerInfos().size() >= 4) {
            return;
        }
        SimplePlayerInfo computer = new SimplePlayerInfo(0, null, PlayerType.COMPUTER);
        GameSettings.INSTANCE.addPlayer(computer);
    }

    @Override
    public void update(String event) {
        updatePlayerTable();
    }

    public void updatePlayerTable() {
        if (playerTable != null) {
            playerTable.getChildren().clear();
        }

        int computerCount = 0;
        for (final SimplePlayerInfo playerInfo : GameSettings.INSTANCE.getPlayerInfos()) {
            if (playerInfo.getPlayerType().equals(PlayerType.COMPUTER)) {
                computerCount++;
            } 
            if (playerTable != null) {
            	if (playerInfo.getPlayerType().equals(PlayerType.HUMAN)) {
                    playerTable.getChildren().add(new PlayerEditableRow(playerInfo).hideRemoveButton());
            	} else {
	                playerTable.getChildren().add(new PlayerEditableRow(playerInfo, new RemoveRowHandler() {
	                    @Override
	                    public void removeRow() {
	                        if (GameSettings.INSTANCE.getPlayerInfos().size() < 3) {
	                            return;
	                        }
	                        GameSettings.INSTANCE.removePlayer(playerInfo);
	                    }
	                }));
            	}
            }
        }

        if (GameSettings.INSTANCE.getPlayerInfos().size() == 4 && (computerCount == 3 || computerCount == 4)) {
            addComputer.disableProperty().set(true);
        } else {
            addComputer.disableProperty().set(false);
        }
    }
}
