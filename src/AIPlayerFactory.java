public class AIPlayerFactory extends PlayerFactory {
    @Override
    public Player createPlayer(String symbol, boolean isAI) {
        return new AIPlayer(symbol, new RandomAIStrategy()); // Можно передавать разные стратегии
    }
}

