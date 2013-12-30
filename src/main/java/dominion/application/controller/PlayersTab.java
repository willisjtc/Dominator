package dominion.application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import dominion.application.IObserver;
import dominion.application.model.PlayerType;
import dominion.application.model.SingleGameSettings;
import dominion.application.RemoveRowHandler;
import javafx.scene.control.Button;

public class PlayersTab extends Tab implements IObserver {

    @FXML
    private AnchorPane content;
    @FXML
    private VBox playerTable;
    @FXML
    private Button addHuman;
    @FXML 
    private Button addComputer;
    
    private SingleGameSettings gameSettings;

    public PlayersTab() {
    }

    public void initializeController(SingleGameSettings gameSettings) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        this.setText("Players");
        this.setContent(content);
        
        this.gameSettings.addPlayer(PlayerType.HUMAN);
        this.gameSettings.addPlayer(PlayerType.COMPUTER);
        this.gameSettings.addPlayer(PlayerType.COMPUTER);
        this.gameSettings.addPlayer(PlayerType.COMPUTER);
    }
    
    @FXML
    public void addHumanPlayer() {
        gameSettings.addPlayer(PlayerType.HUMAN);
    }
    
    @FXML
    public void addComputerPlayer() {
        gameSettings.addPlayer(PlayerType.COMPUTER);
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
        for (final PlayerType playerType : gameSettings.getPlayerTypes()) {
            if (playerType.equals(PlayerType.COMPUTER)) {
                computerCount++;
            }
            if (playerTable != null) {               
                playerTable.getChildren().add(new PlayerEditableRow(playerType, new RemoveRowHandler() {
                    @Override
                    public void removeRow() {
                        gameSettings.removePlayer(playerType);
                    }
                }));
            }
        }
        if (gameSettings.getPlayerTypes().contains(PlayerType.HUMAN)) {
            addHuman.disableProperty().set(true);
        } else {
            addHuman.disableProperty().set(false);
        }
        
        if (gameSettings.getPlayerTypes().size() == 4 && (computerCount == 3 || computerCount == 4)) {
            addComputer.disableProperty().set(true);
        } else {
            addComputer.disableProperty().set(false);
        }
    }
}
