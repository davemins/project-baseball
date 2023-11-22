package main.view;

public class OutputView {
    private static final String GAME_START_MESSAGE = "숫자 게임을 시작합니다.";
    private static final OutputView instance = new OutputView();

    private OutputView() {}

    public static OutputView getInstance() {
        return instance;
    }
    public void printStartGame() {
        System.out.println(GAME_START_MESSAGE);
    }
}
