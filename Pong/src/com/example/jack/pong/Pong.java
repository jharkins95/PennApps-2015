package com.example.jack.pong;

import java.awt.Color;
import java.awt.Font;
import edu.princeton.cs.introcs.*;

public class Pong {
	private static final double MIN_X_POS = 0;
	private static final double MAX_X_POS = 800;
	private static final double MIN_Y_POS = 0;
	private static final double MAX_Y_POS = 800;
	private static final double PADDLE_SPEED = 15;
	
	private static int humanPlayers = 2;
	private static int p1Wins = 0;
	private static int p2Wins = 0;
	private static int menuCursorItem = 0;
	private static boolean menu = true;
	private static boolean start = false;
	private static boolean pause = false;
	private static boolean exit = false;
	private static boolean ballHitPaddle = false;
	private static boolean debug = false;
	private static boolean mouseControlsBall = false;
	
	private static Ball ball;
	private static Paddle paddle1;
	private static Paddle paddle2;
	
	private static void setCanvasScale() {
		StdDraw.setXscale(MIN_X_POS, MAX_X_POS);
		StdDraw.setYscale(MIN_Y_POS, MAX_Y_POS + 100);
	}

	private static void drawMenuCursor() {
		double[] x = { 200, 200, 220 };
		if (menuCursorItem == 0) {
			double[] y =  { 520, 480, 500 };
			StdDraw.filledPolygon(x, y);
			humanPlayers = 1;
			debug = false;
		} else if (menuCursorItem == 1) {
			double[] y =  { 420, 380, 400 };
			StdDraw.filledPolygon(x, y);
			humanPlayers = 2;
			debug = false;
		} else if (menuCursorItem == 2) {
			double[] y =  { 320, 280, 300 };
			StdDraw.filledPolygon(x, y);
			humanPlayers = 0;
			debug = false;
		} else if (menuCursorItem == 3) {
			double[] y =  { 220, 180, 200 };
			StdDraw.filledPolygon(x, y);
			debug = true;
		}
	}

