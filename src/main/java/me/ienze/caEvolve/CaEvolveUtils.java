package me.ienze.caEvolve;

/**
 * @author ienze.
 */
class CaEvolveUtils {

    private CaEvolveSettings settings;

    public CaEvolveUtils(CaEvolveSettings settings) {
        this.settings = settings;
    }

    public void mutate(DeterministicCA ca) {
        ca.transitions[settings.random.nextInt(settings.getPossibleStatesCount())] = settings.random.nextInt(settings.stateCount);
    }

    public void crossover(DeterministicCA ca1, DeterministicCA ca2) {
        for (int i = 0; i < settings.getPossibleStatesCount(); ++i) {
            if (settings.random.nextBoolean()) {
                int temp = ca1.transitions[i];
                ca1.transitions[i] = ca2.transitions[i];
                ca2.transitions[i] = temp;
            }
        }
    }

    public void mutate(CA ca) {
        if(ca instanceof DeterministicCA) {
            mutate((DeterministicCA) ca);
        }
        throw new IllegalArgumentException("This type of CA is not supported");
    }

    public void crossover(CA ca1, CA ca2) {
        if(ca1 instanceof DeterministicCA && ca2 instanceof DeterministicCA) {
            crossover((DeterministicCA) ca1, (DeterministicCA) ca2);
        }
        throw new IllegalArgumentException("This type of CA is not supported");
    }

}
