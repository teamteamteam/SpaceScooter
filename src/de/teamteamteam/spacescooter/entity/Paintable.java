package de.teamteamteam.spacescooter.entity;

import java.awt.Graphics2D;

/**
 * Interface providing the paint method.
 */
public interface Paintable {

	/**
	 * All Paintable Entities need to implement this to paint themselves.
	 */
	public void paint(Graphics2D g);

}
