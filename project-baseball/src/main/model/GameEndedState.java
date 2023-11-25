package main.model;

public class GameEndedState implements GameState {
    @Override
    public void startGame() {
        System.out.println("이미 게임이 종료되었습니다. 재시작하려면 새로운 게임을 시작하세요.");
    }

    @Override
    public void endGame() {
        System.out.println("이미 게임이 종료되었습니다.");
    }

    @Override
    public void setRandomNumber(String randomNumber) {
        // 이미 게임이 종료되었으므로 상태 변경 필요 없음
    }

    @Override
    public boolean isGameRunning() {
        return false;
    }

    @Override
    public void setGameRunning(boolean gameRunning) {
        // 이미 게임이 종료되었으므로 상태 변경 필요 없음
    }

    @Override
    public void setState(GameState state) {
        // GameEndedState에서는 상태 변경이 필요하지 않음
    }

    @Override
    public String getCurrentGameState() {
        // 현재 게임 상태 정보 반환
        return "게임이 종료되었습니다.";
    }

    @Override
    public String getFinalResult() {
        // 최종 결과 반환
        return "게임 결과: 3개의 숫자를 모두 맞혔습니다! 게임 종료";
    }

    @Override
    public String getRandomNumber() {
        // 여기에서 적절한 값 또는 동작을 반환하세요.
        return "";
    }

    @Override
    public boolean isGameEnd(int strike) {
        return false;
    }
}