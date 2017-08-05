package me.ienze.caEvolve;

import java.util.HashMap;

/**
 * @author ienze.
 */
public class EmptyFitnessCalculator implements FitnessCalculator {

    private HashMap<CA, Double> calcualted;

    @Override
    public void calculateFitnesses(CA[] cas) {
        calcualted = new HashMap<>();
        for(CA ca : cas) {
            ca.get()
        }
    }

    @Override
    public double getFitness(CA ca) {
        return 0;
    }
}
