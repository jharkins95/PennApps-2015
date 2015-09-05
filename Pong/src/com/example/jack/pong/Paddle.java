package com.example.jack.pong;

public class Paddle {
	public double px;
	public double py;
	public double halfWidth;
	public double halfHeight;
	
	public Paddle(double px, double py, double halfWidth, double halfHeight) {
		this.px = px;
		this.py = py;
		this.halfWidth = halfWidth;
		this.halfHeight = halfHeight;
	}
}
