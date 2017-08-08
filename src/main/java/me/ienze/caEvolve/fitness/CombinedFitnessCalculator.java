package me.ienze.caEvolve.fitness;

import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.FitnessCalculator;

/**
 * @author ienze
 */
public class CombinedFitnessCalculator implements FitnessCalculator {

    private final FitnessCalculator[] calculators;

    public CombinedFitnessCalculator(FitnessCalculator... calculators) {
        this.calculators = calculators;
    }

    @Override
    public double[] calculateFitnesses(CA[] cas) {
        double[] avg = new double[cas.length];

        int x = 1;
        for (FitnessCalculator calculator : calculators) {
            double[] fitnesses = calculator.calculateFitnesses(cas);
            for (int i = 0; i < fitnesses.length; i++) {
                avg[i] = (avg[i] + fitnesses[i]) / x;
            }
            x++;
        }

        return avg;
    }
}
