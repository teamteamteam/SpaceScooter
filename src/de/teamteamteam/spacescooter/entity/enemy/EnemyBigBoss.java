package de.teamteamteam.spacescooter.entity.enemy;

import de.teamteamteam.spacescooter.brain.GameConfig;
import de.teamteamteam.spacescooter.entity.FlashImage;
import de.teamteamteam.spacescooter.entity.explosion.MultiExplosion;
import de.teamteamteam.spacescooter.entity.shot.BossBeamShooting;
import de.teamteamteam.spacescooter.entity.shot.BossEnemyTakeOff;
import de.teamteamteam.spacescooter.entity.shot.BossRocket;
import de.teamteamteam.spacescooter.gui.BossHealthBar;
import de.teamteamteam.spacescooter.screen.GameScreen;
import de.teamteamteam.spacescooter.utility.Random;

public class EnemyBigBoss extends Enemy{
	
	private int move = 1;
	private int moveTickCounter = 0;
	private int shotTickCounter = 0;
	private int nextShot = 500;
	private int thisShot;
	private int lastShot;
	private FlashImage flashBossImage;
	private BossBeamShooting bbs;
	private BossEnemyTakeOff beto;
	private EnemyBigBossHitbox[] hitbox = new EnemyBigBossHitbox[3];
	private boolean hardmode = false;
	
	public EnemyBigBoss(int x, int y) {
		super(x, y);
		this.setImage("images/bossBig.png");
		this.setHealthPoints(8000);
		this.setCollisionDamage(100);
		this.setScore(10000);
		this.setCollide(false);
		this.willShoot = false;
		this.hitbox[0] = new EnemyBigBossHitbox(409, 136, this);
		this.hitbox[1] = new EnemyBigBossHitbox(192, 284, this);
		this.hitbox[2] = new EnemyBigBossHitbox(74, 398, this);
		new BossHealthBar(240, 5, this);
	}
	
	@Override
	public void die() {
		if(flashBossImage != null) flashBossImage.remove();
		if(bbs != null) bbs.remove();
		if(beto != null) beto.remove();
		for(int i =0; i<3; i++){
			hitbox[i].remove();
		}
		super.die();
	}
	
	/**
	 * Custom MultiExplosion for this enemy.
	 */
	@Override
	public void explode() {
		new MultiExplosion(this.getCenteredX()-2*96, this.getCenteredY());
		new MultiExplosion(this.getCenteredX()-96, this.getCenteredY()-92);
		new MultiExplosion(this.getCenteredX()-96, this.getCenteredY());
		new MultiExplosion(this.getCenteredX()-96, this.getCenteredY()+92);
		new MultiExplosion(this.getCenteredX(), this.getCenteredY()-2*92);
		new MultiExplosion(this.getCenteredX(), this.getCenteredY()-92);
		new MultiExplosion(this.getCenteredX(), this.getCenteredY());
		new MultiExplosion(this.getCenteredX(), this.getCenteredY()+92);
		new MultiExplosion(this.getCenteredX(), this.getCenteredY()+2*92);
		new MultiExplosion(this.getCenteredX()+96, this.getCenteredY()-2*92);
		new MultiExplosion(this.getCenteredX()+96, this.getCenteredY()-92);
		new MultiExplosion(this.getCenteredX()+96, this.getCenteredY());
		new MultiExplosion(this.getCenteredX()+96, this.getCenteredY()+92);
		new MultiExplosion(this.getCenteredX()+96, this.getCenteredY()+2*92);
		new MultiExplosion(this.getCenteredX()+2*96, this.getCenteredY()-92);
		new MultiExplosion(this.getCenteredX()+2*96, this.getCenteredY());
		new MultiExplosion(this.getCenteredX()+2*96, this.getCenteredY()+92);
	}
	
	@Override
	public void update() {
		super.update();
		hitbox[0].setPosition(this.getCenteredX(), this.getCenteredY());
		hitbox[1].setPosition(this.getCenteredX()+15, this.getCenteredY());
		hitbox[2].setPosition(this.getCenteredX()+33, this.getCenteredY());
		
		//Move into the Screen until it fits on X-Axis
		if(this.getX() > GameConfig.gameScreenWidth+GameConfig.gameScreenXOffset-this.getImageWidth()) {
			this.transpose(-1, 0);
		}else if(this.moveTickCounter > 2){//Move up and down with half speed
			//Move up or down within the GameScreen.
			this.transpose(0, this.move);
			
			if(bbs != null && bbs.isAlive()){
				bbs.positionUpdate(move);
			}
			if(beto != null){
				beto.positionUpdate(move);
			}
			if(this.getY() < GameConfig.gameScreenYOffset){
				this.move = 1;
			}
			if(this.getY() > GameConfig.gameScreenHeight + GameConfig.gameScreenYOffset - this.getImageHeight()){
				this.move = -1;
			}
			this.moveTickCounter = 0;
		}else{
			this.moveTickCounter++;
	}
		
		//Randomly fire
		if(this.shotTickCounter == this.nextShot){
			
			do{
				thisShot = Random.nextInt(4);
			}while(thisShot == lastShot);
			lastShot = thisShot;
			switch (thisShot) {
			case 0://Fire the rocket
				if(!hardmode){
					if(GameScreen.getPlayer().getCenteredY() < this.getCenteredY()) new BossRocket(getCenteredX(), getCenteredY(), true);
					else new BossRocket(getCenteredX(), getCenteredY(), false);
				}else{
					new BossRocket(getCenteredX(), getCenteredY(), true);
					new BossRocket(getCenteredX(), getCenteredY(), false);
				}
				flashBossImage = new FlashImage(this.getCenteredX(), this.getCenteredY(), "images/bossBig.png", 12);
				break;
			case 1://Fire the beam
				bbs = new BossBeamShooting(getX(), getY(), this, 0, hardmode);
				break;
			case 2://Fire the shot wave
				bbs = new BossBeamShooting(getX(), getY(), this, 1, hardmode);
				break;
			case 3://Enemy take off
				beto = new BossEnemyTakeOff(getCenteredX()-7, getY(), hardmode);
				break;
			}

			if(getHealthPoints() >6000) this.nextShot = 400;
			else if(getHealthPoints() >4000) this.nextShot = 300;
			else if(getHealthPoints() >2000) this.nextShot = 250;
			else{
				this.nextShot = 200;
				hardmode = true;
			}
			this.shotTickCounter = 0;
		}else{
			this.shotTickCounter++;
		}
		if(flashBossImage != null && flashBossImage.isAlive()) flashBossImage.setPosition(this.getX(), this.getY());
	}
}
