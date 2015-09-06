package com.example.jthem.PennApps2015;

import edu.princeton.cs.introcs.StdDraw;

public class Enemy extends GameObject {

	protected boolean alive;
	protected int hp;
    private double borderR = r + 10;
    private Player player;
    
    static final double BASE_VEL = 1;
	
	public Enemy(double pX, double pY, double vX, double vY, double r, int hp, Player player) {
		super(pX, pY, vX, vY, r, Tag.ENEMY);
		this.hp = hp;
		this.alive = true;
		this.player = player;
	}
	
	public void seek(Player player) {
		if (this.posX > player.posX) {
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
		}
	}
	
	@Override
	public void move() {
		seek(player);
		super.move();
	}
	
    @Override
    public void draw() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(posX, posY, r);
        System.out.println("enemy drawn!");
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
