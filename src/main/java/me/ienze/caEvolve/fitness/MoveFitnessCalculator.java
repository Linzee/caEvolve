package me.ienze.caEvolve.fitness;

import me.ienze.caEvolve.*;

import java.util.Arrays;

/**
 * @author ienze
 */
public class MoveFitnessCalculator implements FitnessCalculator {

    private final CaEvolveSettings settings;
    private int idealDirectionX;
    private int idealDirectionY;

    public MoveFitnessCalculator(CaEvolveSettings settings, Direction...idealDirection) {
        this.settings =  settings;
        this.idealDirectionX = Arrays.stream(idealDirection).reduce((Integer)0, (acc, direction) -> acc + direction.getDx(), (a, b) -> a+b);
        this.idealDirectionY = Arrays.stream(idealDirection).reduce((Integer)0, (acc, direction) -> acc + direction.getDy(), (a, b) -> a+b);
    }

    @Override
    public double[] calculateFitnesses(CA[] cas) {

        double[] fitnesses = new double[cas.length];

        for (int i = 0; i < cas.length; i++) {

            CA ca = cas[i];

            Board board = new Board(settings);
            Board oldBoard = new Board(board);

            board.step(ca);

            double sum = 0;
            for (BoardLocalState localState : board) {
                sum += (board.get(localState.getX(), localState.getY()).equals(oldBoard.get(localState.getX() + idealDirectionX, localState.getY() + idealDirectionY)) ? 1 : 0);
            }

            double fitness = (sum / (board.getWidth() * board.getHeight()));
            fitnesses[i] = fitness;
        }

        return fitnesses;
    }

    public enum Direction {
        NONE (0, 0),
        UP (0, 1),
        RIGHT (1, 0),
        DOWN (0, -1),
        LEFT (-1, 0);

        private final int dx;
        private final int dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        public int getDx() {
            return dx;
        }

        public int getDy() {
            return dy;
        }
    }
}
