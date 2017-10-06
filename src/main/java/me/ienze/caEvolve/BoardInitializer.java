package me.ienze.caEvolve;

import me.ienze.caEvolve.ca.DeterministicCA;

/**
 * @author ienze
 */
public interface BoardInitializer {

    void init(Board board, DeterministicCA ca);

}
