package com.example.jthem.PennApps2015;

import java.awt.Color;
import java.awt.Font;
import edu.princeton.cs.introcs.*;

public class Player extends GameObject {

    public Player(int pX, int pY, int vX, int vY) {
        super(pX, pY, vX, vY, Tag.ALLY);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void draw() {
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledCircle((double) posX, (double) posY, (double) r);
    }

}
