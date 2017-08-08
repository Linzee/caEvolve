package me.ienze.caEvolve.ui;

import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.CaEvolve;
import me.ienze.caEvolve.DeterministicCA;
import me.ienze.caEvolve.EmptyFitnessCalculator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Component;
import java.awt.event.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class CaEvolveGui extends JFrame {

    private final CaEvolve caEvolve;
    private JPanel wrapperContainer;
    private JPanel topContainer;
    private JButton buttonRestart;
    private JButton buttonNextGeneration;
    private JCheckBox checkBoxRun;
    private JScrollPane mainContainerScroll;
    private JPanel mainContainer;

    public CaEvolveGui(CaEvolve caEvolve) {
        this.caEvolve = caEvolve;

        setTitle("CaEvolve");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(wrapperContainer);

        buttonRestart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                caEvolve.init();
                initCaPreviews();
            }
        });

        buttonNextGeneration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                caEvolve.getPool().nextGeneration();
            }
        });

        checkBoxRun.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                caEvolve.setRunning(checkBoxRun.isSelected());
            }
        });

        initCaPreviews();

        ActionListener timerTaskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                CA[] calculated = ((EmptyFitnessCalculator) caEvolve.getFitnessCalculator()).getLastCalculated();
                if(calculated == null) {
                    return;
                }
                Iterator<CA> cas = Arrays.stream(calculated).iterator();

                for(Component c : mainContainer.getComponents()) {

                    if(!cas.hasNext()) {
                        break;
                    }

                    CA ca = cas.next();
                    ((CaPreview) c).setCa(ca);
                    c.repaint();
                }
            }
        };
        Timer timer = new Timer(1000, timerTaskPerformer);
        timer.setRepeats(true);
        timer.start();
    }

    private void initCaPreviews() {
        mainContainer.removeAll();

        for(CA ca : caEvolve.getPool().getCas()) {
            mainContainer.add(new CaPreview(caEvolve.getSettings(), ca));
        }

        mainContainer.revalidate();
        mainContainer.repaint();
    }

}
