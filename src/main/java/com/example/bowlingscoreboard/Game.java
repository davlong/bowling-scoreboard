package com.example.bowlingscoreboard;

import javafx.beans.property.SimpleBooleanProperty;

import java.util.Random;

public class Game {
    SimpleBooleanProperty over = new SimpleBooleanProperty(false);
    private final Frame[] frames;
    private int currentFrame;
    Random random = new Random();

    public Game() {
        frames = new Frame[10];
        currentFrame = 0;
    }

    public Frame roll() {
        return roll(randomPins());
    }

    public Frame roll(int pins) {
        Frame frame = null;
        if (currentFrame < 10) {
            if (frames[currentFrame] == null) {
                frame = new Frame();
                if (currentFrame == 9) {
                    frame.setLast(true);
                }
                frame.setRoll1(pins);
                frames[currentFrame] = frame;
                calculateScoreAtFrame(currentFrame);
                if (pins == 10 && currentFrame < 9) {
                    currentFrame++;
                }
            } else {
                frames[currentFrame].setRoll2(pins);
                if (currentFrame == 9 && !(frames[9].isStrike() || frames[9].isSpare())) {
                    setOver(true);
                }
                calculateScoreAtFrame(currentFrame);
                currentFrame++;
            }
        } else {
            if (currentFrame == 10 && frames[9] != null && (frames[9].isStrike() || frames[9].isSpare())) {
                frames[9].setRoll3(pins);
                calculateScoreAtFrame(currentFrame);
                setOver(true);
            }
        }
        return frame;
    }

    public int calculateScoreAtFrame(int frame) {
        int totalScore = 0;
        for (int i = 0; i <= frame; i++) {
            if (frames[i] != null) {
                int bonus = 0;
                if (i > 0) {
                    if (frames[i - 1].isStrike()) {
                        bonus = frames[i].getRoll1() + frames[i].getRoll2();
                    } else if (frames[i - 1].isSpare()) {
                        bonus = frames[i].getRoll1();
                    }
                    frames[i - 1].setBonus(bonus);
                }
                totalScore += frames[i].getFrameScore();
            }
        }
        frames[frame].setGameScore(totalScore);
        return totalScore;
    }

    public int randomPins() {
        if (currentFrame == 10 && frames[9] != null && (frames[9].isStrike() || frames[9].isSpare())) {
            return random.nextInt(11);
        }
        if (frames[currentFrame] != null && frames[currentFrame].getRoll1() != 0) {
            return random.nextInt(11 - frames[currentFrame].getRoll1());
        }
        return random.nextInt(11);
    }

    public boolean isOver() {
        return over.get();
    }

    public SimpleBooleanProperty overProperty() {
        return over;
    }

    public void setOver(boolean over) {
        this.over.set(over);
    }
}
