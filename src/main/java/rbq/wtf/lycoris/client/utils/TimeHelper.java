package rbq.wtf.lycoris.client.utils;

public class TimeHelper {
	public long lastMs;

	public TimeHelper() {
		this.lastMs = 0L;
	}

	public boolean isDelayComplete(final long delay) {

		return System.currentTimeMillis() - this.lastMs > delay;
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

	public void setLastMs(final int i) {
		this.lastMs = System.currentTimeMillis() + i;
	}

	public boolean hasReached(final long milliseconds) {
		return this.getCurrentMS() - this.lastMs >= milliseconds;
	}

	public boolean isDelayComplete(float delay) {
		return System.currentTimeMillis() - this.lastMs > delay;
	}

	public boolean isDelayComplete(Double delay) {
		return System.currentTimeMillis() - this.lastMs > delay;
	}
}

