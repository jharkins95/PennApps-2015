package com.example.jthem.PennApps2015;

import edu.princeton.cs.introcs.StdDraw;

public class Controls {
	public static void readKey(Game game) {
		char key = StdDraw.nextKeyTyped();
		switch (key) {
		
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
		}
	}
}
