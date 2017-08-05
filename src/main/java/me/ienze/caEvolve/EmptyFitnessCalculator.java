package me.ienze.caEvolve;

import java.util.HashMap;

/**
 * @author ienze
 */
public class EmptyFitnessCalculator implements FitnessCalculator {

    private CaEvolveSettings settings;
    private HashMap<CA, Double> calculated;

    public EmptyFitnessCalculator(CaEvolveSettings settings) {
        this.settings = settings;
    }

    @Override
    public void calculateFitnesses(CA[] cas) {
        calculated = new HashMap<>();
        for(CA ca : cas) {

            Board board = new Board(settings);

            for (int i=0; i<10; i++) {
                board.step(ca);
            }

            double sum = 0;
            for(int x=0; x<board.getWidth(); x++) {
                for(int y=0; y<board.getHeight(); y++) {
                    sum += board.get(x, y);
                }
            }

            double fitness = sum / (board.getWidth() * board.getHeight());
            calculated.put(ca, fitness);
        }
    }

    @Override
    public double getFitness(CA ca) {
        return calculated.get(ca);
    }
}
