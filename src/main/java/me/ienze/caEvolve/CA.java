package me.ienze.caEvolve;

import java.awt.Image;

/**
 * @author ienze
 */
public interface CA {

    int get(BoardLocalState localState);

    Image getPreviewImage();

    void setPreviewImage(Image image);

    double getFitness();

    void setFitness(double fitness);

    Board getResultBoard();

    void setResultBoard(Board board);
}
