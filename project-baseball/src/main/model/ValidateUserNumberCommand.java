package main.model;

import main.controller.GameController;

class ValidateUserNumberCommand implements Command {
    private final GameController gameController;
    private final String userNumber;

    public ValidateUserNumberCommand(GameController gameController, String userNumber) {
        this.gameController = gameController;
        this.userNumber = userNumber;
    }

    @Override
    public void execute() {
        gameController.validateUserNumberInput(userNumber);
    }
}