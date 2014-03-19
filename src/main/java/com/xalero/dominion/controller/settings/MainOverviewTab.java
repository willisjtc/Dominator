package com.xalero.dominion.controller.settings;

import java.util.LinkedList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.xalero.dominion.IObserver;
import com.xalero.dominion.cards.Card;
import com.xalero.dominion.controller.terminal.TerminalController;
import com.xalero.dominion.server.model.DominionModel;
import com.xalero.dominion.server.model.GameSettings;
import com.xalero.dominion.server.model.SimplePlayerInfo;

public class MainOverviewTab extends Tab implements IObserver {

    @FXML
    public AnchorPane content;
    @FXML
    private VBox playerTable;
    @FXML
    private ImageView overviewImage1;
    @FXML
    private ImageView overviewImage2;
    @FXML
    private ImageView overviewImage3;
    @FXML
    private ImageView overviewImage4;
    @FXML
    private ImageView overviewImage5;
    @FXML
    private ImageView overviewImage6;
    @FXML
    private ImageView overviewImage7;
    @FXML
    private ImageView overviewImage8;
    @FXML
    private ImageView overviewImage9;
    @FXML
    private ImageView overviewImage10;
    private GameSettings gameSettings;
    private List<ImageView> overviewImages;

    public MainOverviewTab() {
    }

    public void initializeController(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
        this.gameSettings.registerObserver(this);

        initView();
    }

    private void initView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main_overview_tab.fxml"));
        fxmlLoader.setRoot(content);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        this.setText("Overview Game");
        this.setContent(content);

        initOverviewImages();
    }

    private void resetOverviewImages() {
        for (ImageView overviewImageView : overviewImages) {
            overviewImageView.setImage(null);
        }
        
        for (Card card : gameSettings.getSelectedCards()) {
            for (ImageView overviewImageView : overviewImages) {
                if (overviewImageView.getImage() == null) {
                    overviewImageView.setImage(card.getCardImage());
                    break;
                }
            }
        }
    }

    private void initOverviewImages() {
        overviewImages = new LinkedList<>();
        overviewImages.add(overviewImage1);
        overviewImages.add(overviewImage2);
        overviewImages.add(overviewImage3);
        overviewImages.add(overviewImage4);
        overviewImages.add(overviewImage5);
        overviewImages.add(overviewImage6);
        overviewImages.add(overviewImage7);
        overviewImages.add(overviewImage8);
        overviewImages.add(overviewImage9);
        overviewImages.add(overviewImage10);
    }
    
    @FXML
    private void playGame() {
        final Stage stage = new Stage();
        DominionModel dominionModel = new DominionModel(gameSettings);
        TerminalController playerTerminal = new TerminalController(dominionModel, gameSettings.getCurrentPlayerId());
        Scene scene = new Scene(playerTerminal);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        // eventually create AI Controllers
    }

    @Override
    public void update(String event) {
        resetOverviewImages();
        updatePlayerTable();
    }

    private void updatePlayerTable() {
        if (playerTable != null) {
            playerTable.getChildren().clear();
        }

        for (final SimplePlayerInfo playerInfo : gameSettings.getPlayerInfos()) {
            if (playerTable != null) {
                playerTable.getChildren().add(new PlayerRow(playerInfo));
            }
        }
    }
}
