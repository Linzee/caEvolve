package me.ienze.caEvolve.ca;

import me.ienze.caEvolve.Board;
import me.ienze.caEvolve.BoardLocalState;
import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.CaEvolveSettings;

import java.awt.Image;
import java.util.Arrays;

/**
 * @author ienze
 */
public class ProbabilisticCA implements CA {

    private double[][] transitions;
    private double fitness;
    private Board resultBoard;
    private Image previewImage;

    public ProbabilisticCA(CaEvolveSettings settings) {
        transitions = new double[settings.getPossibleStatesCount()][settings.stateCount];
        for (int i = 0; i < settings.getPossibleStatesCount(); ++i) {
            for (int j = 0; j < settings.stateCount; ++j) {
                transitions[i][j] = settings.random.nextFloat();
            }
        }
    }

    public int get(BoardLocalState localState) {
        double[] probabilities = transitions[localState.getNumericRepresentation()];
        double probabilitiesSum = Arrays.stream(probabilities).sum();

        double p = Math.random() * probabilitiesSum;
        double cp = 0.0;
        for (int i=0; i<probabilities.length; i++) {
            cp += probabilities[i];
            if (p <= cp) {
                return i;
            }
        }
        return probabilities.length-1;
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

    public double[][] getTransitions() {
        return transitions;
    }
}
