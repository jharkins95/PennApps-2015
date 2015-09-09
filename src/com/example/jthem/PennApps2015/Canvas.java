package com.example.jthem.PennApps2015;

import edu.princeton.cs.introcs.StdDraw;

public class Canvas {
	
	static final int MAX_X_RES = 800;
	static final int MAX_Y_RES = 600;
	
	private int resX;
	private int resY;
	
	public Canvas() {
		this.resX = MAX_X_RES;
		this.resY = MAX_Y_RES;
	}
	
	public Canvas(int resX, int resY) {
		this.resX = resX;
		this.resY = resY;
	}
	
	public void init() {
		StdDraw.setCanvasSize(resX, resY);
		StdDraw.setXscale(0, resX);
		StdDraw.setYscale(0, resY);
	}
	
	public void clear() {
		StdDraw.clear(StdDraw.BLACK);
	}
	
	public void animate(int speed) {
		StdDraw.show(speed);
	}
	
}
