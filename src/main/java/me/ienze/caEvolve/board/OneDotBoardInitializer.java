package me.ienze.caEvolve.board;

import me.ienze.caEvolve.Board;
import me.ienze.caEvolve.BoardInitializer;
import me.ienze.caEvolve.CaEvolveSettings;

/**
 * @author ienze
 */
public class OneDotBoardInitializer implements BoardInitializer {

    @Override
    public void init(Board board) {
        board.forEach(state -> board.set(state.getX(), state.getY(), 0));
        board.set(board.getWidth() / 2, board.getHeight() / 2, 1);
    }
}
