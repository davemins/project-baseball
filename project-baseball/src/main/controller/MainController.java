package main.controller;

import main.model.HintChecker;
import main.model.NumberChecker;
import main.model.RandomNumberCreater;
import main.model.Validator;
import main.view.InputView;
import main.view.OutputView;

public class MainController {

    private String randomNumber;
    private String userNumber;
    private String userAnswer;
    private boolean gameRunning = true;
    private int strike;
    private int ball;

    RandomNumberCreater randomNumberCreater = new RandomNumberCreater();
    OutputView outputView = new OutputView();
    InputView inputView = new InputView();
    NumberChecker numberChecker = new NumberChecker();

    public void play() {
        initializeGame();

        while (gameRunning) {
            getUserInput();
            checkInput();
            printHint();
            handleGameEnd();
        }
    }

    private void initializeGame() {
        outputView.printStartGame();
        randomNumber = randomNumberCreater.getRandomNumber();
    }

    private void getUserInput() {
        userNumber = inputView.enterGameNumber();
        isValidUserNumberInput(userNumber);
    }

    private void checkInput() {
        strike = numberChecker.checkStrike(randomNumber, userNumber);
        ball = numberChecker.checkBall(randomNumber, userNumber);
    }

    private void printHint() {
        String hint = HintChecker.getHint(strike, ball);
        System.out.println(hint);
    }

    private void handleGameEnd() {
        if (isGameEnd()) {
            restartOrEndGame();
        }
    }

    private void restartOrEndGame() {
        userAnswer = inputView.enterAnswerRestartGame();
        isValidUserAnswerInput(userAnswer);

        if (userAnswer.equals("1")) {
            restartGame();
        } else {
            endGame();
        }
    }

    private void restartGame() {
        randomNumber = randomNumberCreater.getRandomNumber();
        gameRunning = true;
    }

    private void endGame() {
        gameRunning = false;
    }

    private boolean isGameEnd() {
        return strike == 3;
    }

    private void isValidUserNumberInput(String input) {
        Validator.isValidUserNumberInput(input);
    }

    private void isValidUserAnswerInput(String input) {
        Validator.isValidUserAnswerInput(input);
    }
}