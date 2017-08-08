package me.ienze.caEvolve;

import me.ienze.caEvolve.fitness.ChessFitnessCalculator;
import me.ienze.caEvolve.fitness.EmptyFitnessCalculator;
import me.ienze.caEvolve.fitness.NeighborFitnessCalculator;

/**
 * @author ienze
 */
public class CaEvolve {

    private boolean running;
    private Object runningLock = new Object();

    CaEvolveSettings settings;
    FitnessCalculator fitnessCalculator;
    CaPool<DeterministicCA> pool;

    public CaEvolve() {
        init();

        startRunner();
    }

    private void startRunner() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    boolean isRunning = true;
                    synchronized (runningLock) {
                        isRunning = running;
                    }

                    if (isRunning) {
                        pool.nextGeneration();
                    } else {
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        new Thread(runnable).start();
    }

    public boolean getRunning() {
        synchronized (runningLock) {
            return running;
        }
    }

    public void setRunning(boolean value) {
        synchronized (runningLock) {
            running = value;
        }
    }

    public void init() {
        setRunning(false);

        settings = new CaEvolveSettings();

        fitnessCalculator = new NeighborFitnessCalculator(settings);

        pool = new CaPool<DeterministicCA>(settings, fitnessCalculator) {
            @Override
            public void init() {
                cas = new DeterministicCA[settings.poolSize];
                for (int i = 0; i < cas.length; i++) {
                    cas[i] = new DeterministicCA(settings);
                }
            }
        };
    }

    public FitnessCalculator getFitnessCalculator() {
        return fitnessCalculator;
    }

    public CaPool<DeterministicCA> getPool() {
        return pool;
    }

    public CaEvolveSettings getSettings() {
        return settings;
    }
}
