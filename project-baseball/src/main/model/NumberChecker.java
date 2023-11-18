package main.model;

public class NumberChecker {
    private static final int NUMBER_LENGTH = 3;

    public int checkStrike(String randomNumber, String userNumber) {
        String[] randomNumberArr = initializeArray(randomNumber);
        String[] userNumberArr = initializeArray(userNumber);

        int strike = 0;
        for (int i = 0; i < NUMBER_LENGTH; i++) {
            if (randomNumberArr[i].equals(userNumberArr[i])) {
                strike++;
            }
        }
        return strike;
    }

    public int checkBall(String randomNumber, String userNumber) {
        String[] randomNumberArr = initializeArray(randomNumber);
        String[] userNumberArr = initializeArray(userNumber);

        int ball = 0;
        for (int i = 0; i < NUMBER_LENGTH; i++) {
            ball += countMatchingBalls(randomNumberArr[i], userNumberArr);
        }
        return ball;
    }

    private String[] initializeArray(String number) {
        return String.valueOf(number).split("");
    }

    private int countMatchingBalls(String digit, String[] userNumberArr) {
        int ball = 0;
        for (String userDigit : userNumberArr) {
            if (digit.equals(userDigit)) {
                ball++;
            }
        }
        return ball > 0 ? ball - 1 : 0;
    }
}