package me.ienze.caEvolve;

/**
 * @author ienze
 */
public interface FitnessCalculator {

    void calculateFitnesses(CA[] cas);

    CA[] getLastCalculated();
}
