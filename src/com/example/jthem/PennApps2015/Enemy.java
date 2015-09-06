package com.example.jthem.PennApps2015;

import edu.princeton.cs.introcs.StdDraw;

public class Enemy extends GameObject {

	protected boolean alive;
	protected int hp;
    private double borderR = r + 10;
    
    static final double BASE_VEL = 1;
	
	public Enemy(double pX, double pY, double vX, double vY, double r, int hp) {
		super(pX, pY, vX, vY, r, Tag.ENEMY);
		this.hp = hp;
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
	public void clip() {
        // check if player is not leaving playing field
        // if so, put them back in playing field
        if (posX - borderR < 0)
            posX = borderR;
        else if (posX + borderR > Canvas.MAX_X_RES)
            posX = Canvas.MAX_X_RES - borderR;
        
        if (posY - borderR < 0)
            posY = borderR;
        else if (posY + borderR > Canvas.MAX_Y_RES)
            posY = Canvas.MAX_Y_RES - borderR;
    }
	
    @Override
    public void draw() {
        StdDraw.setPenColor(StdDraw.RED);
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
