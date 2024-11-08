import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private static GameModel instance;
    private String[] board;
    private List<Observer> observers;
    private String currentPlayer;

    private GameModel() {
        board = new String[9];
        observers = new ArrayList<>();
        currentPlayer = "X";
    }

    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }

    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = null;
        }
        currentPlayer = "X";
        notifyObservers();
    }

    public boolean makeMove(int index) {
        if (board[index] == null) { // Проверяем, что ячейка пуста
            board[index] = currentPlayer;
            currentPlayer = currentPlayer.equals("X") ? "O" : "X"; // Переключаем текущего игрока
            notifyObservers();
            return true;
        }
        return false;
    }

    public void setBoard(String[] newBoard) {
        this.board = newBoard.clone();  // Устанавливаем новое состояние доски
        notifyObservers();
    }

    public String[] getBoard() {
        return board.clone();
    }

    public void addObserver(Observer observer) {
        observers.add(observer); // Добавляем наблюдателя
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    // Метод для проверки победителя или ничьей
    public String checkWinner() {
        // Проверка горизонтальных линий
        for (int i = 0; i <= 6; i += 3) {
            if (board[i] != null && board[i].equals(board[i + 1]) && board[i].equals(board[i + 2])) {
                return board[i]; // Возвращаем победителя ("X" или "O")
            }
        }

        // Проверка вертикальных линий
        for (int i = 0; i < 3; i++) {
            if (board[i] != null && board[i].equals(board[i + 3]) && board[i].equals(board[i + 6])) {
                return board[i]; // Возвращаем победителя
            }
        }

        // Проверка диагональных линий
        if (board[0] != null && board[0].equals(board[4]) && board[0].equals(board[8])) {
            return board[0]; // Возвращаем победителя
        }
        if (board[2] != null && board[2].equals(board[4]) && board[2].equals(board[6])) {
            return board[2]; // Возвращаем победителя
        }

        // Проверка на ничью (все ячейки заполнены, но победителя нет)
        for (String cell : board) {
            if (cell == null) {
                return null; // Игра продолжается
            }
        }

        return "Draw"; // Если все ячейки заполнены и победителя нет, возвращаем "ничья"
    }

}
