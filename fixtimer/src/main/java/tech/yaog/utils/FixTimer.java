package tech.yaog.utils;

import android.util.Log;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

public class FixTimer implements Runnable {

    private static final String TAG = FixTimer.class.getName();

    static ThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(500);

    protected Runnable runnable;
    protected long delay;
    protected boolean isCanceled = true;
    protected String name = "";

    protected FixTimer() {

    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public static FixTimer newInstance() {
        return new FixTimer();
    }

    public FixTimer setName(String name) {
        this.name = name;
        return this;
    }

    public FixTimer setRunnable(Runnable runnable) {
        this.runnable = runnable;
        return this;
    }

    public FixTimer setDelay(long delay) {
        this.delay = delay;
        return this;
    }

    public FixTimer start() {
        Log.d(TAG, name+" start");
        cancelTimer();
        isCanceled = false;
        startTimer();
        return this;
    }

    public FixTimer cancel() {
        Log.d(TAG, name+" cancel");
        cancelTimer();
        isCanceled = true;
        return this;
    }

    protected void startTimer() {
        executor.submit(this);
        Log.d(TAG, name+" task schedule "+delay);
    }

    protected void cancelTimer() {
        executor.remove(this);
    }

    @Override
    public void run() {
        if (!Thread.interrupted()) {
            try {
                Thread.sleep(delay);
//                Log.d(TAG, name+" Timeout");
            } catch (InterruptedException e) {
                return;
            }
            if (isCanceled) {
//                Log.d(TAG, name+" Timeout canceled");
                return;
            }
            Log.d(TAG, name+" Timeout run");
            runnable.run();
        }
    }
}
