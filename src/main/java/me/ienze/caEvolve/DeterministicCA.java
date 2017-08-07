package me.ienze.caEvolve;

import me.ienze.caEvolve.ui.CaPreview;

import java.awt.Image;

/**
 * @author ienze
 */
public class DeterministicCA implements CA {

    private final CaEvolveSettings settings;
    public int[] transitions;
    public Image previewImage;

    public DeterministicCA(CaEvolveSettings settings) {
        this.settings = settings;

        transitions = new int[settings.getPossibleStatesCount()];
        for(int i=0; i<settings.getPossibleStatesCount(); ++i) {
            transitions[i] = settings.random.nextInt(settings.stateCount);
        }
    }

    public int get(BoardLocalState localState) {
        return transitions[localState.getNumericRepresentation()];
    }

    @Override
    public Image getPreviewImage() {
        return previewImage;
    }

    @Override
    public void setPreviewImage(Image image) {
        previewImage = image;
    }
}
