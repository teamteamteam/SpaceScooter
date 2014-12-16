package de.teamteamteam.spacescooter.fx;

import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.brain.GameConfig;

/**
 * The EffectMaster3000 is a product of pure madness.
 */
public class EffectMaster {
	
	private static double x;
	private static int percentage;
	private static int timer;
	
	static {
		EffectMaster.init();
	}
	/**
	 * Private constructor - this guy does not want to be instantiated.
	 */
	private EffectMaster() {}
	
	/**
	 * Set initial values here.
	 */
	public static void init() {
		EffectMaster.x = 0.34;
	}
	/**
	 * Apply effects to the Graphics2D object here.
	 */
	public static void applyEffects(Graphics2D g) {
		//EffectMaster.rotationEffect(g);
	}
	
	/**
	 * A very simple effect that rotates the whole screen continuously.
	 * @param g
	 */
	@SuppressWarnings("unused")
	private static void rotationEffect(Graphics2D g) {
		g.fillRect(0, 0, GameConfig.windowWidth, GameConfig.windowHeight);
		if(EffectMaster.timer == 0 && EffectMaster.percentage < 100) {
			EffectMaster.percentage++;
		} else if (EffectMaster.timer > 500 && EffectMaster.percentage > 0) {
			EffectMaster.percentage--;
		} else if (EffectMaster.percentage == 100){
			EffectMaster.timer++;
		} else if (EffectMaster.percentage == 0) {
			EffectMaster.timer--;
		}
		//EffectMaster.x += 0.005;
		
		
		g.rotate(EffectMaster.x * EffectMaster.percentage / 100, GameConfig.windowWidth/2, GameConfig.windowHeight/2);
	}
	
}
