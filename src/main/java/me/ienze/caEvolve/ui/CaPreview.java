package me.ienze.caEvolve.ui;

import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.CaEvolveSettings;
import me.ienze.caEvolve.DeterministicCA;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * @author ienze
 */
public class CaPreview extends JPanel {

    private CA ca;

    public CaPreview(CaEvolveSettings settings, CA ca) {
        this.ca = ca;
        Dimension size = new Dimension(settings.boardWidth, settings.boardHeight);
        setPreferredSize(size);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(ca.getPreviewImage() != null) {
            g.drawImage(ca.getPreviewImage(), 0, 0, this);
        }
    }

    public void setCa(CA ca) {
        this.ca = ca;
    }
}
