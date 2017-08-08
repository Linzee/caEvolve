package me.ienze.caEvolve.fitness;

import me.ienze.caEvolve.Board;
import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.CaEvolveSettings;
import me.ienze.caEvolve.FitnessCalculator;
import me.ienze.twoDimMap.io.DistinctMapImageWriter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author ienze
 */
public class SimillarStateFitnessCalculator implements FitnessCalculator {

    private CaEvolveSettings settings;
    private CA[] lastCalculated;

    public SimillarStateFitnessCalculator(CaEvolveSettings settings) {
        this.settings = settings;
    }

    @Override
    public void calculateFitnesses(CA[] cas) {
        lastCalculated = Arrays.copyOf(cas, cas.length);

        DistinctMapImageWriter boardImageWriter = new DistinctMapImageWriter();

        int caIndex=0;

        for(CA ca : cas) {

            Board board = new Board(settings);

            //simulate
            for (int i=0; i<settings.boardSteps; i++) {
                board.step(ca);
            }

            //save result
            try {
                boardImageWriter.write(board, new File("out/"+caIndex+".png"));
                ca.setPreviewImage(boardImageWriter.generateImage(board));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //calculate fitness
            double sum = 0;
            for(int x=0; x<board.getWidth(); x++) {
                for(int y=0; y<board.getHeight(); y++) {
                    sum += Math.abs(board.get(x, y) - board.get(x+1, y)) == 1 ? 1 : 0;
                    sum += Math.abs(board.get(x, y) - board.get(x-1, y)) == 1 ? 1 : 0;
                    sum += Math.abs(board.get(x, y) - board.get(x, y+1)) == 1 ? 1 : 0;
                    sum += Math.abs(board.get(x, y) - board.get(x, y-1)) == 1 ? 1 : 0;
                }
            }


            double fitness = (sum / (4 * board.getWidth() * board.getHeight()));
            ca.setFitness(fitness);

            caIndex++;
        }
    }

    public CA[] getLastCalculated() {
        return lastCalculated;
    }
}
