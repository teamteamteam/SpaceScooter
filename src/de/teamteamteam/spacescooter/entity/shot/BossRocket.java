package de.teamteamteam.spacescooter.entity.shot;

import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.entity.enemy.Enemy;
import de.teamteamteam.spacescooter.entity.enemy.EnemyBigBossHitbox;
import de.teamteamteam.spacescooter.entity.explosion.MultiExplosion;
import de.teamteamteam.spacescooter.entity.spi.Collidable;
import de.teamteamteam.spacescooter.screen.GameScreen;

public class BossRocket extends Shot {

	private double rotation = 0;
	private int lifeTime = 0;
	
	public BossRocket(int x, int y, boolean upperRocket) {
		super(x, y, Shot.LEFT, 3, 50, "images/shots/bossRocket.png");
		if(upperRocket) this.setPosition(x-85, y-143);
		else this.setPosition(x-85, y+93);
	}
	
	@Override
	public void update(){
		if(lifeTime > 30){
			if(GameScreen.getPlayer().getCenteredY() < this.getCenteredY() - 20){
				this.transpose(0, -1);
				rotation = 0.1;
			}else if(GameScreen.getPlayer().getCenteredY() > this.getCenteredY() + 20){
				this.transpose(0, 1);
				rotation = -0.1;
			}else{
				rotation = 0;
			}
		}
		lifeTime++;
		super.update();
	}
	
	@Override
	public void paint(Graphics2D g) {
		g.rotate(rotation);
		super.paint(g);
		g.rotate(-rotation);
	}
	
	@Override
	public void collideWith(Collidable entity) {
		if(entity instanceof EnemyBigBossHitbox) return;
		super.collideWith(entity);
		if(this.getDirection() == LEFT && entity instanceof Enemy) return;
		new MultiExplosion(this.getX(), this.getCenteredY());
		new MultiExplosion(this.getX()+(int)(this.getHitboxWidth()*0.25), this.getCenteredY());
		new MultiExplosion(this.getCenteredX(), this.getCenteredY());
		new MultiExplosion(this.getX()+(int)(this.getHitboxWidth()*0.75), this.getCenteredY());
		new MultiExplosion(this.getX()+this.getHitboxWidth(), this.getCenteredY());
	}
}
