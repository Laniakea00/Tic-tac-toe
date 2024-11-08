import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame implements Observer {
    private JButton[] buttons;
    private JLabel turnLabel;
    private GameController controller;

    public GameView() {
        setupUI();
        String gameMode = showGameModeDialog();
        setupGameController(gameMode);
    }

    // Диалоговое окно для выбора режима игры
    private String showGameModeDialog() {
        String[] options = {"Играть с другом", "Играть с ИИ"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "Выберите режим игры:",
                "Режим игры",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[1]
        );
        return choice == 0 ? "friend" : "ai";
    }

    // Настроить GameController в зависимости от выбора
    private void setupGameController(String gameMode) {
        controller = new GameController(this, "ai".equals(gameMode)); // передаем GameView и режим
        controller.resetGame();
    }

    private void setupUI() {
        setTitle("Крестики-нолики");
        setSize(300, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        turnLabel = new JLabel("Ходит игрок (X)");
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(turnLabel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        buttons = new JButton[9];

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            final int index = i;
            buttons[i].addActionListener(e -> controller.handleUserMove(index));
            boardPanel.add(buttons[i]);
        }

        add(boardPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void setTurnDisplay(String message) {
        turnLabel.setText(message);
    }

    public void showWinner(String winner) {
        JOptionPane.showMessageDialog(this, winner);
        controller.resetGame();
    }

    @Override
    public void update() {
        String[] board = GameModel.getInstance().getBoard();
        for (int i = 0; i < board.length; i++) {
            buttons[i].setText(board[i] == null ? "" : board[i]); // Обновляем текст кнопок
        }
    }
}
