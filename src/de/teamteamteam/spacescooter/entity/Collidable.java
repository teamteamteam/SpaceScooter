package de.teamteamteam.spacescooter.entity;

import java.awt.Rectangle;

public interface Collidable {
	public Rectangle getCollisionBox();
	public void collideWith(Collidable entity);
}
