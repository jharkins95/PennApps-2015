package com.example.jthem.PennApps2015;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;

public class Bullet extends GameObject {

    private static final double BASE_VEL = 4;
    
	private Color color;
	private Player player;
	private boolean shouldTrack;
	
	// constructor intended for player bullets
    public Bullet(double pX, double pY, double vX, double vY, int r, Color color, Tag t) {
        super(pX, pY, vX, vY, r, t);
        this.color = color;
        shouldTrack = false;
    }
    
    // constructor intended for enemy bullets
    public Bullet(double pX, double pY, double vX, double vY, int r, Color color, Player pl, Tag t) {
        super(pX, pY, vX, vY, r, t);
        this.color = color;
        this.player = pl;
        shouldTrack = true;
    }
    
    @Override
    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(posX, posY, r);
    }
    
    @Override
    public void move() {
        // to disable tracking bullets, just put false below
        if (shouldTrack) {
            double dist = Math.sqrt(Math.pow(posX - player.posX, 2) 
                    + Math.pow(posY - player.posY, 2));
            velX = BASE_VEL * (player.posX - posX)/dist;
            velY = -BASE_VEL; // only tracking horizontally for now
            // velY = BASE_VEL * (player.posY - posY)/dist;
        }
        super.move();
    }

}
