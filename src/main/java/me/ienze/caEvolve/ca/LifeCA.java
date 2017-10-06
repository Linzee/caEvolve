package me.ienze.caEvolve.ca;

import me.ienze.caEvolve.Board;
import me.ienze.caEvolve.BoardLocalState;
import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.CaEvolveSettings;

import java.awt.image.BufferedImage;

/**
 * @author ienze
 */
public class LifeCA implements CA {

    private final CaEvolveSettings settings;
    private int[] transitions;
    private double fitness;
    private Board resultBoard;
    private BufferedImage previewImage;

    public LifeCA(CaEvolveSettings settings) {
        this.settings = settings;

        transitions = new int[(settings.visibleRadius * 2 + 1) * (settings.visibleRadius * 2 + 1)];

        for (int i = 0; i < transitions.length; ++i) {
            transitions[i] = settings.random.nextBoolean() ? -1 : settings.random.nextInt(settings.stateCount);
        }
    }

    public int get(BoardLocalState localState) {

        int count = 0;
        int myState = localState.getBoard().get(localState.getX(), localState.getY());

        for (int dx = -settings.visibleRadius; dx <= settings.visibleRadius; dx++) {
            for (int dy = -settings.visibleRadius; dy <= settings.visibleRadius; dy++) {
                if (dx == 0 && dy == 0)
                    continue;
                if (localState.getBoard().get(localState.getX() + dx, localState.getY() + dy) == 1)
                    count++;
            }
        }

        return transitions[count] == -1 ? myState : transitions[count];
    }

    @Override
    public BufferedImage getPreviewImage() {
        return previewImage;
    }

    @Override
    public void setPreviewImage(BufferedImage image) {
        previewImage = image;
    }

    @Override
    public Board getResultBoard() {
        return resultBoard;
    }

    @Override
    public void setResultBoard(Board resultBoard) {
        this.resultBoard = resultBoard;
    }

    @Override
    public double getFitness() {
        return fitness;
    }

    @Override
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int[] getTransitions() {
        return transitions;
    }
}
