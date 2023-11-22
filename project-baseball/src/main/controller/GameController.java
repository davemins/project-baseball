package main.controller;

import main.model.*;
import main.view.InputView;
import main.view.OutputView;

public class GameController {

    private GameState gameState;
    private final OutputView outputView = OutputView.getInstance();
    private final InputView inputView = InputView.getInstance();
    private final NumberChecker numberChecker = new NumberChecker();

    public void play() {
        initializeGame();

        while (gameState.isGameRunning()) {
            String userNumber = getUserInput();
            int strike = checkStrike(userNumber);
            int ball = checkBall(userNumber);
            printHint(strike, ball);
            handleGameEnd(strike);
        }
    }

    public void initializeGame() {
        gameState = new PlayingState();
        outputView.printStartGame();
        RandomNumberCreator newRandomNumber = new RandomNumberCreator();
        gameState.setRandomNumber(newRandomNumber.getRandomNumber());
    }

    private String getUserInput() {
        String userNumber = inputView.enterGameNumber();
        try {
            validateUserNumberInput(userNumber);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            getUserInput();
        }
        return userNumber;
    }

    public void validateUserNumberInput(String userNumber) {
        Validator.isValidUserNumberInput(userNumber);
    }

    public int checkStrike(String userNumber) {
        return numberChecker.checkStrike(gameState.getRandomNumber(), userNumber);
    }

    public int checkBall(String userNumber) {
        return numberChecker.checkBall(gameState.getRandomNumber(), userNumber);
    }

    private void printHint(int strike, int ball) {
        String hint = HintChecker.getHint(strike, ball);
        System.out.println(hint);
    }

    private void handleGameEnd(int strike) {
        if (gameState.isGameEnd(strike)) {
            restartOrEndGame();
        }
    }

    private void restartOrEndGame() {
        String userAnswer = inputView.enterAnswerRestartGame();
        try {
            validateUserAnswerInput(userAnswer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            restartOrEndGame();
        }
        if (userAnswer.equals("1")) {
            initializeGame();
        } else {
            endGame();
        }
    }

    private void validateUserAnswerInput(String userAnswer) {
        Validator.isValidUserAnswerInput(userAnswer);
    }

    private void endGame() {
        gameState.setGameRunning(false);
        gameState.setState(new GameEndedState());
    }

    public String getCurrentGameState(String userNumber) {
        int strike = checkStrike(userNumber);
        int ball = checkBall(userNumber);
        return "현재 황소: " + strike + ", 현재 소: " + ball;
    }
    public boolean isGameEnd(int strike) {
        return strike == 3;
    }

    public String getFinalResult() {
        if (gameState.isGameEnd(0)) {
            return "게임 결과: " + "3개의 숫자를 모두 맞혔습니다! 게임 종료";
        } else {
            return "게임 중";
        }
    }
}