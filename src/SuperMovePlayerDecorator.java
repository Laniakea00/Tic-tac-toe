public class SuperMovePlayerDecorator extends PlayerDecorator {
    public SuperMovePlayerDecorator(Player player) {
        super(player);
    }

    @Override
    public int getMove(String[] board) {
        System.out.println("Используется суперход!");
        return super.getMove(board); // Можем вызвать базовую логику или дополнить ее
    }
}
