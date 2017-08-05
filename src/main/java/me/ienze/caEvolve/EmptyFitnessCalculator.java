package me.ienze.caEvolve;

import me.ienze.twoDimMap.DistinctMapLayer;
import me.ienze.twoDimMap.io.DistinctMapImageWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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

        DistinctMapImageWriter boardImageWriter = new DistinctMapImageWriter();

        int caIndex=0;

        for(CA ca : cas) {

            Board board = new Board(settings);

            //simulate
            for (int i=0; i<10; i++) {
                board.step(ca);
            }

            //save result
            try {
                boardImageWriter.write(board, new File("out/"+caIndex+".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //calculate fitness
            double sum = 0;
            for(int x=0; x<board.getWidth(); x++) {
                for(int y=0; y<board.getHeight(); y++) {
                    sum += board.get(x, y);
                }
            }

            double fitness = sum / (board.getWidth() * board.getHeight());
            calculated.put(ca, fitness);

            caIndex++;
        }
    }

    @Override
    public double getFitness(CA ca) {
        return calculated.get(ca);
    }
}
