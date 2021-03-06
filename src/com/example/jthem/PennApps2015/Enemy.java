package com.example.jthem.PennApps2015;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;

public class Enemy extends GameObject {

	protected boolean alive;
	protected int hp;
    private Player player;
    
    static final double BASE_VEL = 4;
    
    private Game game;
	
	public Enemy(double pX, double pY, double vX, double vY, double r, Game game, Player player) {
		super(pX, pY, vX, vY, r, Tag.ENEMY);
		this.game = game;
		this.alive = true;
		this.player = player;
	}
	
	private void seekAdv(Player player) {
		velX = BASE_VEL * (player.posX - posX) / Canvas.MAX_X_RES;
		velY = BASE_VEL * (player.posY - posY) / Canvas.MAX_Y_RES;
	}
	
	public void seek(Player player) {
	  /*if (this.posX > player.posX) {
			velX = -BASE_VEL;
		} else if (this.posX < player.posX) {
			velX = BASE_VEL;
		} else {
			velX = 0;
		}
		
		if (this.posY > player.posY) {
			velY = -BASE_VEL;
		} else if (this.posY < player.posY) {
			velY = BASE_VEL;
		} else {
			velY = 0;
		} */
		seekAdv(player);
	}
	
	@Override
	public void move() {
		seek(player);
		super.move();
	}
	
	public void shoot() {
		Color color = Color.ORANGE;
        game.enemyBulletList.add(new Bullet(posX - 10, posY, 0, -10, 5, color,
                player, Tag.ENEMY));

        game.enemyBulletList.add(new Bullet(posX + 10, posY, 0, -10, 5, color,
                player, Tag.ENEMY));
    }
	
    @Override
    public void draw() {
        StdDraw.setPenColor(Color.RED);
        StdDraw.filledCircle(posX, posY, r);
    }
    
    public void injure(int amount) {
    	hp -= amount;
    }
    
    public void heal(int amount) {
    	hp += amount;
    }
    
    @Override
    public boolean isVisible() {
        return super.isVisible() && alive;
    }
    
    public void kill() {
    	alive = false;
    }
	
}
