package de.teamteamteam.spacescooter.thread;

/**
 * Since things like drawing the next image or triggering the next game tick
 * need to happen in time, this TimedThread allows more precise timing
 * combined with a nap for the cpu in case there is enough time for it.
 */
public abstract class TimedThread extends Thread {

	/**
	 * Internal interval in which to trigger work().
	 * Calculated based on setHz()
	 */
	private long workInterval;

	/**
	 * Internal value representing runtime of the last work() call.
	 */
	private long workTime;
	
	/**
	 * This is a quick hack :)
	 */
	private long runloops; 
	
	/**
	 * This method sets the actual working interval based on hz.
	 * 
	 * @param hz
	 */
	public final void setHz(int hz) {
		this.workInterval = (1000L * 1000L * 1000L) / (long) hz;
	}

	/**
	 * This method takes care of the timing.
	 */
	public final void run() {
		while (true) {
			this.runloops++;
			long workStart = System.nanoTime();
			// do the actual work
			this.work();
			long workDone = System.nanoTime();
			//calculate time of work
			this.workTime = (workDone - workStart);

			long timeToWait = this.workInterval - workTime;
			//in case we are already running late, just print a warning and carry on!
			if(timeToWait < 0 && this.runloops > 50) { // runloops for filtering out game start delays
				System.err.println("[" + this.getName() + "] workTime exceeds workInterval!:" + this.workTime + " > " + this.workInterval);
				runloops = 100; // overflow protect 
				continue;
			}
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
			long currentTime = System.nanoTime();
			while ((sleepUntil - currentTime) > 10) {
				if((sleepUntil - currentTime) > 10000) {
					Thread.yield(); //Give other threads a chance.
				}
				currentTime = System.nanoTime();
			}
		}
	}

	/**
	 * Do the actual thread work in here.
	 */
	public abstract void work();
	
	/**
	 * Returns current value of workTime.
	 * Tells how many nanoseconds the last work() call needed to complete.
	 */
	public long getWorkTime() {
		return this.workTime;
	}

}
