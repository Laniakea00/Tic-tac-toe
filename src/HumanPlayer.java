public class HumanPlayer extends Player {
    public HumanPlayer(String symbol) {
        super(symbol);
    }

    @Override
    public int getMove(String[] board) {
        // Этот метод не используется для человека в нашем контроллере,
        // так как ход человека обрабатывается напрямую через интерфейс
        return -1;
    }
}
