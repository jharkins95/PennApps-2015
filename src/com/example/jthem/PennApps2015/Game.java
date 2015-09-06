package com.example.jthem.PennApps2015;

import edu.princeton.cs.introcs.*;

public class Game {
	
	boolean hasInitiated;
	boolean hasStarted;
	boolean isPaused;
	boolean hasExited;
	
	static final int MAX_X_RES = 800;
	static final int MAX_Y_RES = 600;
	
	public Game() {
		this.hasInitiated = false;
		this.hasStarted = false;
		this.isPaused = false;
		this.hasExited = false;
	}
	
	public void init() {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setCanvasSize(MAX_X_RES, MAX_Y_RES);
		StdDraw.setXscale(0, MAX_X_RES);
		StdDraw.setYscale(0, MAX_Y_RES);
		this.hasInitiated = true;
	}

	public void loop(int speed) {
		while (!this.hasExited) {
			if (!this.hasInitiated) {
				this.init();
			}
			StdDraw.rectangle(100, 100, 50, 50);
			StdDraw.show(speed);
		}
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		int speed = 0;;
		try {
			speed = Integer.parseInt(args[0]);
		} catch (IllegalArgumentException e) {
			System.out.println("error: speed is not an integer!");
		} catch(ArrayIndexOutOfBoundsException e) {
			speed = 10;
			System.out.println("no speed provided; defaulting to " + speed);
		}
		game.loop(speed);;
	}

}
