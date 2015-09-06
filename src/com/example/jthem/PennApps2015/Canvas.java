package com.example.jthem.PennApps2015;

import edu.princeton.cs.introcs.StdDraw;

public class Canvas {
	
	static final int MAX_X_RES = 800;
	static final int MAX_Y_RES = 600;
	
	int resX;
	int resY;
	
	public Canvas() {
		this.resX = MAX_X_RES;
		this.resY = MAX_Y_RES;
	}
	
	public Canvas(int resX, int resY) {
		this.resX = resX;
		this.resY = resY;
	}
	
	public void init() {
		StdDraw.setCanvasSize(MAX_X_RES, MAX_Y_RES);
		StdDraw.setXscale(0, MAX_X_RES);
		StdDraw.setYscale(0, MAX_Y_RES);
	}
	
}
