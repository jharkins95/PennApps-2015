package com.example.jthem.PennApps2015;

import edu.princeton.cs.introcs.*;

public class Game {
	
	private boolean initiated;
	private boolean started;
	private boolean paused;
	private boolean exited;
	private Canvas canvas;
	
	public Player player;
	public Enemy enemy;
	public GameObjectList bulletList;
	public GameObjectList enemyList;
	
	public Game() {
		this.initiated = false;
		this.started = false;
		this.paused = false;
		this.exited = false;
		canvas = new Canvas();
		player = new Player(400, 400, 0, 0, 30, this);
		enemyList = new GameObjectList();
		bulletList = new GameObjectList();
	}
	
	public void init() {
		canvas.init();
		for (int i = 0; i < 5; i++) {
			enemyList.add(new Enemy(i * 100 + 100, 500, 0, 0, 30, 100, player));
		}
		
		this.initiated = true;
	}

	public void loop(int speed) {
		if (!this.initiated) {
			this.init();
		}
		
        Controls.checkControls(this);
        
		if (StdDraw.hasNextKeyTyped()) { // Has a key been pressed?
            Controls.readKey(this);
        }
		if (this.paused) {
			return;
		}
		canvas.clear();
		player.move();
		bulletList.moveList();
		enemyList.moveList();
		bulletList.checkCollisions(enemyList);
		enemyList.checkCollisions(player);
		
		player.draw();

		if (player.getLives() < 1) {
			this.setHasExited(true);
		}
		
		bulletList.drawList();
		enemyList.drawList();

		StdDraw.show(speed);
		
	}
	
	public void setHasExited(boolean b) {
	    exited = b;
	}
	
	public void setPaused(boolean b) {
		paused = b;
	}
	
	public boolean isPaused() {
		return paused;
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
		while (!game.exited)
		    game.loop(speed);
		System.exit(0);
	}

}
