package com.example.jthem.PennApps2015;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.event.KeyEvent;

public class Controls {
    
    private static boolean leftPressed;
    private static boolean rightPressed;
    private static boolean upPressed;
    private static boolean downPressed;
    private static boolean shootPressed;    

	public static void readKey(Game game) {
		char key = StdDraw.nextKeyTyped();
		switch (key) {
		/*
		case 'a': // left
		    game.player.moveLeft();
			break;
		case 'd': // right
		    game.player.moveRight();
			break;
		case 'w': // up
		    game.player.moveUp();
			break;
		case 's': // down
		    game.player.moveDown();
			break;
		*/
		case 'p':
		    System.out.println("This should pause!");
		    break;
		case 'o':
		    game.setHasExited(true);
		    break;
		}
	}

	public static void updatePressedKeys() {
	    upPressed = StdDraw.isKeyPressed(KeyEvent.VK_W);
	    leftPressed = StdDraw.isKeyPressed(KeyEvent.VK_A);
	    downPressed = StdDraw.isKeyPressed(KeyEvent.VK_S);
	    rightPressed = StdDraw.isKeyPressed(KeyEvent.VK_D);
	    shootPressed = StdDraw.isKeyPressed(KeyEvent.VK_J);
	}
	
	public static boolean isMovingHorizontally() {
	    return leftPressed || rightPressed;
	}
	

    public static boolean isMovingVertically() {
        return upPressed || downPressed;
    }
	
	// Movement keys need to be checked continuously, as we need to update
	// the game when they are released, not just when pressed
	
	public static void checkLeft(Game game) {
	    if (leftPressed)
	        game.player.moveLeft();
	}
	
	public static void checkRight(Game game) {
	    if (rightPressed)
            game.player.moveRight();
	}
	
	public static void checkUp(Game game) {
        if (upPressed)
            game.player.moveUp();
    }
	
	public static void checkDown(Game game) {
        if (downPressed)
            game.player.moveDown();
    }
	
	public static void checkShoot(Game game) {
	    if (shootPressed)
	        game.player.shoot();
	}
	
	public static void checkControls(Game game) {
	    updatePressedKeys();
	    if(!isMovingHorizontally())
	        game.player.stopX();
	    if(!isMovingVertically())
	        game.player.stopY();
	    checkLeft(game);
	    checkRight(game);
	    checkUp(game);
	    checkDown(game);
	    checkShoot(game);
	    
	}
	
}
