package me.ienze.caEvolve;

import me.ienze.twoDimMap.DistinctMapLayer;

/**
 * @author ienze.
 */
class Board extends DistinctMapLayer {

    public Board(CaEvolveSettings settings) {
        super(settings.stateCount, settings.boardWidth, settings.boardHeight);
    }

}
