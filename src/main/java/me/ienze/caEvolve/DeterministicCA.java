package me.ienze.caEvolve;

/**
 * @author ienze.
 */
class DeterministicCA implements CA {

    private final CaEvolveSettings settings;
    public int[] transitions;

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
}
