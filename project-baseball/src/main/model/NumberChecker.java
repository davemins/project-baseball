package main.model;

public class NumberChecker {
    private static final int ZERO = 0;
    private static final int NUMBER_LENGTH = 3;
    int strike = 0;
    int ball = 0;

    public int checkStrike(String randomNumber, String userNumber) {

        String[] randomNumberArr = String.valueOf(randomNumber).split("");
        String[] userNumberArr = String.valueOf(userNumber).split("");

        strike = ZERO;

        for (int i = ZERO; i < NUMBER_LENGTH; i++) {
            if (randomNumberArr[i].equals(userNumberArr[i])) {
                strike++;
            }
        }
        return strike;
    }

    public int checkBall(String randomNumber, String userNumber) {

        String[] randomNumberArr = String.valueOf(randomNumber).split("");
        String[] userNumberArr = String.valueOf(userNumber).split("");

        ball = ZERO;

        for (int i = ZERO; i < NUMBER_LENGTH; i++) {
            for (int j = ZERO; j < NUMBER_LENGTH; j++) {
                if (randomNumberArr[i].equals(userNumberArr[j]) && i != j) {
                    ball++;
                }
            }
        }
        return ball;
    }
}