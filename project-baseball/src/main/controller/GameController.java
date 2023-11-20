package main.controller;

import main.model.HintChecker;
import main.model.NumberChecker;
import main.model.RandomNumberCreator;
import main.model.Validator;
import main.view.InputView;
import main.view.OutputView;

public class GameController {

    private String randomNumber;
    private boolean gameRunning = true;
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

    public void initializeGame() {
        outputView.printStartGame();
        RandomNumberCreator newRandomNumber = new RandomNumberCreator();
        randomNumber = newRandomNumber.getRandomNumber();
        gameRunning = true;
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
        int strike = numberChecker.checkStrike(randomNumber, userNumber);
        System.out.println("Strike: " + strike); // 디버깅용 출력
        return strike;
    }

    public int checkBall(String userNumber) {
        int ball = numberChecker.checkBall(randomNumber, userNumber);
        System.out.println("Ball: " + ball); // 디버깅용 출력
        return ball;
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
            initializeGame();
        } else {
            endGame();
        }
    }

    private void validateUserAnswerInput(String userAnswer) {
        Validator.isValidUserAnswerInput(userAnswer);
    }

    private void endGame() {
        gameRunning = false;
    }

    public boolean isGameEnd(int strike) {
        return strike == 3;
    }

    // 게임의 현재 상태(스트라이크, 볼, 힌트 등)를 반환하는 메서드
    public String getCurrentGameState(String userNumber) {
        int strike = checkStrike(userNumber);
        int ball = checkBall(userNumber);
        // 여기서 현재 상태에 대한 메시지를 생성하여 반환
        String currentGameState = "현재 스트라이크: " + strike + ", 현재 볼: " + ball; // 예시 메시지, 수정이 필요함
        return currentGameState;
    }

    // 게임 결과를 반환하는 메서드
    public String getFinalResult() {
        if (isGameEnd(0)) { // 여기에 적절한 값 입력 (현재 상태가 필요한 경우 getCurrentGameState() 사용)
            // 게임이 종료된 경우 최종 결과 메시지를 생성하여 반환
            return "게임 결과: " + "3개의 숫자를 모두 맞혔습니다! 게임 종료"; // 예시 메시지, 수정이 필요함
        } else {
            // 아직 게임이 진행 중인 경우 게임 중 메시지를 반환
            return "게임 중"; // 예시 메시지, 수정이 필요함
        }
    }
}