public class MinimaxAIStrategy implements AIStrategy {

    @Override
    public int getBestMove(String[] board, String aiSymbol, String playerSymbol) {
        int bestScore = Integer.MIN_VALUE;
        int move = -1;

        for (int i = 0; i < board.length; i++) {
            if (board[i] == null) { // Если ячейка пустая
                board[i] = aiSymbol; // Попробуем ход для ИИ
                int score = minimax(board, false, aiSymbol, playerSymbol); // Рекурсивно считаем оценку
                board[i] = null; // Отменяем ход

                if (score > bestScore) {
                    bestScore = score;
                    move = i;
                }
            }
        }
        return move;
    }

    private int minimax(String[] board, boolean isMaximizing, String aiSymbol, String playerSymbol) {
        String winner = GameModel.getInstance().checkWinner(); // Предполагается, что этот метод определен в GameModel
        if (winner != null) {
            if (winner.equals(aiSymbol)) return 1;
            else if (winner.equals(playerSymbol)) return -1;
            else return 0;
        }

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == null) { // Проверяем, что ячейка пустая
                board[i] = isMaximizing ? aiSymbol : playerSymbol; // Выполняем ход
                int score = minimax(board, !isMaximizing, aiSymbol, playerSymbol);
                board[i] = null; // Отменяем ход

                if (isMaximizing) {
                    bestScore = Math.max(score, bestScore);
                } else {
                    bestScore = Math.min(score, bestScore);
                }
            }
        }
        return bestScore;
    }
}
