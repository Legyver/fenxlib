package com.legyver.fenxlib.core.api.util;

import javafx.application.Platform;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Execute the specified action on a delay.
 *
 * Note: This leverage JavaFX pulses to keep delaying the action until a specified delay has passed
 */
public class DelayedAction implements Runnable {
    /**
     * The action to run
     */
    private final Runnable runnable;
    /**
     * The time in millis to run the action after.
     */
    private final long timeMillis;

    /**
     * Construct a delayed action
     * @param runnable the action to run
     * @param delay the delay in milliseconds to wait before running
     */
    public DelayedAction(Runnable runnable, Long delay) {
        this.runnable = runnable;
        this.timeMillis = System.currentTimeMillis() + delay;
    }

    @Override
    public void run() {
        if (System.currentTimeMillis() > timeMillis) {
            runnable.run();
        } else {
            Platform.runLater(this::run);
        }
    }
}
