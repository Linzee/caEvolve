package me.ienze.caEvolve;

import me.ienze.caEvolve.ca.LifeCA;

/**
 * @author ienze
 */
public class CaEvolve {

    private boolean running;
    private Object runningLock = new Object();

    private CaEvolveSettings settings;
    private CaPool<LifeCA> pool;

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

        pool = new CaPool<LifeCA>(settings) {
            @Override
            public void init() {
                cas = new LifeCA[settings.poolSize];
                for (int i = 0; i < cas.length; i++) {
                    cas[i] = new LifeCA(settings);
                }
            }
        };
    }

    public FitnessCalculator getFitnessCalculator() {
        return settings.fitnessCalculator;
    }

    public CaPool<LifeCA> getPool() {
        return pool;
    }

    public CaEvolveSettings getSettings() {
        return settings;
    }
}
