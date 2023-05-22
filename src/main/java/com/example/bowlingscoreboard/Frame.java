package com.example.bowlingscoreboard;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;

public class Frame {
    private final SimpleIntegerProperty roll1 = new SimpleIntegerProperty();
    private final SimpleIntegerProperty roll2 = new SimpleIntegerProperty();
    private final SimpleIntegerProperty roll3 = new SimpleIntegerProperty();
    private final SimpleIntegerProperty bonus = new SimpleIntegerProperty();
    private final SimpleIntegerProperty frameScore = new SimpleIntegerProperty();
    private final SimpleIntegerProperty gameScore = new SimpleIntegerProperty();
    private boolean last = false;

    public Frame() {
        frameScore.bind(Bindings.add(roll1Property().add(roll2Property()), roll3Property().add(bonusProperty())));
    }

    public int getRoll1() {
        return roll1.get();
    }

    public SimpleIntegerProperty roll1Property() {
        return roll1;
    }

    public void setRoll1(int roll1) {
        this.roll1.set(roll1);
    }

    public int getRoll2() {
        return roll2.get();
    }

    public SimpleIntegerProperty roll2Property() {
        return roll2;
    }

    public void setRoll2(int roll2) {
        this.roll2.set(roll2);
    }

    public int getRoll3() {
        return roll3.get();
    }

    public SimpleIntegerProperty roll3Property() {
        return roll3;
    }

    public void setRoll3(int roll3) {
        this.roll3.set(roll3);
    }

    public int getBonus() {
        return bonus.get();
    }

    public SimpleIntegerProperty bonusProperty() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus.set(bonus);
    }

    public int getFrameScore() {
        return frameScore.get();
    }

    public SimpleIntegerProperty frameScoreProperty() {
        return frameScore;
    }

    public void setFrameScore(int frameScore) {
        this.frameScore.set(frameScore);
    }

    public int getGameScore() {
        return gameScore.get();
    }

    public SimpleIntegerProperty gameScoreProperty() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore.set(gameScore);
    }

    public boolean isStrike() {
        return getRoll1() == 10;
    }
    public boolean isSpare() {
        return getRoll1() + getRoll2() == 10;
    }
    public boolean isLast() {
        return last;
    }
    public void setLast(boolean last) {
        this.last = last;
    }
}
