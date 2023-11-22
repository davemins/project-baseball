package main.model;

public interface GameState {
    void startGame();
    void endGame();
    void setRandomNumber(String randomNumber);
    boolean isGameRunning();
    void setGameRunning(boolean gameRunning);
    void setState(GameState state);
    String getCurrentGameState();
    String getFinalResult();
    String getRandomNumber();
    boolean isGameEnd(int strike);
}