package me.ienze.caEvolve.ui;

import me.ienze.caEvolve.Board;
import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.CaEvolveSettings;
import me.ienze.twoDimMap.io.DistinctMapImageWriter;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;

public class CaPreviewGui extends JDialog {

    private final DistinctMapImageWriter distinctMapImageWriter = new DistinctMapImageWriter();

    private JPanel wrapperContainer;
    private JPanel mainContainer;
    private JPanel topContainer;
    private JLabel labelFitness;
    private JButton buttonRestart;
    private JPanel bottomContainer;
    private JButton buttonStep;
    private Image simulationImage;
    private Board simulationBoard;

    public CaPreviewGui(CaEvolveSettings settings, CA ca) {

        setContentPane(wrapperContainer);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        simulationImage = new Image(new Dimension(400, 400));
        mainContainer.add(simulationImage);

        simulationBoard = new Board(settings);
        redrawSimulationImage();

        buttonRestart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulationBoard = new Board(settings);
                redrawSimulationImage();
            }
        });

        buttonStep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simulationBoard.step(ca);
                redrawSimulationImage();
            }
        });

        labelFitness.setText("Fitness "+Math.round(ca.getFitness()*100));

        // call onCancel() on ESCAPE
        CaPreviewGui frame = this;
        wrapperContainer.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void redrawSimulationImage() {
        simulationImage.setImage(distinctMapImageWriter.generateImage(simulationBoard));
        simulationImage.repaint();
    }
}
