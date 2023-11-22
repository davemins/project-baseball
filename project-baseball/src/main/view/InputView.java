package main.view;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_NUMBER_MESSAGE = "숫자를 입력해주세요 : ";
    private static final String ASK_RESET_GAME_MESSAGE = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    private static Scanner scanner;
    private static final InputView instance = new InputView();

    private InputView() {}

    public static InputView getInstance() {
        return instance;
    }

    public String enterGameNumber() {
        System.out.print(INPUT_NUMBER_MESSAGE);
        return getInstance().getScanner().nextLine();
    }

    public String enterAnswerRestartGame() {
        System.out.print(ASK_RESET_GAME_MESSAGE);
        return getInstance().getScanner().nextLine();
    }

    private Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}