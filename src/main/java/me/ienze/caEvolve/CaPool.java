package me.ienze.caEvolve;

import me.ienze.twoDimMap.io.DistinctMapImageWriter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ienze
 */
public abstract class CaPool<C extends CA> {

    private final CaEvolveSettings settings;
    private final FitnessCalculator fitnessCalculator;
    private final CaEvolveUtils evolveUtils;
    private final DistinctMapImageWriter boardImageWriter;

    private int generation;

    protected C[] cas;
    private C[] oldCas;

    public CaPool(CaEvolveSettings settings) {
        this.settings = settings;
        this.fitnessCalculator = settings.fitnessCalculator;
        this.evolveUtils = new CaEvolveUtils(settings);
        this.boardImageWriter = new DistinctMapImageWriter();
        init();
    }

    abstract public void init();

    public void nextGeneration() {

        //simulate
        for (CA ca : cas) {
            Board board = new Board(settings);
            for (int i = 0; i < settings.boardSteps; i++) {
                board.step(ca);
            }
            ca.setResultBoard(board);
            ca.setPreviewImage(boardImageWriter.generateImage(board));
        }

        //calculate fitnesses
        fitnessCalculator.calculateFitnesses(cas);

        //semi sort by fitness
        Arrays.sort(cas, new Comparator<C>() {
            public int compare(C c1, C c2) {
                return Double.compare(c2.getFitness(), c1.getFitness());
            }
        });

        //new generation
        oldCas = Arrays.copyOf(cas, cas.length);

        //crossover
        for (int i = 0; i < settings.poolSize; i++) {
            C ca1 = oldCas[settings.random.nextInt(settings.poolSize / 2)];
            C ca2 = oldCas[settings.random.nextInt(settings.poolSize / 2)];
            cas[i] = (C) evolveUtils.crossover(ca1, ca2);
        }

        //mutate
        for (int i = 0; i < settings.poolSize; ++i) {
            if (settings.random.nextFloat() < settings.mutateCaChance) {
                evolveUtils.mutate(cas[i]);
            }
        }

        generation++;
        System.out.println("Generation " + generation);
    }

    public C[] getCas() {
        return cas;
    }

    public int getGeneration() {
        return generation;
    }

    public CA[] getOldCas() {
        return oldCas;
    }
}
