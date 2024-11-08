public class GameBoardAdapterImpl implements GameBoardAdapter {
    private final GameModel gameModel;

    public GameBoardAdapterImpl(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public String[] getBoard() {
        return gameModel.getBoard();  // Возвращаем доску из модели
    }

    @Override
    public void setBoard(String[] newBoard) {
        gameModel.setBoard(newBoard);  // Устанавливаем новое состояние доски в модель
    }
}

