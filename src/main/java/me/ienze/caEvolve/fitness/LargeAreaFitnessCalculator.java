package me.ienze.caEvolve.fitness;

import me.ienze.caEvolve.Board;
import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.FitnessCalculator;
import me.ienze.twoDimMap.Vec;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author ienze
 */
public class LargeAreaFitnessCalculator implements FitnessCalculator {

    private final Random random = new Random();
    private int randomPointsCount = 10;

    @Override
    public double[] calculateFitnesses(CA[] cas) {

        double[] fitnesses = new double[cas.length];

        for (int i = 0; i < cas.length; i++) {

            CA ca = cas[i];

            Board board = ca.getResultBoard();

            double sum = 0;
            for(int j=0; j<randomPointsCount; j++) {

                Vec point = new Vec(random.nextInt(board.getWidth()), random.nextInt(board.getHeight()));
                int pointColor = board.get(point.x, point.y);

                Set<Vec> visited = new HashSet<Vec>();
                Set<Vec> toVisit = new HashSet<Vec>();
                toVisit.add(point);

                while(!toVisit.isEmpty()) {
                    Vec vec = toVisit.iterator().next();

                    toVisit.remove(vec);
                    visited.add(vec);

                    Vec[] toCheck = new Vec[] {
                            new Vec(Math.floorMod(vec.x + 1, board.getWidth()), Math.floorMod(vec.y, board.getWidth())),
                            new Vec(Math.floorMod(vec.x - 1, board.getWidth()), Math.floorMod(vec.y, board.getWidth())),
                            new Vec(Math.floorMod(vec.x, board.getWidth()), Math.floorMod(vec.y + 1, board.getWidth())),
                            new Vec(Math.floorMod(vec.x, board.getWidth()), Math.floorMod(vec.y - 1, board.getWidth()))
                    };

                    for (Vec checkVec : toCheck) {
                        if(board.get(checkVec.x, checkVec.y) != pointColor) {
                            continue;
                        }
                        if(visited.contains(checkVec)) {
                            continue;
                        }
                        toVisit.add(checkVec);
                    }
                }

                sum += visited.size();
            }

            fitnesses[i] = sum / (randomPointsCount * board.getWidth() * board.getHeight());
        }

        return fitnesses;
    }
}
