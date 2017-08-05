package me.ienze.caEvolve;

/**
 * @author ienze
 */
class CaEvolve {

    public static void main(String[] args) {

        CaEvolveSettings settings = new CaEvolveSettings();

        FitnessCalculator fitnessCalculator = new EmptyFitnessCalculator(settings);

        CaPool<DeterministicCA> pool = new CaPool<DeterministicCA>(settings, fitnessCalculator) {
            @Override
            public void init() {
                cas = new DeterministicCA[settings.poolSize];
                for (int i=0; i<cas.length; i++) {
                    cas[i] = new DeterministicCA(settings);
                }
            }
        };

        while(true) {
            pool.nextGeneration();
        }
    }
}
