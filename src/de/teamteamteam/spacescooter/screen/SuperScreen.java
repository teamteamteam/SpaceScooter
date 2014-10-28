package de.teamteamteam.spacescooter.screen;

import java.awt.Graphics;

public class SuperScreen extends Screen {

	public SuperScreen(Screen parent) {
		super(null);
		this.overlay = new MainMenuScreen(this);
	}

	@Override
	public void paint(Graphics g) {
		//nothing to paint, we're so meta meta.
	}

	@Override
	public void update() {
		//dummy method
	}

}
