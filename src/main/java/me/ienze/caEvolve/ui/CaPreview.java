package me.ienze.caEvolve.ui;

import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.CaEvolveSettings;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

/**
 * @author ienze
 */
public class CaPreview extends JPanel {

    private CA ca;

    public CaPreview(CaEvolveSettings settings, CA ca) {
        this.ca = ca;
        Dimension size = new Dimension(settings.boardWidth, settings.boardHeight + 12);
        setPreferredSize(size);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(ca.getPreviewImage() != null) {
            g.drawImage(ca.getPreviewImage(), 0, 0, this);
        }

        String percent = ""+(Math.round(ca.getFitness() * 100));
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString(percent, 0, getHeight());
    }

    public void setCa(CA ca) {
        this.ca = ca;
    }
}
