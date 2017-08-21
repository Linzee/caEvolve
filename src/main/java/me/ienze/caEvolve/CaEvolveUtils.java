package me.ienze.caEvolve;

import me.ienze.caEvolve.ca.DeterministicCA;
import me.ienze.caEvolve.ca.ProbabilisticCA;

/**
 * @author ienze
 */
public class CaEvolveUtils {

    private CaEvolveSettings settings;

    public CaEvolveUtils(CaEvolveSettings settings) {
        this.settings = settings;
    }

    public void mutate(DeterministicCA ca) {
        for (int i = 0; i < settings.getPossibleStatesCount(); i++) {
            if (settings.random.nextFloat() < settings.mutateGeneChance) {
                ca.getTransitions()[i] = settings.random.nextInt(settings.stateCount);
            }
        }
    }

    public DeterministicCA crossover(DeterministicCA ca1, DeterministicCA ca2) {
        DeterministicCA ca = new DeterministicCA(settings);

        for (int i = 0; i < settings.getPossibleStatesCount(); ++i) {
            ca.getTransitions()[i] = settings.random.nextBoolean() ? ca1.getTransitions()[i] : ca2.getTransitions()[i];
        }

        return ca;
    }

    public void mutate(ProbabilisticCA ca) {
        for (int i = 0; i < settings.getPossibleStatesCount(); i++) {
            if (settings.random.nextFloat() < settings.mutateGeneChance) {
                for (int j = 0; j < settings.stateCount; j++) {
                    if (settings.random.nextFloat() < settings.mutateGeneChance) {
                        ca.getTransitions()[i][j] = settings.random.nextFloat();
                    }
                }
            }
        }
    }

    public ProbabilisticCA crossover(ProbabilisticCA ca1, ProbabilisticCA ca2) {
        ProbabilisticCA ca = new ProbabilisticCA(settings);

        for (int i = 0; i < settings.getPossibleStatesCount(); i++) {
            for (int j = 0; j < settings.stateCount; j++) {
                ca.getTransitions()[i][j] = settings.random.nextBoolean() ? ca1.getTransitions()[i][j] : ca2.getTransitions()[i][j];
            }
        }

        return ca;
    }

    public void mutate(CA ca) {
        if (ca instanceof DeterministicCA) {
            mutate((DeterministicCA) ca);
        } else if (ca instanceof ProbabilisticCA) {
            mutate((ProbabilisticCA) ca);
        } else {
            throw new IllegalArgumentException("This type of CA is not supported");
        }
    }

    public CA crossover(CA ca1, CA ca2) {
        if (ca1 instanceof DeterministicCA && ca2 instanceof DeterministicCA) {
            return crossover((DeterministicCA) ca1, (DeterministicCA) ca2);
        } else if (ca1 instanceof ProbabilisticCA && ca2 instanceof ProbabilisticCA) {
            return crossover((ProbabilisticCA) ca1, (ProbabilisticCA) ca2);
        } else {
            throw new IllegalArgumentException("This type of CA is not supported");
        }
    }

}
