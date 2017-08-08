package me.ienze.caEvolve.ui;

import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.CaEvolveSettings;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author ienze
 */
public class CaPreview extends JPanel {

    private CA ca;

    public CaPreview(CaEvolveSettings settings) {
        this.ca = ca;
        Dimension size = new Dimension(settings.boardWidth, settings.boardHeight + 12);
        setPreferredSize(size);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                CaPreviewGui caPreviewGui = new CaPreviewGui(settings, ca);
                caPreviewGui.pack();
                caPreviewGui.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(ca == null) {
            return;
        }

        if (ca.getPreviewImage() != null) {
            g.drawImage(ca.getPreviewImage(), 0, 0, this);
        }

        String percent = "" + (Math.round(ca.getFitness() * 100));
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString(percent, 0, getHeight());
    }

    public void setCa(CA ca) {
        this.ca = ca;
    }
}
