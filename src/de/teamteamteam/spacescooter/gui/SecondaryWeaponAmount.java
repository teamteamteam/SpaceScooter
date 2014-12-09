package de.teamteamteam.spacescooter.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.brain.PlayerSession;
import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.screen.GameScreen;

public class SecondaryWeaponAmount extends Entity{
	
	public SecondaryWeaponAmount(int x, int y) {
		super(x, y);
		if(PlayerSession.getSecondsecondaryWeapon() == 1){
			new ImageEntity(this.getX(), this.getY(), "images/shots/rocket.png");
		}else{
			new ImageEntity(this.getX(), this.getY(), "images/shots/beamamount.png");
		}
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Monospace", 0, 16));
		if(PlayerSession.getSecondsecondaryWeapon() == 1){
			g.drawString("x " + GameScreen.getPlayer().getRocketAmount(), this.getX() + 30, this.getY() + 12);
		}else{
			g.drawString("x " + GameScreen.getPlayer().getBeamAmount(), this.getX() + 30, this.getY() + 12);
		}
	}
	
	public void update() {}

}
