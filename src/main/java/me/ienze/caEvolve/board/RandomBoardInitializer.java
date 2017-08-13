package me.ienze.caEvolve.board;

import me.ienze.caEvolve.Board;
import me.ienze.caEvolve.BoardInitializer;
import me.ienze.caEvolve.CaEvolveSettings;

/**
 * @author ienze
 */
public class RandomBoardInitializer implements BoardInitializer {

    private final CaEvolveSettings settings;

    public RandomBoardInitializer(CaEvolveSettings settings) {
        this.settings = settings;
    }

    @Override
    public void init(Board board) {
        board.forEach(state -> board.set(state.getX(), state.getY(), settings.random.nextInt(settings.stateCount)));
    }
}
