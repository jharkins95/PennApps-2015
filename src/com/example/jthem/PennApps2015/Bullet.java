package com.example.jthem.PennApps2015;

import edu.princeton.cs.introcs.StdDraw;

public class Bullet extends GameObject {

    public Bullet(double pX, double pY, double vX, double vY, int r, Tag t) {
        super(pX, pY, vX, vY, r, t);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void draw() {
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.filledCircle(posX, posY, r);
    }

}