public class GameController {
    private GameModel model;
    private GameView view;
    private HumanPlayer humanPlayer;
    private Player secondPlayer; // Может быть HumanPlayer или AIPlayer
    private boolean isHumanTurn;
    private boolean isPlayingWithAI;

    public GameController(GameView view, boolean playWithAI) {
        this.view = view;
        model = GameModel.getInstance();
        model.addObserver(view);

        // Настроить игроков в зависимости от режима игры
        if (playWithAI) {
            secondPlayer = new AIPlayer("O", new RandomAIStrategy());
            isPlayingWithAI = true;
        } else {
            secondPlayer = new HumanPlayer("O"); // Второй игрок-человек
            isPlayingWithAI = false;
        }

        humanPlayer = new HumanPlayer("X"); // Первый игрок
        isHumanTurn = true;
        model.resetBoard();
        updateTurnDisplay();
    }

    public void handleUserMove(int index) {
        if (isHumanTurn && model.makeMove(index)) {
            isHumanTurn = false;
            updateTurnDisplay();

            if (checkGameEnd()) return;

            if (isPlayingWithAI) {
                handleAIMove();
            } else {
                isHumanTurn = true;
                updateTurnDisplay();
            }
        } else if (!isHumanTurn && !isPlayingWithAI && model.makeMove(index)) {
            isHumanTurn = true;
            updateTurnDisplay();
            checkGameEnd();
        }
    }

    private void handleAIMove() {
        int aiMove = secondPlayer.getMove(model.getBoard());
        if (aiMove != -1 && model.makeMove(aiMove)) {
            isHumanTurn = true;
            updateTurnDisplay();
            checkGameEnd();
        }
    }

    private boolean checkGameEnd() {
        String winner = model.checkWinner();
        if (winner != null) {
            view.showWinner(winner.equals("Draw") ? "Ничья!" : "Победитель: " + winner);
            resetGame();
            return true;
        }
        return false;
    }

    public void resetGame() {
        model.resetBoard();
        isHumanTurn = true;
        updateTurnDisplay();
    }

    private void updateTurnDisplay() {
        view.setTurnDisplay(isHumanTurn ? "Ходит игрок (X)" : (isPlayingWithAI ? "Ходит ИИ (O)" : "Ходит игрок (O)"));
    }
}
