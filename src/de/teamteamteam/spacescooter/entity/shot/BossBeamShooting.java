package de.teamteamteam.spacescooter.entity.shot;

import de.teamteamteam.spacescooter.entity.Entity;
import de.teamteamteam.spacescooter.entity.enemy.EnemyBigBoss;
import de.teamteamteam.spacescooter.gui.ImageEntity;

public class BossBeamShooting extends Entity{

	private int lifetime = 0;
	private ImageEntity front1;
	private ImageEntity front2;
	private ImageEntity bossGun;
	private ImageEntity bossImage;
	private EnemyBigBoss boss;
	private BossBeam bb;
	private BossShotWave bsw;
	private int mode;
	private boolean hardmode;
	
	public BossBeamShooting(int x, int y, EnemyBigBoss boss, int mode, boolean hardmode) {
		super(x, y);
		boss.setImage("images/bossBigShooting1.png");
		this.boss = boss;
		this.bossGun = new ImageEntity(getX()+10, getY()+190, "images/bossGun.png");
		this.bossImage = new ImageEntity(x, y, "images/bossBigShooting1.png");
		this.front1 = new ImageEntity(getX(), getY(), "images/bossBigShooting2.png");
		this.front2 = new ImageEntity(getX(), getY(), "images/bossBigShooting3.png");
		this.mode = mode;
		this.hardmode = hardmode;
	}
	
	@Override
	public void remove() {
		front1.remove();
		front2.remove();
		bossGun.remove();
		bossImage.remove();
		if(bb != null) bb.remove();
		super.remove();
	}
	
	@Override
	public void update() {
		this.lifetime++;
		if(this.lifetime<40){
			front1.transpose(0, -1);
			front2.transpose(0, 1);
		}else if(this.lifetime == 50){
			if(mode == 0) bb = new BossBeam(this.getX(), this.getY()+200);
			else bsw = new BossShotWave(this.getX()-10, this.getY()+200, hardmode);
		}else if(this.lifetime>160 && this.lifetime<200){
			front1.transpose(0, 1);
			front2.transpose(0, -1);
		}else if(this.lifetime == 200){
			boss.setImage("images/bossBig.png");
			this.remove();
		}
	}
	
	public void positionUpdate(int y_delta){
		front1.transpose(0, y_delta);
		front2.transpose(0, y_delta);
		bossGun.transpose(0, y_delta);
		bossImage.transpose(0, y_delta);
		if(bb != null && bb.isAlive()) bb.positionUpdate(y_delta);
		if(bsw != null && bsw.isAlive()) bsw.positionUpdate(y_delta);
	}
}
