package me.ienze.caEvolve;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ienze
 */
public abstract class CaPool<C extends CA> {

    private CaEvolveSettings settings;
    private FitnessCalculator fitnessCalculator;
    private CaEvolveUtils evolveUtils;

    protected C[] cas;
    private int generation;

    public CaPool(CaEvolveSettings settings, FitnessCalculator fitnessCalculator) {
        this.settings = settings;
        this.fitnessCalculator = fitnessCalculator;
        this.evolveUtils = new CaEvolveUtils(settings);
        init();
    }

    abstract public void init();

    public void nextGeneration() {

        //calculate fitnesses
        fitnessCalculator.calculateFitnesses(cas);

        //semi sort by fitness
        Arrays.sort(cas, new Comparator<C>() {
            public int compare(C c1, C c2) {
                return Double.compare(c2.getFitness(), c1.getFitness());
            }
        });

        //new generation
        C[] oldGeneration = Arrays.copyOf(cas, cas.length);

        //crossover
        for (int i = 0; i < settings.poolSize; i++) {
            C ca1 = oldGeneration[settings.random.nextInt(settings.poolSize / 2)];
            C ca2 = oldGeneration[settings.random.nextInt(settings.poolSize / 2)];
            cas[i] = (C) evolveUtils.crossover(ca1, ca2);
        }

        //mutate
        for (int i = 0; i < settings.poolSize; ++i) {
            if(settings.random.nextFloat() < settings.mutateCaChance) {
                evolveUtils.mutate(cas[i]);
            }
        }

        generation++;
        System.out.println("Generation "+generation);
    }

    public C[] getCas() {
        return cas;
    }
}
