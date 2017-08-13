package me.ienze.caEvolve.ca;

import me.ienze.caEvolve.CaEvolveSettings;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author ienze
 */
public class DeterministicCAReader {

    private final CaEvolveSettings settings;

    public DeterministicCAReader(CaEvolveSettings settings) {
        this.settings = settings;
    }

    public DeterministicCA read(InputStream stream) throws IOException {
        DataInputStream in = new DataInputStream(stream);

        DeterministicCA ca = new DeterministicCA(settings);

        for(int i=0; i<settings.getPossibleStatesCount(); i++) {
            ca.getTransitions()[i] = in.readInt();
        }

        return ca;
    }

}
