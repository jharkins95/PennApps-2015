package com.example.jthem.PennApps2015;

import java.awt.Color;
import java.awt.Font;
import edu.princeton.cs.introcs.*;

public class Player extends GameObject {

    private double MOVE_INC = 5.0;
    private boolean alive;
    private double borderR = r + 10.0;
    private int lives;
    
    private Game game;
    
    public Player(double pX, double pY, double vX, double vY, double r, Game game) {
        super(pX, pY, vX, vY, r, Tag.ALLY);
        this.game = game;
        this.alive = true;
        this.lives = 5;
        // TODO Auto-generated constructor stub
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
        game.gameObjectList.add(new Bullet(posX - 10, posY, 0, 10, 5, 
                Tag.ALLY));

        game.gameObjectList.add(new Bullet(posX + 10, posY, 0, 10, 5, 
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
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledCircle(posX, posY, r);
    }
    
    public void kill() {
    	this.alive = false;
    	this.lives--;
    	this.posX = 400;
    	this.posY = 100;
    }
    
    public int getLives() {
    	return this.lives;
    }

}
