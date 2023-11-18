package main.model;

public class HintChecker {
    private static final int ZERO = 0;
    private static final int THREE = 3;
    private static final String NOTHING_MESSAGE = "낫싱";
    private static final String BALL_MESSAGE = "볼";
    private static final String STRIKE_MESSAGE = "스트라이크";
    private static final String GAME_END_MESSAGE = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";

    public static String getHint(int strike, int ball) {
        StringBuilder hint = new StringBuilder();

        if (isNothing(ball, strike)) {
            hint.append(NOTHING_MESSAGE);
        } else {
            appendBallHint(hint, ball);
            appendStrikeHint(hint, strike);
        }

        return hint.toString();
    }

    private static boolean isNothing(int ball, int strike) {
        return ball == ZERO && strike == ZERO;
    }

    private static void appendBallHint(StringBuilder hint, int ball) {
        if (ball > ZERO) {
            hint.append(ball).append(BALL_MESSAGE);
        }
    }

    private static void appendStrikeHint(StringBuilder hint, int strike) {
        if (strike > ZERO) {
            hint.append(strike).append(STRIKE_MESSAGE);
            if (strike == THREE) {
                hint.append("\n").append(GAME_END_MESSAGE);
            }
        }
    }
}