import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToeGame extends JFrame implements ActionListener {
    private JPanel buttonPanel;
    // private JPanel optionsPanel;
    // private JPanel botPanel;
    private JButton[][] gameButtons = new JButton[3][3];
    private char[][] board = new char[3][3];
    private int currentTurn;
    private String winner;

    // private JButton resetButton= new JButton("Reset");

    public TicTacToeGame() {
        setSize(300, 350);
        setVisible(true);
        this.setResizable(false);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3, 5, 5));
        buttonPanel.setBackground(Color.BLACK);
        this.add(buttonPanel, BorderLayout.CENTER);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '*';
                gameButtons[i][j] = new JButton();
                buttonPanel.add(gameButtons[i][j]);
                gameButtons[i][j].addActionListener(this);
            }
        }
        currentTurn = 1;
    }

    private void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '*';
                gameButtons[i][j].setText("");
            }
        }
        currentTurn = 1;
    }
    
    private boolean victory() {
        for(int i = 0; i < 3; i++) {
            if(board[i][0] != '*' && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                if(board[i][0] == 'X') {
                    winner = "X";
                } else {
                    winner = "O";
                }
                return true;
            } 
            if(board[0][i] != '*' && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                if(board[0][i] == 'X') {
                    winner = "X";
                } else {
                    winner = "O";
                }
                return true;
            } 
        }
        if(board[0][0] != '*' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            if(board[0][0] == 'X') {
                winner = "X";
            } else {
                winner = "O";
            }
            return true;
        } 
        if (board[0][2] != '*' && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            if(board[0][2] == 'X') {
                winner = "X";
            } else {
                winner = "O";
            }
            return true;
        }
        return false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int buttonPressedX = -1;
        int buttonPressedY = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(e.getSource() == gameButtons[i][j] && board[i][j] == '*') {
                    buttonPressedX = i;
                    buttonPressedY = j;
                    break;
                }
            }
            
        }
        if(buttonPressedX > -1 && buttonPressedY > -1) {
            if (currentTurn == 1) {
                board[buttonPressedX][buttonPressedY] = 'X';
                gameButtons[buttonPressedX][buttonPressedY].setText("X"); 
                currentTurn = 2;
            } else {
                board[buttonPressedX][buttonPressedY] = 'O';
                gameButtons[buttonPressedX][buttonPressedY].setText("O");
                currentTurn = 1;
            }
        }
        if(victory()) {
            int choice = JOptionPane.showConfirmDialog(this, String.format("%s wins! Play again?", winner));
            if (choice == 0) {
                reset();
            } else if (choice == 2) {
               
            } else {
                System.exit(0);
            }
        }
        getContentPane().validate();

    }

}
