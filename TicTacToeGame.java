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

    /**
     * Constructor for the game, sets the size of the frame, and makes it
     * visible and not resizable. Then creates the object for button Panel and
     * gives it a GridLayour with 3 rows and columns and a padding of 5 between
     * each. The last part of the constructor initialzes the button array and
     * "board" array to track what is stored in each position to check for
     * winner, then sets the currentTurn to 1.
     */
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

    /**
     * Sets the turn to one, clears the resets the board array to '*' and
     * removes any text from the JButtons.
     */
    private void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '*';
                gameButtons[i][j].setText("");
            }
        }
        currentTurn = 1;
    }

    /**
     * Loops through the rows and then the columns for a winner, then checks the
     * diagonals returning true if there are three in a row of the same symbol.
     * 
     * @author: Administrator
     * @return
     */
    private boolean victory() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '*' && board[i][0] == board[i][1]
                    && board[i][0] == board[i][2]) {
                if (board[i][0] == 'X') {
                    winner = "X";
                } else {
                    winner = "O";
                }
                return true;
            }
            if (board[0][i] != '*' && board[0][i] == board[1][i]
                    && board[0][i] == board[2][i]) {
                if (board[0][i] == 'X') {
                    winner = "X";
                } else {
                    winner = "O";
                }
                return true;
            }
        }
        if (board[0][0] != '*' && board[0][0] == board[1][1]
                && board[0][0] == board[2][2]) {
            if (board[0][0] == 'X') {
                winner = "X";
            } else {
                winner = "O";
            }
            return true;
        }
        if (board[0][2] != '*' && board[0][2] == board[1][1]
                && board[0][2] == board[2][0]) {
            if (board[0][2] == 'X') {
                winner = "X";
            } else {
                winner = "O";
            }
            return true;
        }
        return false;
    }

    /**
     * Loops through the 2D arrays to find the source of the action and stores
     * the row and column position if the position is not already an X or O.
     * Then if the position was captured the button's text will be set to X if
     * it is currently player 1's turn or O if player 2's turn, while also
     * switching to the other turn. Lastly the method will create a JOptionPane
     * if there is a winner, which will display the winner and ask if the users
     * would like to play again.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int buttonPressedX = -1;
        int buttonPressedY = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (e.getSource() == gameButtons[i][j] && board[i][j] == '*') {
                    buttonPressedX = i;
                    buttonPressedY = j;
                    break;
                }
            }

        }
        if (buttonPressedX > -1 && buttonPressedY > -1) {
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
        if (victory()) {
            int choice = JOptionPane.showConfirmDialog(this,
                    String.format("%s wins! Play again?", winner));
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
