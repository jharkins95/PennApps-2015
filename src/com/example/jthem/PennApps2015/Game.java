package com.example.jthem.PennApps2015;

import edu.princeton.cs.introcs.*;

public class Game {
	
	private boolean hasInitiated;
	private boolean hasStarted;
	private boolean isPaused;
	private boolean hasExited;
	private Canvas canvas;
	
	public Player player;
	public Enemy enemy;
	public GameObjectList gameObjectList;
	
	public Game() {
		this.hasInitiated = false;
		this.hasStarted = false;
		this.isPaused = false;
		this.hasExited = false;
		canvas = new Canvas();
		player = new Player(400, 400, 0, 0, 30, this);
		enemy = new Enemy(700, 700, 0, 0, 30, 100);
		gameObjectList = new GameObjectList();
	}
	
	public void init() {
		canvas.init();
		this.hasInitiated = true;
	}

	public void loop(int speed) {
		if (!this.hasInitiated) {
			this.init();
		}
		
        Controls.checkControls(this);
        
		if (StdDraw.hasNextKeyTyped()) { // Has a key been pressed?
            Controls.readKey(this);
        }
		canvas.clear();
		player.move();
		gameObjectList.moveList();
		gameObjectList.drawList();
		player.draw();
		if (!enemy.collide(player)) {
			System.out.println(enemy.collide(player));
			enemy.seek(player);
		} else {
			enemy.velX = 0;
			enemy.velY = 0;
		}
		//for (GameObject g : gameObjectList) {
		//	;
		//}
		enemy.move();
		enemy.draw();
		StdDraw.show(speed);
		
	}
	
	public void setHasExited(boolean b) {
	    hasExited = b;
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
		System.exit(0);
	}

}
