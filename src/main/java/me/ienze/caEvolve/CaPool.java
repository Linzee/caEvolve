package me.ienze.caEvolve;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ienze.
 */
class CaPool<C extends CA> {

    private CaEvolveSettings settings;
    private FitnessCalculator fitnessCalculator;
    private CaEvolveUtils evolveUtils;
    private C[] cas;

    public CaPool(CaEvolveSettings settings, FitnessCalculator fitnessCalculator) {
        this.settings = settings;
        this.fitnessCalculator = fitnessCalculator;
        this.evolveUtils = new CaEvolveUtils(settings);
        this.cas = (C[]) new Object[settings.poolSize];
    }

    void nextGeneration() {

        //calculate fitnesses
        fitnessCalculator.calculateFitnesses(cas);

        //semi sort by fitness
        Arrays.sort(cas, new Comparator<C>() {
            public int compare(C c1, C c2) {
                if (settings.random.nextBoolean()) {
                    return Double.compare(fitnessCalculator.getFitness(c1), fitnessCalculator.getFitness(c2));
                } else {
                    return 0;
                }
            }
        });

        //new generation
        C[] oldGeneration = Arrays.copyOf(cas, cas.length);

        //crossover
        //TODO is this right?
        for (int i = 0; i < settings.poolSize; i += 2) {
            C ca1 = oldGeneration[settings.random.nextInt(settings.poolSize)];
            C ca2 = oldGeneration[settings.random.nextInt(settings.poolSize)];
            evolveUtils.crossover(ca1, ca2);
            cas[i] = ca1;
            cas[i + 1] = ca2;
        }

        //mutate
        for (int i = 0; i < settings.poolSize; ++i) {
            if (settings.random.nextInt(10) == 0) {
                evolveUtils.mutate(cas[i]);
            }
        }
    }


}
