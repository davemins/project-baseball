package main.model;
public class PlayingState implements GameState {
    private String randomNumber;
    private boolean gameRunning = true;

    @Override
    public void startGame() {
        // 게임 시작 로직
        System.out.println("숫자 게임을 시작합니다.");
    }

    @Override
    public void endGame() {
        // 게임 종료 로직
        System.out.println("게임이 종료되었습니다.");
    }

    @Override
    public void setRandomNumber(String randomNumber) {
        this.randomNumber = randomNumber;
    }

    @Override
    public boolean isGameRunning() {
        return gameRunning;
    }

    @Override
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    @Override
    public void setState(GameState state) {
        // 현재 상태에서의 상태 변경 로직
        // 여기서는 PlayingState에서는 상태 변경이 필요 없음
    }

    @Override
    public String getCurrentGameState() {
        // 현재 게임 상태 정보 반환
        return "게임 진행 중";
    }

    @Override
    public String getFinalResult() {
        // 최종 결과 반환
        return "게임 중";
    }

    @Override
    public String getRandomNumber() {
        return randomNumber;
    }

    @Override
    public boolean isGameEnd(int strike) {
        return strike == 3;
    }
}