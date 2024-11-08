public abstract class PlayerDecorator extends Player {
    protected Player wrappedPlayer;

    public PlayerDecorator(Player player) {
        super(player.getSymbol());
        this.wrappedPlayer = player;
    }

    @Override
    public int getMove(String[] board) {
        return wrappedPlayer.getMove(board); // Перенаправляем вызов
    }
}
