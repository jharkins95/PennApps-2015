package com.example.jack.pong;

public class Ball {
	public double px;
	public double py;
	public double vx;
	public double vy;
	public double halfWidth;
	
	public Ball(double px, double py, double vx, double vy, double halfWidth) {
		this.px = px;
		this.py = py;
		this.vx = vx;
		this.vy = vy;
		this.halfWidth = halfWidth;
	}
}
