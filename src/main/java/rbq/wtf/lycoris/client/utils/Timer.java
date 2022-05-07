package rbq.wtf.lycoris.client.utils;

public class Timer {
    public long lastMs = 0L;
    private long time;

    public boolean isDelayComplete(long delay) {
        if (System.currentTimeMillis() - this.lastMs > delay) {
            return true;
        }
        return false;
    }
    public boolean sleep(final long time) {
        if ((this.getCurrentMS() - this.lastMs) >= time) {
            reset();
            return true;
        }
        return false;
    }
    public boolean sleep(final float time) {
        if ((float)(this.getCurrentMS() - this.lastMs) >= time) {
            reset();
            return true;
        }
        return false;
    }
    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }

    public void reset() {
        this.lastMs = System.currentTimeMillis();
    }

    public long getLastMs() {
        return this.lastMs;
    }

    public void setLastMs(int i) {
        this.lastMs = System.currentTimeMillis() + (long)i;
    }

    public boolean hasReached(long milliseconds) {
        return this.getCurrentMS() - this.lastMs >= milliseconds;
    }

    public boolean hasReached(float timeLeft) {
        return (float)(this.getCurrentMS() - this.lastMs) >= timeLeft;
    }

    public boolean reach(final long time) {
        return time() >= time;
    }

    public long time() {
        return System.nanoTime() / 1000000L - time;
    }

    public boolean delay(double nextDelay) {
        return (double)(System.currentTimeMillis() - this.lastMs) >= nextDelay;
    }










}
