package main;

import main.controller.MainController;

public class Application {
    public static void main(String[] args) {
        MainController game = new MainController();
        game.play();
    }
}