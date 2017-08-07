package me.ienze.caEvolve;

/**
 * @author ienze
 */
public class CaEvolveUtils {

    private CaEvolveSettings settings;

    public CaEvolveUtils(CaEvolveSettings settings) {
        this.settings = settings;
    }

    public void mutate(DeterministicCA ca) {
        for(int i=0; i<settings.getPossibleStatesCount(); i++) {
            if(settings.random.nextFloat() < settings.mutateGeneChance) {
                ca.transitions[i] = settings.random.nextInt(settings.stateCount);
            }
        }
    }

    public DeterministicCA crossover(DeterministicCA ca1, DeterministicCA ca2) {
        DeterministicCA ca = new DeterministicCA(settings);

        for (int i = 0; i < settings.getPossibleStatesCount(); ++i) {
            ca.transitions[i] = settings.random.nextBoolean() ? ca1.transitions[i] : ca2.transitions[i];
        }

        return ca;
    }

    public void mutate(CA ca) {
        if(ca instanceof DeterministicCA) {
            mutate((DeterministicCA) ca);
        } else {
            throw new IllegalArgumentException("This type of CA is not supported");
        }
    }

    public CA crossover(CA ca1, CA ca2) {
        if(ca1 instanceof DeterministicCA && ca2 instanceof DeterministicCA) {
            return crossover((DeterministicCA) ca1, (DeterministicCA) ca2);
        } else {
            throw new IllegalArgumentException("This type of CA is not supported");
        }
    }

}
