package dominion.application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import dominion.application.IObserver;
import dominion.application.model.PlayerType;
import dominion.application.model.SingleGameSettings;
import dominion.game.RemoveRowHandler;

public class PlayersTab extends Tab implements IObserver {

    @FXML
    private AnchorPane content;
    @FXML
    private VBox playerTable;
    private SingleGameSettings gameSettings;

    public PlayersTab() {
    }

    public void initializeController(SingleGameSettings gameSettings) {
        this.gameSettings = gameSettings;
        this.gameSettings.registerObserver(this);

        this.gameSettings.addPlayer(PlayerType.HUMAN);
        this.gameSettings.addPlayer(PlayerType.COMPUTER);
        this.gameSettings.addPlayer(PlayerType.COMPUTER);
        this.gameSettings.addPlayer(PlayerType.COMPUTER);

        initView();
    }

    private void initView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("players_tab.fxml"));
        fxmlLoader.setRoot(content);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setText("Players");
        this.setContent(content);
    }

    @Override
    public void update() {
        updatePlayerTable();
    }

    public void updatePlayerTable() {
        if (playerTable != null) {
            playerTable.getChildren().clear();
        }
        for (final PlayerType playerType : gameSettings.getPlayerTypes()) {
            if (playerTable != null) {
                playerTable.getChildren().add(new PlayerEditableRow(playerType, new RemoveRowHandler() {
                    @Override
                    public void removeRow() {
                        gameSettings.removePlayer(playerType);
                    }
                }));
            }
        }
    }
}