	private static void drawMenuItems() {
		StdDraw.clear(Color.BLACK);
		StdDraw.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 48));
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.text(MAX_X_POS / 2, 700, "PONG");
		StdDraw.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		StdDraw.text(MAX_X_POS / 2,  500, "1-PLAYER GAME");
		StdDraw.text(MAX_X_POS / 2,  400, "2-PLAYER GAME");
		StdDraw.text(MAX_X_POS / 2,  300, "AI GAME");
		StdDraw.text(MAX_X_POS / 2,  200, "DEBUG");
	}
	
	private static void resetBall() {
		ballHitPaddle = false;
		int vSign;
		double initBallX;
		if (Math.random() < 0.5) {
			vSign = -1;
			initBallX = MAX_X_POS / 1.2;
		} else { 
			vSign = 1;
			initBallX = MAX_X_POS - MAX_X_POS / 1.2;
		}
		ball = new Ball(initBallX, MAX_Y_POS / 2, 
				vSign * 8 - Math.random(), 1 - 4 * Math.random(), 5);
		if (p1Wins == 0 && p2Wins == 0) { // create new paddles at start of game
			paddle1 = new Paddle(20, MAX_Y_POS / 2, 5, 50);
			paddle2 = new Paddle(780, MAX_Y_POS / 2, 5, 50);
		}
	}

	private static void readKey() {
		char key = StdDraw.nextKeyTyped();
		switch (key) {

		case 's': // start game
			if (!menu)
				start = true;
			else
				menu = false;
			break;
		case 'p': // pause/unpause game
			pause = !pause;
			break;
		case 'q': // left paddle up
			if (!menu && !pause && paddle1.py >= MIN_Y_POS + paddle1.halfHeight && 
			paddle1.py <= MAX_Y_POS - paddle1.halfHeight - PADDLE_SPEED) 
				paddle1.py += PADDLE_SPEED;
			else if (menu && menuCursorItem > 0)
				menuCursorItem--;
			break;
		case 'a': // left paddle down
			if (!menu && !pause && paddle1.py >= MIN_Y_POS + paddle1.halfHeight + PADDLE_SPEED && 
			paddle1.py <= MAX_Y_POS - paddle1.halfHeight) 
				paddle1.py -= PADDLE_SPEED;
			else if (menu && menuCursorItem < 3)
				menuCursorItem++;
			break;
		case 'o': // right paddle up
			if (!menu && !pause && paddle2.py >= MIN_Y_POS + paddle2.halfHeight && 
			paddle2.py <= MAX_Y_POS - paddle2.halfHeight - PADDLE_SPEED) 
				paddle2.py += PADDLE_SPEED;
			break;
		case 'l': // right paddle down
			if (!menu && !pause && paddle2.py >= MIN_Y_POS + paddle2.halfHeight + PADDLE_SPEED && 
			paddle2.py <= MAX_Y_POS - paddle2.halfHeight) 
				paddle2.py -= PADDLE_SPEED;
			break;
		case 'e': // break the loop and exit the game
			exit = true;
			break;
			
		// DEBUG MODE KEYS
		case ',':
			if (debug) changeBallSpeed(-0.2, -0.2, false);
			break;
		case '.':
			if (debug) changeBallSpeed(0.2, 0.2, false);
			break;
		case '0':
			if (debug) humanPlayers = 0;
			break;
		case '1':
			if (debug) humanPlayers = 1;
			break;
		case '2':
			if (debug) humanPlayers = 2;
			break;
		case 'r':
			if (debug) resetBall();
			break;
		case 'd': //enable/disable mouse dragging of ball
			mouseControlsBall = !mouseControlsBall;

			break;
		default:
		
		}
	}
	
	private static void drawGame() {
		// Set up canvas
		StdDraw.clear(Color.BLACK);
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.line(MIN_X_POS, MAX_Y_POS, MAX_X_POS, MAX_Y_POS);
		
		// Draw scores
		StdDraw.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
		StdDraw.text(40, 850, "" + p1Wins);
		StdDraw.text(760, 850, "" + p2Wins);
		
		// Draw ball
		StdDraw.filledSquare(ball.px, ball.py, ball.halfWidth);
		
		// Draw paddles
		StdDraw.filledRectangle(paddle1.px, paddle1.py, paddle1.halfWidth, paddle1.halfHeight);
		StdDraw.filledRectangle(paddle2.px, paddle2.py, paddle2.halfWidth, paddle2.halfHeight);
	}
	
	private static void changeBallSpeed(double vx, double vy, boolean random) {
		double scale = 1;
		if (random) scale = 1 - 0.5 * Math.random();
		ball.vx += scale * vx;
		ball.vy += scale * vy;
	}
	
	private static void checkBounds() {
		
		if (ball.px <= paddle1.px + ball.halfWidth && ball.py >= paddle1.py - 0.5 * paddle1.halfHeight && 
				ball.py <= paddle1.py + 0.5 * paddle1.halfHeight) {
			ball.vx = -ball.vx;                  //hit left paddle, center half
			changeBallSpeed(0.2, 0.2, false);
			ballHitPaddle = true;
		} else if (ball.px <= paddle1.px + ball.halfWidth && ball.py >= paddle1.py - paddle1.halfHeight && 
				ball.py <= paddle1.py - 0.5 * paddle1.halfHeight) {
			ball.vx = -ball.vx;                  //hit left paddle, lower quarter
			changeBallSpeed(0, -2, true);
			ballHitPaddle = true;
		} else if (ball.px <= paddle1.px + ball.halfWidth && ball.py >= paddle1.py + 0.5 * paddle1.halfHeight && 
				ball.py <= paddle1.py + paddle1.halfHeight) {
			ball.vx = -ball.vx;                  //hit left paddle, upper quarter
			changeBallSpeed(0, 2, true);
			ballHitPaddle = true;
		} else if (ball.px >= paddle2.px - ball.halfWidth && ball.py >= paddle2.py - 0.5 * paddle2.halfHeight && 
				ball.py <= paddle2.py + 0.5 * paddle2.halfHeight) {
			ball.vx = -ball.vx;                  // hit right paddle, center
			changeBallSpeed(0.2, 0.2, false);
			ballHitPaddle = true;
		} else if (ball.px >= paddle2.px - ball.halfWidth && ball.py >= paddle2.py - paddle2.halfHeight && 
				ball.py <= paddle2.py - 0.5 * paddle2.halfHeight) {
			ball.vx = -ball.vx;                  //hit right paddle, lower quarter
			changeBallSpeed(0, -2, true);
			ballHitPaddle = true;
		} else if (ball.px >= paddle2.px - ball.halfWidth && ball.py >= paddle2.py + 0.5 * paddle2.halfHeight && 
				ball.py <= paddle2.py + paddle2.halfHeight) {
			ball.vx = -ball.vx;                  //hit right paddle, upper quarter
			changeBallSpeed(0, 2, true);
			ballHitPaddle = true;
		} else if (ball.px <= paddle1.px + ball.halfWidth) {         
			p2Wins++;                            // out of left bound
			resetBall();
		} else if (ball.px >= paddle2.px - ball.halfWidth) {         
			p1Wins++;                            // out of right bound
			resetBall();
		} else if (ball.py <= MIN_Y_POS + ball.halfWidth || 
				ball.py >= MAX_Y_POS - ball.halfWidth) 
			ball.vy = -ball.vy;                  // out of vertical bounds
	}
	
	private static void updateBallPos() {
		if (mouseControlsBall) {
			ball.px = StdDraw.mouseX();
			ball.py = StdDraw.mouseY();
		}
		else {
			ball.px += ball.vx;
			ball.py += ball.vy;
		}
	}
	
	private static void movePaddleAI(Paddle p) {
		if (!ballHitPaddle) {
			if (p.py > ball.py && ball.vy > 0)
				p.py -= Math.sqrt(ball.vx * ball.vx + ball.vy * ball.vy);
			else if (p.py > ball.py && ball.vy < 0)
				p.py -= 2 * Math.sqrt(ball.vx * ball.vx + ball.vy * ball.vy);
			else if (p.py < ball.py && ball.vy > 0)
				p.py += 2 * Math.sqrt(ball.vx * ball.vx + ball.vy * ball.vy);
			else if (p.py < ball.py && ball.vy < 0)
				p.py += Math.sqrt(ball.vx * ball.vx + ball.vy * ball.vy);
		}
		if (ballHitPaddle && ball.vy > 0 && p.py + p.halfHeight < 800 && p.py > 0) 
			p.py += Math.pow(ball.vy, 0.75);
		if (ballHitPaddle && ball.vy < 0 && p.py < 800 && p.py - p.halfHeight > 0) 
			p.py -= Math.pow(-ball.vy, 0.75);
	}
	
	private static void printDebugInfo() {
		StdDraw.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		StdDraw.setPenColor(Color.BLUE);
		StdDraw.text(MAX_X_POS / 2, 100, "ball.px: " + ball.px);
		StdDraw.text(MAX_X_POS / 2, 75, "ball.py: " + ball.py);
		StdDraw.text(MAX_X_POS / 2, 50, "ball.vx: " + ball.vx);
		StdDraw.text(MAX_X_POS / 2, 25, "ball.vy: " + ball.vy);
	}
	
	private static void gameLoop(int speed) {
		while (!exit) {
			if (StdDraw.hasNextKeyTyped()) { // Has a key been pressed?
				readKey();
			}
			StdDraw.show(speed);
			if (pause) {
				StdDraw.setPenColor(Color.GREEN);
				StdDraw.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
				StdDraw.text(MAX_X_POS / 2, MAX_Y_POS / 2, "Paused");
				continue;
			}
			if (menu) {
				drawMenuItems();
				drawMenuCursor();
				continue;
			}
			if (start && humanPlayers == 1) 
				movePaddleAI(paddle1);
			else if (start && humanPlayers == 0) {
				movePaddleAI(paddle1);
				movePaddleAI(paddle2);
			}
			drawGame();
			if (debug) printDebugInfo();
			if (!start) continue;
			checkBounds();
			updateBallPos();
		}
	}
	
	public static void main(String[] args) {
		setCanvasScale();
		resetBall();
		gameLoop(10);
		System.exit(0);
	}
}
