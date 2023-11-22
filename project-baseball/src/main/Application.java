package main;

import main.controller.GameController;
import main.view.GameGUI;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameGUI gameGUI = new GameGUI();
                gameGUI.setVisible(true);
            }
        });
    }
}