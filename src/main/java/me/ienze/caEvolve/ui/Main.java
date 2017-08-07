package me.ienze.caEvolve.ui;

import me.ienze.caEvolve.CaEvolve;

/**
 * @author ienze
 */
public class Main {
    public static void main(String[] args) {

        CaEvolve caEvolve = new CaEvolve();

        CaEvolveGui gui = new CaEvolveGui(caEvolve);

        gui.pack();
        gui.setVisible(true);
    }
}
