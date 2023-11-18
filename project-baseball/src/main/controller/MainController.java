package main.controller;

import main.model.HintChecker;
import main.model.NumberChecker;
import main.model.RandomNumberCreator;
import main.model.Validator;
import main.view.InputView;
import main.view.OutputView;

public class MainController {

    private String randomNumber;
    private boolean gameRunning = true;

    private final RandomNumberCreator randomNumberCreator = new RandomNumberCreator();
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final NumberChecker numberChecker = new NumberChecker();

    public void play() {
        initializeGame();

        while (gameRunning) {
            String userNumber = getUserInput();
            int strike = checkStrike(userNumber);
            int ball = checkBall(userNumber);
            printHint(strike, ball);
            handleGameEnd(strike);
        }
    }

    private void initializeGame() {
        outputView.printStartGame();
        randomNumber = randomNumberCreator.getRandomNumber();
    }

    private String getUserInput() {
        String userNumber = inputView.enterGameNumber();
        try {
            validateUserNumberInput(userNumber);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            getUserInput();
        }
        return userNumber;
    }

    private void validateUserNumberInput(String userNumber) {
        Validator.isValidUserNumberInput(userNumber);
    }

    private int checkStrike(String userNumber) {
        return numberChecker.checkStrike(randomNumber, userNumber);
    }

    private int checkBall(String userNumber) {
        return numberChecker.checkBall(randomNumber, userNumber);
    }

    private void printHint(int strike, int ball) {
        String hint = HintChecker.getHint(strike, ball);
        System.out.println(hint);
    }

    private void handleGameEnd(int strike) {
        if (isGameEnd(strike)) {
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
            restartGame();
        } else {
            endGame();
        }
    }

    private void validateUserAnswerInput(String userAnswer) {
        Validator.isValidUserAnswerInput(userAnswer);
    }

    private void restartGame() {
        randomNumber = randomNumberCreator.getRandomNumber();
        gameRunning = true;
    }

    private void endGame() {
        gameRunning = false;
    }

    private boolean isGameEnd(int strike) {
        return strike == 3;
    }
}