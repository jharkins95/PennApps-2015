package com.example.jthem.PennApps2015;

import java.awt.Color;
import java.awt.Font;
import edu.princeton.cs.introcs.*;

public class Player extends GameObject {

    private int MOVE_INC = 10;
    
    private int borderR = r + 20;
    
    public Player(int pX, int pY, int vX, int vY, int r) {
        super(pX, pY, vX, vY, r, Tag.ALLY);
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
        StdDraw.filledCircle((double) posX, (double) posY, (double) r);
    }

}
