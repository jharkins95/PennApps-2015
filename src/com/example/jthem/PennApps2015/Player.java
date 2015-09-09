package com.example.jthem.PennApps2015;

import java.awt.Color;
import edu.princeton.cs.introcs.*;
import java.awt.Font;

public class Player extends GameObject {

    private static final double MOVE_INC = 5.0;
    private boolean alive;
    private boolean immune;
    private double borderR = r + 10.0;
    private int lives;
    
    private Game game;
	public GameTimer playerTimer;
    
    public Player(double pX, double pY, double vX, double vY, double r, Game game) {
        super(pX, pY, vX, vY, r, Tag.ALLY);
        this.game = game;
        this.immune = true;
        this.alive = true;
        this.lives = 5;
		playerTimer = new GameTimer();
		playerTimer.setCurrentTime();
		playerTimer.setMarker(playerTimer.getTime());
    }
    
    public void moveLeft() {
        velX = -MOVE_INC;
    }
    
    public void moveRight() {
        velX = MOVE_INC;
    }
    
    public void moveUp() {
        velY = MOVE_INC;
    }
    
    public void moveDown() {
        velY = -MOVE_INC;
    }
    
    public void stopX() {
        velX = 0;
    }
    
    public void stopY() {
        velY = 0;
    }
    
    public void shoot() {
    	Color color = Color.CYAN;
        game.playerBulletList.add(new Bullet(posX - 10, posY, 0, 10, 5, color,
                Tag.ALLY));

        game.playerBulletList.add(new Bullet(posX + 10, posY, 0, 10, 5, color,
                Tag.ALLY));
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
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.filledCircle(posX, posY, r);
        if (immune) {
        	StdDraw.setPenColor(Color.BLACK);
        	StdDraw.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        	StdDraw.text(posX, posY, "I");
        }
    }
    
    public void kill() {
    	alive = false;
    	immune = true;
    	lives--;
   		posX = 400;
       	posY = 100;
       	playerTimer.setMarker(playerTimer.getTime());
    }
    
    public boolean isAlive() {
    	return alive;
    }
    
    public boolean isImmune() {
    	return immune;
    }
    
    public void makeVulnerable() {
    	immune = false;
    }
    
    public int getLives() {
    	return lives;
    }

}
