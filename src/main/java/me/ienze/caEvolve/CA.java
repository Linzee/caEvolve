package me.ienze.caEvolve;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * @author ienze
 */
public interface CA {

    int get(BoardLocalState localState);

    BufferedImage getPreviewImage();

    void setPreviewImage(BufferedImage image);

    double getFitness();

    void setFitness(double fitness);

    Board getResultBoard();

    void setResultBoard(Board board);
}
