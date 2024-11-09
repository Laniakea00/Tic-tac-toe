public class HumanPlayerFactory extends PlayerFactory {
    @Override
    public Player createPlayer(String symbol, boolean isAI) {
        return new HumanPlayer(symbol);
    }
}
