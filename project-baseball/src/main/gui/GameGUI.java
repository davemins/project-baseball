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
        setTitle("숫자 게임");

        setSize(740, 350); // 크기 추가

        userInputField = new JTextField(10);

        JButton submitButton = new JButton("입력");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUserInput();
            }
        });

        JButton restartButton = new JButton("재시작");
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

        gameStatusLabel = new JLabel("게임 진행 중");

        JPanel mainPanel = new JPanel();
        mainPanel.add(new JLabel("3자리 숫자를 입력하세요: "));
        mainPanel.add(userInputField);
        mainPanel.add(submitButton);
        mainPanel.add(restartButton);
        mainPanel.add(outputArea);
        mainPanel.add(gameInfoArea);
        mainPanel.add(gameStatusLabel);

        add(mainPanel);

        setLocationRelativeTo(null);
    }

    private void handleUserInput() {
        String userNumber = userInputField.getText();
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
            updateGameStatus("게임 종료");
            userInputField.setEnabled(false);
        }
    }

    private void restartGame() {
        clearOutput();
        updateGameStatus("게임 진행");
        userInputField.setEnabled(true);
        gameController.initializeGame();
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