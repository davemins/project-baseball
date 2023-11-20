package main.gui;

import main.controller.GameController;
import main.model.HintChecker;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JFrame {
    private GameController gameController;
    private JTextField userInputField;
    private JTextArea outputArea;
    private JTextArea gameInfoArea;
    private JLabel gameStatusLabel;

    public GameGUI() {
        gameController = new GameController();
        gameController.initializeGame();
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Number Game");

        userInputField = new JTextField(10);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUserInput();
            }
        });

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        gameInfoArea = new JTextArea(10, 30);
        gameInfoArea.setEditable(false);

        gameStatusLabel = new JLabel("Game in progress");

        JPanel mainPanel = new JPanel();
        mainPanel.add(new JLabel("Enter your guess: "));
        mainPanel.add(userInputField);
        mainPanel.add(submitButton);
        mainPanel.add(restartButton);
        mainPanel.add(outputArea);
        mainPanel.add(gameInfoArea);
        mainPanel.add(gameStatusLabel);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private void handleUserInput() {
        String userNumber = userInputField.getText();
        System.out.println("User input: " + userNumber); // 디버깅용 출력
        try {
            gameController.validateUserNumberInput(userNumber);
            int strike = gameController.checkStrike(userNumber);
            int ball = gameController.checkBall(userNumber);
            printHint(strike, ball);
            handleGameEnd(strike);
            updateGameInfo(userNumber);
        } catch (Exception ex) {
            outputArea.append(ex.getMessage() + "\n");
        }
    }

    private void printHint(int strike, int ball) {
        String hint = HintChecker.getHint(strike, ball);
        outputArea.append(hint + "\n");
    }

    private void handleGameEnd(int strike) {
        if (gameController.isGameEnd(strike)) {
            outputArea.append("Game Over!\n");
            gameInfoArea.append("Final Result: " + gameController.getFinalResult() + "\n");
            updateGameStatus("Game Over");
            userInputField.setEnabled(false);
        }
    }

    private void restartGame() {
        gameController.restartGame();
        clearOutput();
        updateGameStatus("Game in progress");
        userInputField.setEnabled(true);
        updateGameInfo("");
    }

    private void clearOutput() {
        outputArea.setText("");
        gameInfoArea.setText("");
        userInputField.setText("");
    }

    private void updateGameStatus(String status) {
        gameStatusLabel.setText(status);
    }

    private void updateGameInfo(String userNumber) {
        gameInfoArea.setText(gameController.getCurrentGameState(userNumber));
    }

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