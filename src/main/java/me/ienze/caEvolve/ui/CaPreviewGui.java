package me.ienze.caEvolve.ui;

import me.ienze.caEvolve.Board;
import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.CaEvolveSettings;
import me.ienze.caEvolve.ca.DeterministicCA;
import me.ienze.caEvolve.ca.DeterministicCAWriter;
import me.ienze.twoDimMap.io.DistinctMapImageWriter;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CaPreviewGui extends JDialog {

    private final DistinctMapImageWriter distinctMapImageWriter = new DistinctMapImageWriter();

    private JPanel wrapperContainer;
    private JPanel mainContainer;
    private JPanel topContainer;
    private JLabel labelFitness;
    private JButton buttonRestart;
    private JPanel bottomContainer;
    private JButton buttonStep;
    private JButton buttonSave;
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

        buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeterministicCAWriter dcaw = new DeterministicCAWriter(settings);
                try(OutputStream out = new FileOutputStream("latest.ca")) {
                    dcaw.write(out, (DeterministicCA) ca);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
