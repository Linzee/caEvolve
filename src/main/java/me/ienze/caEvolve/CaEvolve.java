package me.ienze.caEvolve;

import me.ienze.caEvolve.ca.DeterministicCA;
import me.ienze.caEvolve.ca.LifeCA;

/**
 * @author ienze
 */
public class CaEvolve {

    private boolean running;
    private Object runningLock = new Object();

    private CaEvolveSettings settings;
    private CaPool<DeterministicCA> pool;

    public CaEvolve() {
        init();

        startRunner();
    }

    private void startRunner() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    boolean isRunning;
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

        pool = new CaPool<DeterministicCA>(settings) {
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
        return settings.fitnessCalculator;
    }

    public CaPool<DeterministicCA> getPool() {
        return pool;
    }

    public CaEvolveSettings getSettings() {
        return settings;
    }
}
