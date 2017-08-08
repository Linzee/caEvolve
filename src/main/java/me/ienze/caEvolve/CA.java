package me.ienze.caEvolve;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * @author ienze
 */
public interface CA {

    int get(BoardLocalState localState);

    Image getPreviewImage();

    void setPreviewImage(Image image);

    void setFitness(double fitness);

    double getFitness();
}
