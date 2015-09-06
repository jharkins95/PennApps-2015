package com.example.jthem.PennApps2015;

import edu.princeton.cs.introcs.*;

public class Game {
	
	boolean hasInitiated;
	boolean hasStarted;
	boolean isPaused;
	boolean hasExited;
	Canvas canvas;
	public Player player;
	
	public Game() {
		this.hasInitiated = false;
		this.hasStarted = false;
		this.isPaused = false;
		this.hasExited = false;
		canvas = new Canvas();
	}
	
	public void init() {
		canvas.init();
		this.hasInitiated = true;
		player = new Player(400, 400, 0, 0, 40);
	}

	public void loop(int speed) {
		if (!this.hasInitiated) {
			this.init();
		}
		if (StdDraw.hasNextKeyTyped()) { // Has a key been pressed?
            Controls.readKey(this);
        } else {
        	player.velX = 0;
        	player.velY = 0;
        }
		canvas.clear();
		player.move();
		player.draw();
		StdDraw.show(speed);
		
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
		while (!game.hasExited)
		    game.loop(speed);
	}

}
