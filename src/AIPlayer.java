public class AIPlayer extends Player {
    private AIStrategy strategy;

    public AIPlayer(String symbol, AIStrategy strategy) {
        super(symbol); // Конструктор базового класса Player
        this.strategy = strategy;
    }

    @Override
    public int getMove(String[] board) {
        return strategy.getBestMove(board, this.symbol, "X".equals(this.symbol) ? "O" : "X");
    }
}
