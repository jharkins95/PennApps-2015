package com.example.jthem.PennApps2015;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;

public class Bullet extends GameObject {

	private Color color;
	
    public Bullet(double pX, double pY, double vX, double vY, int r, Color color, Tag t) {
        super(pX, pY, vX, vY, r, t);
        this.color = color;
    }
    
    @Override
    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(posX, posY, r);
    }

}
