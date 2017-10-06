package me.ienze.caEvolve.board;

import me.ienze.caEvolve.Board;
import me.ienze.caEvolve.BoardInitializer;
import me.ienze.caEvolve.ca.DeterministicCA;

/**
 * @author ienze
 */
public class QrRulesBoardInitializer implements BoardInitializer {

    @Override
    public void init(Board board, DeterministicCA ca) {
        ca.getTransitions()
    }
}
