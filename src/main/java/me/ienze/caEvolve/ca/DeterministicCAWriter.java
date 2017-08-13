package me.ienze.caEvolve.ca;

import me.ienze.caEvolve.CaEvolveSettings;

import java.io.*;

/**
 * @author ienze
 */
public class DeterministicCAWriter {

    private final CaEvolveSettings settings;

    public DeterministicCAWriter(CaEvolveSettings settings) {
        this.settings = settings;
    }

    public void write(OutputStream stream, DeterministicCA ca) throws IOException {
        DataOutputStream out = new DataOutputStream(stream);

        for(int i=0; i<settings.getPossibleStatesCount(); i++) {
            out.writeInt(ca.getTransitions()[i]);
        }
    }

}
