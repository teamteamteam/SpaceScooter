package de.teamteamteam.spacescooter.thread;

import de.teamteamteam.spacescooter.screen.Screen;

public class UpdateThread extends TimedThread {
		
	private Screen superScreen;

	public UpdateThread() {
		this.setName("UpdateThread");
	}
	
	public void setSuperScreen(Screen superScreen) {
		this.superScreen = superScreen;
	}
	
	public void work() {
		this.superScreen.doUpdate();
	}

}
