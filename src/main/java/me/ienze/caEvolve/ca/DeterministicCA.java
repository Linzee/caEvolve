package me.ienze.caEvolve.ca;

import me.ienze.caEvolve.BoardLocalState;
import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.CaEvolveSettings;

import java.awt.Image;

/**
 * @author ienze
 */
public class DeterministicCA implements CA {

    private final CaEvolveSettings settings;
    public int[] transitions;
    private double fitness;
    private Image previewImage;

    public DeterministicCA(CaEvolveSettings settings) {
        this.settings = settings;

        transitions = new int[settings.getPossibleStatesCount()];
        for(int i=0; i<settings.getPossibleStatesCount(); ++i) {
            transitions[i] = settings.random.nextInt(settings.stateCount);
        }
    }

    public int get(BoardLocalState localState) {
        return transitions[localState.getNumericRepresentation()];
    }

    @Override
    public Image getPreviewImage() {
        return previewImage;
    }

    @Override
    public void setPreviewImage(Image image) {
        previewImage = image;
    }

    @Override
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public double getFitness() {
        return fitness;
    }
}
