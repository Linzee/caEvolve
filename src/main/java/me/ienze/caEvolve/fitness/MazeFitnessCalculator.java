package me.ienze.caEvolve.fitness;

import me.ienze.caEvolve.Board;
import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.FitnessCalculator;

/**
 * @author ienze
 */
public class MazeFitnessCalculator implements FitnessCalculator {


    @Override
    public double[] calculateFitnesses(CA[] cas) {

        double[] fitnesses = new double[cas.length];

        for (int i = 0; i < cas.length; i++) {
            CA ca = cas[i];

            Board board = ca.getResultBoard();

            //calculate fitness
            int count1total = 0, count3total = 0, countXtotal = 0, countYtotal = 0;
            double sum = 0;
            for (int x = 0; x < board.getWidth(); x++) {
                for (int y = 0; y < board.getHeight(); y++) {

                    int countX0 = 0, countX1 = 0, countY0 = 0, countY1 = 0, count = 0;

                    if(board.get(x, y) == 1) {
                        count++; countX0++; countY0++;
                    }
                    if(board.get(x+1, y) == 1) {
                        count++; countX1++; countY0++;
                    }
                    if(board.get(x, y + 1) == 1) {
                        count++; countX0++; countY1++;
                    }
                    if(board.get(x + 1, y + 1) == 1) {
                        count++; countX1++; countY1++;
                    }

//                    sum += (count == 1 || count == 3 || (count == 2 && (countX0 == 2 || countX1 == 2 || countY0 == 2 || countY1 == 2))) ? 1 : 0;

                    if(count == 1) {
                        count1total += 1;
                    } else if(count == 3) {
                        count3total += 1;
                    } else if(count == 2 && (countX0 == 2 || countX1 == 2)) {
                        countXtotal += 1;
                    } else if(count == 2 && (countY0 == 2 || countY1 == 2)) {
                        countYtotal += 1;
                    } else {
                        sum += 1;
                    }
                }
            }

            for(double d1 : new double[]{count1total, count3total, countXtotal, countYtotal}) {
                for(double d2 : new double[]{count1total, count3total, countXtotal, countYtotal}) {
//                    sum += Math.abs(d1 - d2);
                }
            }

            double fitness = 1 - (sum / (board.getWidth() * board.getHeight()));
            fitnesses[i] = fitness;
        }

        return fitnesses;
    }
}
