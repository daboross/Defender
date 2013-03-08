package net.daboross.defender;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daboross
 */
public class DefenderThread implements Runnable {

    private final Thread currentThread;
    private final DefenderMain main;
    private final Object waitingObject;
    private boolean run;

    public DefenderThread(DefenderMain main) {
        waitingObject = new Object();
        currentThread = new Thread(this, "DefenderThread");
        this.main = main;
    }

    public void notifyThread() {
        synchronized (waitingObject) {
            waitingObject.notifyAll();
        }
    }

    /**
     * Do Not Call This Method!!!!.
     */
    @Override
    public void run() {
        run = true;
        main.beforeRun();
        while (true) {
            main.runLoop();
            synchronized (waitingObject) {
                try {
                    waitingObject.wait(20);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DefenderThread.class.getName()).log(Level.SEVERE, "Caught in DefenderThread", ex);
                }
            }
            if (!run) {
                break;
            }
        }
        main.afterRun();
    }

    protected void start() {
        if (!currentThread.isAlive()) {
            if (currentThread.isInterrupted()) {
            } else {
                currentThread.start();
            }
        }
    }

    protected void stop() {
        if (currentThread.isAlive()) {
            run = false;
            notifyThread();
        }
    }
}
