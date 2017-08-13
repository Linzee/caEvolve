package me.ienze.caEvolve.fitness;

import me.ienze.caEvolve.Board;
import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.FitnessCalculator;

/**
 * @author ienze
 */
public class ZeroFitnessCalculator implements FitnessCalculator {

    @Override
    public double[] calculateFitnesses(CA[] cas) {

        double[] fitnesses = new double[cas.length];

        for (int i = 0; i < cas.length; i++) {
            fitnesses[i] = 0;
        }

        return fitnesses;
    }

}
