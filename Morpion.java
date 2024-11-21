import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Morpion extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private boolean isPlayerX = true; // true: Player X | false: Player O

    public Morpion() {
        setTitle("Morpion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));

        // Initialisation de la grille
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 50));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                add(buttons[i][j]);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!buttons[row][col].getText().equals("")) {
                return; // Case déjà occupée
            }

            buttons[row][col].setText(isPlayerX ? "X" : "O");
            buttons[row][col].setEnabled(false);

            if (checkWin()) {
                JOptionPane.showMessageDialog(null, (isPlayerX ? "X" : "O") + " a gagné !");
                resetGame();
                return;
            }

            if (isBoardFull()) {
                JOptionPane.showMessageDialog(null, "Match nul !");
                resetGame();
                return;
            }

            isPlayerX = !isPlayerX; // Changer de joueur
        }
    }

    private boolean checkWin() {
        // Vérifie les lignes, colonnes et diagonales
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                buttons[i][1].getText().equals(buttons[i][2].getText()) &&
                !buttons[i][0].getText().equals("")) {
                return true;
            }

            if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                buttons[1][i].getText().equals(buttons[2][i].getText()) &&
                !buttons[0][i].getText().equals("")) {
                return true;
            }
        }

        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][2].getText()) &&
            !buttons[0][0].getText().equals("")) {
            return true;
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][0].getText()) &&
            !buttons[0][2].getText().equals("")) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        isPlayerX = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Morpion morpion = new Morpion();
            morpion.setVisible(true);
        });
    }
}
