package TicTacToeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGameGUI extends JFrame {
    private JButton[][] buttons; // 2D array for the buttons representing the board
    private boolean isXturn; // to track turns
    private JButton restarButton;
    private JLabel scoreLabel;
    private int xWins = 0;
    private int oWins = 0;
    private int draws = 0;

    public TicTacToeGameGUI()
    {
        //JFrame
        setTitle("Tic-Tac-Toe Game");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // Use BorderLayout

        // Initialize the game board (Center panel)
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3)); //3x3
        buttons = new JButton[3][3];
        isXturn = true; // X starts


        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                buttons[row][col] = new JButton("");
                buttons[row][col].setBackground(Color.YELLOW);
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(new ButtonClickListener(row, col));
                gamePanel.add(buttons[row][col]);
            }
        }
        add(gamePanel, BorderLayout.CENTER);

        scoreLabel = new JLabel("X: 0, O: 0, Draws: 0", JLabel.CENTER);
        add(scoreLabel, BorderLayout.NORTH);

        restarButton = new JButton("Restart");
        restarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                resetGame();
            }
        });
        add(restarButton, BorderLayout.SOUTH);

        //make frame visible
        setVisible(true);
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col)
        {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (buttons[row][col].getText().equals(""))
            {
                buttons[row][col].setText(isXturn ? "X" : "O");
                buttons[row][col].setBackground(Color.ORANGE);
                isXturn = !isXturn;
                checkForWinner();
            }
        }
    }

    private void updateScore() {
        scoreLabel.setText("X: " + xWins + ", O: " + oWins + ", Draws: " + draws);
    }

    private void announceWinner(String winner) {
        if (winner.equals("X")) {
            xWins++;
        } else if (winner.equals("O")) {
            oWins++;
        }
        updateScore();
        JOptionPane.showMessageDialog(this, winner + " wins!");
        disableBoard();
    }

    private void announceDraw() {
        draws++;
        updateScore();
        JOptionPane.showMessageDialog(this, "It's a draw!");
        disableBoard();
    }

    private void checkForWinner()
    {
        int Xcount = 0;
        int Ocount = 0;
        boolean Xwon = false;
        boolean Owon = false;
        for (int rowChecker = 0; rowChecker < 3; rowChecker++)
        {
            Xcount = 0;
            Ocount = 0;
            for (int colChecker = 0; colChecker < 3; colChecker++)
            {
                if (buttons[rowChecker][colChecker].getText().equals("X"))
                {
                    Xcount++;
                }
                else if (buttons[rowChecker][colChecker].getText().equals("O"))
                {
                    Ocount++;
                }
                if (Xcount == 3)
                {
                    Xwon = true;
                    break;
                }
                else if (Ocount == 3)
                {
                    Owon = true;
                    break;
                }
            }
        }
        for (int colChecker = 0; colChecker < 3; colChecker++)
        {
            Xcount = 0;
            Ocount = 0;
            for (int rowChecker = 0; rowChecker < 3; rowChecker++)
            {
                if (buttons[rowChecker][colChecker].getText().equals("X"))
                {
                    Xcount++;
                }
                else if (buttons[rowChecker][colChecker].getText().equals("O"))
                {
                    Ocount++;
                }
                if (Xcount == 3)
                {
                    Xwon = true;
                    break;
                }
                else if (Ocount == 3)
                {
                    Owon = true;
                    break;
                }
            }
        }
        if (buttons[0][0].getText().equals("X") && buttons[1][1].getText().equals("X") && buttons[2][2].getText().equals("X"))
        {
            Xwon = true;
        }
        else if (buttons[0][0].getText().equals("O") && buttons[1][1].getText().equals("O") && buttons[2][2].getText().equals("O"))
        {
            Owon = true;
        }
        if (buttons[0][2].getText().equals("X") && buttons[1][1].getText().equals("X") && buttons[2][0].getText().equals("X"))
        {
            Xwon = true;
        }
        else if (buttons[0][2].getText().equals("O") && buttons[1][1].getText().equals("O") && buttons[2][0].getText().equals("O"))
        {
            Owon = true;
        }

        if (Xwon)
        {
            announceWinner("X");
        }
        else if (Owon)
        {
            announceWinner("O");
        }
        else //check draw
        {
            boolean draw = true;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (buttons[row][col].getText().equals(""))
                    {
                        draw = false;
                        break;
                    }
                }
            }
            if (draw)
            {
                announceDraw();
            }
        }
    }

    private void disableBoard()
    {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setEnabled(false);
            }
        }
    }

    private void resetGame()
    {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setBackground(Color.YELLOW);
                buttons[row][col].setEnabled(true);
            }
        }
        isXturn = true;
    }

    public static void main(String[] args)
    {
        new TicTacToeGameGUI();
    }
}
