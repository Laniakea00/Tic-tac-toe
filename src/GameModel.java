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
}
