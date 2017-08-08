package me.ienze.caEvolve.ui;

import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.CaEvolveSettings;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * @author ienze
 */
public class Image extends JPanel {

    private java.awt.Image image;

    public Image(Dimension size) {
        setPreferredSize(size);
    }

    public Image(java.awt.Image image) {
        setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(),this);
        }
    }

    public java.awt.Image getImage() {
        return image;
    }

    public void setImage(java.awt.Image image) {
        this.image = image;
    }
}
