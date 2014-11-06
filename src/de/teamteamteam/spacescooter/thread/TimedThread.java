package de.teamteamteam.spacescooter.thread;

/**
 * Since things like drawing the next image or triggering the next game tick
 * need to happen in time, this TimedThread allows more precise timing
 * combined with a nap for the cpu in case there is enough time for it.
 */
public abstract class TimedThread extends Thread {

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
			//calculate time of work
			long workTime = (workDone - workStart);

			long timeToWait = this.workInterval - workTime;
			long msToWait = timeToWait / 1000000;

			// wait using sleep for bigger intervals
			if (msToWait > 1) {
				try {
					//make sure there is a little buffer for manual waiting loop left
					Thread.sleep(Math.max(0,(msToWait - 1)));
				} catch (InterruptedException e) {
					System.err.println("[" + this.getName() + "]" + e.getStackTrace());
				}
			}
			
			//wait manually for the rest of the interval
			long sleepUntil = workStart + this.workInterval;
			while ((sleepUntil- System.nanoTime()) > 0) {
				if((sleepUntil- System.nanoTime()) > 5000) {
					Thread.yield(); //Give other threads a chance.
				}
			}
		}
	}

	/**
	 * Do the actual thread work in here.
	 */
	public abstract void work();

}
