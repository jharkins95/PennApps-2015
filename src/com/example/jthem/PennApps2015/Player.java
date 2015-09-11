package com.example.jthem.PennApps2015;

import java.awt.Color;
import edu.princeton.cs.introcs.*;

public class Player extends GameObject {

    private static final double MOVE_INC = 5.0;
    private static final int IMMUNE_ANIM_COUNTER_ON = 10;
    private static final int IMMUNE_ANIM_COUNTER_OFF = 2*IMMUNE_ANIM_COUNTER_ON;
    private static final int DEATH_ANIM_COUNTER_MAX = 150;
    
    
    private boolean alive;
    private boolean immune;
    private double borderR = r + 10.0;
    private int lives;
    private int immuneAnimCounter;
    private int deathAnimCounter;
    private boolean doneWithDeathAnim;
    private float deathAnimColor;
    
    private Game game;
	public GameTimer playerTimer;
    
    public Player(double pX, double pY, double vX, double vY, double r, Game game) {
        super(pX, pY, vX, vY, r, Tag.ALLY);
        this.game = game;
        this.immune = true;
        this.alive = true;
        this.lives = 5;
        this.immuneAnimCounter = 0;
        this.deathAnimCounter = 0;
        this.deathAnimColor = 0;
        this.doneWithDeathAnim = true;
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
        if (immune & alive) {
            if (immuneAnimCounter < IMMUNE_ANIM_COUNTER_ON) {
                // do nothing
            }
            else if (immuneAnimCounter < IMMUNE_ANIM_COUNTER_OFF) {
                StdDraw.filledCircle(posX, posY, r);
            }
        }
        else {
            if (!alive) {                
                StdDraw.setPenColor(new Color(deathAnimColor,1.0f,deathAnimColor));       
            }
            StdDraw.filledCircle(posX, posY, r);
        }
    }
    
    public void updateCounters() {
        if (immune & alive) {
            immuneAnimCounter++;
            if (immuneAnimCounter >= IMMUNE_ANIM_COUNTER_OFF)
                immuneAnimCounter = 0;
        }
        
        if (!alive) {
            doneWithDeathAnim = false;
            deathAnimColor = (float) deathAnimCounter/DEATH_ANIM_COUNTER_MAX;
            deathAnimCounter++;
            if(deathAnimCounter >= DEATH_ANIM_COUNTER_MAX) {
                deathAnimCounter = 0;
                doneWithDeathAnim = true;
                reset();
            }
        }
    }
    
    public boolean isDoneWithDeathAnim() {
        return doneWithDeathAnim;
    }
    
    public void kill() {
    	alive = false;
    	lives--;
    }
    
    public void reset() {
        alive = true;
        immune = true;
        posX = Game.PLAYER_SPAWN_X;
        posY = Game.PLAYER_SPAWN_Y;
        playerTimer.setMarker(playerTimer.getTime());
    }
    
    public void checkVulnerability() {
        if (immune && playerTimer.cmpMarkerCurrent() >= 3000) {
            makeVulnerable();
        }
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
