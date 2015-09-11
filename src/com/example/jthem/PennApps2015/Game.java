package com.example.jthem.PennApps2015;

import edu.princeton.cs.introcs.*;

import java.awt.Color;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class Game {
	
    public static final double PLAYER_SPAWN_X = 400;
    public static final double PLAYER_SPAWN_Y = 100;

    private static final Color PAUSE_OVERLAY = new Color(0, 0, 0, 200);
    
	private boolean initiated;
	private boolean paused;
	private boolean exited;
	private Canvas canvas;
	public Player player;
	public Enemy enemy;
	public GameObjectList playerBulletList;
	public GameObjectList enemyBulletList;
	public GameObjectList enemyList;
	private GameTimer enemyTimer;
	
	public Game() {
		this.initiated = false;
		this.paused = false;
		this.exited = false;
		canvas = new Canvas();
		player = new Player(PLAYER_SPAWN_X, PLAYER_SPAWN_Y, 0, 0, 30, this);
		enemyList = new GameObjectList();
		playerBulletList = new GameObjectList();
		enemyBulletList = new GameObjectList();
		enemyTimer = new GameTimer();
		
	}
	
	public void init() {
		canvas.init();
		for (int i = 0; i < 5; i++) {
			enemyList.add(new Enemy(i * 100 + 100, 700, 0, 0, 30, this, player));
		}
		
		this.initiated = true;
	}

	public void loop(int speed) {
		enemyTimer.setCurrentTime(); // these don't seem necessary
		player.playerTimer.setCurrentTime(); // these don't seem necessary
		if (!this.initiated) {
			this.init();
		}
		
		Controls.checkControls(this);
        
        if (StdDraw.hasNextKeyTyped()) { // Has a key been pressed?
            Controls.readKey(this);
        }
		
		if (!paused) {
    		if (player.getLives() < 1 && player.isDoneWithDeathAnim()) {
    		    // not done with this check. could be better
    			JOptionPane.showMessageDialog(null, "You are out of lives! Game over!",
    					"Game over!", JOptionPane.ERROR_MESSAGE);
    			this.setHasExited(true);
    		}
    		
    		
    		if (enemyTimer.cmpMarkerCurrent() >= 1000) {
    			enemyList.add(new Enemy(400, 700, 0, 0, 30, this, player));
    			enemyTimer.setMarker(enemyTimer.getTime());
    		}
    		
    		player.checkVulnerability();
    		player.updateCounters();
    		
    		player.move();
    		playerBulletList.moveList();
    		enemyBulletList.moveList();
    		enemyList.moveList();
    		
    		playerBulletList.checkCollisions(enemyList);
    		enemyBulletList.checkCollisions(player);
    		enemyList.checkCollisions(player);
    		
    		Iterator<GameObject> iter = enemyList.getList().listIterator();
    		while(iter.hasNext()) {
    			Enemy enemy = (Enemy) iter.next();
    			double epsilon = 10;
    			if (player.getPosX() <= enemy.getPosX() + epsilon &&
    				player.getPosX() >= enemy.getPosX() - epsilon &&
    				player.getPosY() <= enemy.getPosY()) {
    				enemy.shoot();
    			}
    		}
		}

        canvas.clear();
		playerBulletList.drawList();
		enemyBulletList.drawList();
		enemyList.drawList();
		player.draw();

		if (paused) {
		    StdDraw.clear(PAUSE_OVERLAY);
		    StdDraw.text(Canvas.MAX_X_RES/2, Canvas.MAX_X_RES/2, "PAUSED");
		}
		
		canvas.animate(speed);
		
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
	
	public void pauseTimers() {
	    enemyTimer.pause();
	    player.playerTimer.pause();
	}
	
	public void unpauseTimers() {
	    enemyTimer.unpause();
        player.playerTimer.unpause();
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
		while (!game.exited) {
		    game.loop(speed);
		}
		System.exit(0);
	}

}
