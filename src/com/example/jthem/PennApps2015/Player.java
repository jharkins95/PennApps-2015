package com.example.jthem.PennApps2015;

import java.awt.Color;
import java.awt.Font;
import edu.princeton.cs.introcs.*;

public class Player extends GameObject {

    private int MOVE_INC = 10;
    
    
    public Player(int pX, int pY, int vX, int vY) {
        super(pX, pY, vX, vY, Tag.ALLY);
        // TODO Auto-generated constructor stub
    }
    
    public void moveLeft() {
        velX -= MOVE_INC;
    }
    
    public void moveRight() {
        velX += MOVE_INC;
    }
    
    public void moveUp() {
        velY += MOVE_INC;
    }
    
    public void moveDown() {
        velY -= MOVE_INC;
    }
    
    @Override
    public void draw() {
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledCircle((double) posX, (double) posY, (double) r);
    }

}
