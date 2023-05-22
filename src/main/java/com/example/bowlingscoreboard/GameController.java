package com.example.bowlingscoreboard;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private final ToggleGroup rollType = new ToggleGroup();
    @FXML private MenuItem newGame, close;
    @FXML private Label gameOver;
    @FXML private HBox frameContainer;
    @FXML private RadioButton rollTypeRandom, rollTypeValue;
    @FXML private Spinner<Integer> value;
    @FXML private Button roll;
    private Game game;
    private Frame currentFrame;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = new Game();
        roll.setOnAction(e -> roll());

        rollType.getToggles().add(rollTypeRandom);
        rollType.getToggles().add(rollTypeValue);
        rollTypeRandom.selectedProperty().bindBidirectional(value.disableProperty());
        rollTypeRandom.setSelected(true);

        bindProperties();

        newGame.setOnAction(e -> newGame());
        close.setOnAction(e -> Platform.exit());
    }

    private void roll() {
        Frame newFrame;
        if (rollTypeValue.isSelected()) {
            newFrame = game.roll(value.getValue());
        } else {
            newFrame = game.roll();
        }

        if (newFrame != null && (currentFrame == null || !currentFrame.equals(newFrame))) {
            loadFrame(newFrame);
            currentFrame = newFrame;
        }

        updateSpinnerValueFactory();
    }

    private void loadFrame(Frame frame) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Frame.class.getResource(getFrameViewName(frame)));
            GridPane gridPane = fxmlLoader.load();
            frameContainer.getChildren().add(gridPane);

            FrameController frameController = fxmlLoader.getController();
            frameController.setFrame(frame);
            frameController.initProperties();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFrameViewName(Frame frame) {
        if (frame.isLast()) {
            return "last-frame-view.fxml";
        } else {
            return "frame-view.fxml";
        }
    }

    private void bindProperties() {
        roll.disableProperty().bindBidirectional(game.overProperty());
        rollTypeRandom.disableProperty().bindBidirectional(game.overProperty());
        rollTypeValue.disableProperty().bindBidirectional(game.overProperty());
        gameOver.visibleProperty().bindBidirectional(game.overProperty());
    }

    private void newGame() {
        this.game = new Game();
        this.currentFrame = null;
        frameContainer.getChildren().clear();
        value.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0));
        bindProperties();
    }

    private void updateSpinnerValueFactory() {
        if (currentFrame != null) {
            if (currentFrame.getRoll1() != 0 && !currentFrame.isStrike()) {
                Integer currentValue = value.getValue();
                int max = 10 - currentFrame.getRoll1();
                int newValue = currentValue > max ? max : currentValue;
                value.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, max, newValue));
            }
        }
    }

}