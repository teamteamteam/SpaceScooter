package de.teamteamteam.spacescooter.threads;

public abstract class TimingThread extends Thread {

	private long workInterval;

	/**
	 * This method sets the actual working interval based on hz.
	 * 
	 * @param hz
	 */
	public void setHz(int hz) {
		this.workInterval = (1000L * 1000L * 1000L) / (long) hz;
	}

	/**
	 * This method takes care of the timing.
	 */
	public void run() {
		while (true) {
			long workStart = System.nanoTime();

			// do the actual work
			this.work();

			long workDone = System.nanoTime();
			long workTime = (workDone - workStart);

			long timeToWait = this.workInterval - workTime;
			long msToWait = timeToWait / 1000000;

			// wait using sleep for bigger intervals
			if (msToWait > 1) {
				try {
					Thread.sleep(msToWait);
				} catch (InterruptedException e) {
					System.err.println("[" + this.getName() + "]" + e.getStackTrace());
				}
			}

			while (this.workInterval - (System.nanoTime() - workStart) > 100);
			//System.out.println("[" + this.getName() + "]: "+ (System.nanoTime() - workStart));
		}
	}

	/**
	 * Do the actual thread work in here.
	 */
	public abstract void work();

}
