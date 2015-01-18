package de.teamteamteam.spacescooter.entity.enemy;

import de.teamteamteam.spacescooter.entity.LivingEntity;
import de.teamteamteam.spacescooter.entity.Player;
import de.teamteamteam.spacescooter.entity.shot.Shot;
import de.teamteamteam.spacescooter.entity.spi.Collidable;

public class EnemyBigBossHitbox extends LivingEntity{

	EnemyBigBoss enemyBigBoss;
	
	public EnemyBigBossHitbox(int width, int height, EnemyBigBoss enemyBigBoss) {
		super(0, 0);
		this.setHitboxDimenstions(width, height);
		this.enemyBigBoss = enemyBigBoss;
	}
	
	@Override
	public void collideWith(Collidable entity) {
		if(entity instanceof EnemyBigBossHitbox) return;
		if(entity instanceof Shot){
			Shot shot = (Shot) entity;
			if(shot.getDirection() == Shot.LEFT) return;
			enemyBigBoss.takeDamage(shot.getDamageValue());
		}
		if (entity instanceof Player) {
			Player player = (Player) entity;
			enemyBigBoss.takeDamage(player.getCollisionDamage());
		}
	}
	
	@Override
	public void explode() {}

}
