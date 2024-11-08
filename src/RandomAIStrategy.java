import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomAIStrategy implements AIStrategy {

    @Override
    public int getBestMove(String[] board, String aiSymbol, String playerSymbol) {
        List<Integer> availableMoves = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            if (board[i] == null) {
                availableMoves.add(i);
            }
        }

        if (!availableMoves.isEmpty()) {
            Random random = new Random();
            return availableMoves.get(random.nextInt(availableMoves.size()));
        }

        return -1; // Если нет доступных ходов
    }
}
