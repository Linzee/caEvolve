package me.ienze.caEvolve;

/**
 * @author ienze
 */
interface FitnessCalculator {

    void calculateFitnesses(CA[] cas);

    double getFitness(CA ca);
}
