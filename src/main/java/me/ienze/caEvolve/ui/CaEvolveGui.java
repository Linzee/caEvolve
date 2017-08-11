package me.ienze.caEvolve.ui;

import me.ienze.caEvolve.CA;
import me.ienze.caEvolve.CaEvolve;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
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
    private JLabel labelGeneration;
    private JButton buttonSettings;

    public CaEvolveGui(CaEvolve caEvolve) {
        this.caEvolve = caEvolve;

        setTitle("CaEvolve");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 500));

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

        buttonSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SettingsGui settingsGui = new SettingsGui(caEvolve.getSettings());
                settingsGui.pack();
                settingsGui.setVisible(true);
            }
        });

        checkBoxRun.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                caEvolve.setRunning(checkBoxRun.isSelected());
            }
        });

        initCaPreviews();

        mainContainer.setLayout(new WrapLayout());

        ActionListener timerTaskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                //Update CA previews
                CA[] calculated = caEvolve.getPool().getOldCas();
                if (calculated == null) {
                    return;
                }
                Iterator<CA> cas = Arrays.stream(calculated).iterator();

                for (Component c : mainContainer.getComponents()) {

                    if (!cas.hasNext()) {
                        break;
                    }

                    CA ca = cas.next();
                    ((CaPreview) c).setCa(ca);
                    c.repaint();
                }

                //Update generation counter
                labelGeneration.setText("Generation " + caEvolve.getPool().getGeneration());
            }
        };
        Timer timer = new Timer(3300, timerTaskPerformer);
        timer.setRepeats(true);
        timer.start();

    }

    private void initCaPreviews() {
        mainContainer.removeAll();

        for (int i=0; i<caEvolve.getSettings().poolSize; i++) {
            mainContainer.add(new CaPreview(caEvolve.getSettings()));
        }

        mainContainer.revalidate();
        mainContainer.repaint();
    }

}
