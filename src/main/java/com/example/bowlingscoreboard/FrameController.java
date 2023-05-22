package com.example.bowlingscoreboard;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.converter.NumberStringConverter;

public class FrameController {

    @FXML private Label roll1, roll2, roll3, score;

    private Frame frame;

    public void initProperties() {
        roll1.textProperty().bindBidirectional(frame.roll1Property(), new NumberStringConverter());
        roll2.textProperty().bindBidirectional(frame.roll2Property(), new NumberStringConverter());
        if (roll3 != null) {
            roll3.textProperty().bindBidirectional(frame.roll3Property(), new NumberStringConverter());
        }
        score.textProperty().bindBidirectional(frame.gameScoreProperty(), new NumberStringConverter());
    }

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }
}
