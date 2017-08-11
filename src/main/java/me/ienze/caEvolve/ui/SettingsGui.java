package me.ienze.caEvolve.ui;

import me.ienze.caEvolve.CaEvolveSettings;

import javax.swing.*;
import java.awt.event.*;

public class SettingsGui extends JDialog {

    private final CaEvolveSettings settings;

    private JPanel wrapperContainer;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel mainContainer;
    private JPanel bottomContainer;
    private JLabel labelSteps;
    private JSpinner spinnerSteps;
    private JSlider sliderMutateCA;
    private JSlider sliderMutateGenes;

    public SettingsGui(CaEvolveSettings settings) {
        this.settings = settings;

        setContentPane(wrapperContainer);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        wrapperContainer.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        spinnerSteps.setValue(settings.boardSteps);
        sliderMutateCA.setValue(Math.round(settings.mutateCaChance * 100));
        sliderMutateGenes.setValue(Math.round(settings.mutateGeneChance * 100));
    }

    private void onOK() {
        settings.boardSteps = (Integer) spinnerSteps.getValue();
        settings.mutateCaChance = sliderMutateCA.getValue() / 100.0f;
        settings.mutateGeneChance = sliderMutateGenes.getValue() / 100.0f;
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
