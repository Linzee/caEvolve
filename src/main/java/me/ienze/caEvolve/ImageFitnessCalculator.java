package me.ienze.caEvolve;

import java.io.File;

/**
 * @author ienze
 */
public class ImageFitnessCalculator implements FitnessCalculator {

    public ImageFitnessCalculator(File file) {

    }

    @Override
    public double[] calculateFitnesses(CA[] cas) {

        double[] fitnesses = new double[cas.length];

        for (int i = 0; i < cas.length; i++) {
            CA ca = cas[i];

            Board board = ca.getResultBoard();


            double fitness = 0;
            fitnesses[i] = fitness;
        }

        return fitnesses;
    }

}
