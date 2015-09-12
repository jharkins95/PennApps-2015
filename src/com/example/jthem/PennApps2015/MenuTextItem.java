package com.example.jthem.PennApps2015;

import java.awt.Font;
import edu.princeton.cs.introcs.*;
import java.awt.Color;

public class MenuTextItem {
	public String string;
	private int px;
	private int py;
	private Font font;
	private Color color;
	private Tag tag;
	
	public enum Tag {
		SINGLE_PLAYER_GAME, AI_GAME, OPTIONS
	}
	
	public MenuTextItem(int px, int py, String string, Font font, Tag tag) {
		this.string = string;
		this.px = px;
		this.py = py;
		this.font = font;
		color = Color.WHITE;
		this.tag = tag;
	}
	
	public void draw() {
		StdDraw.setPenColor(color);
		StdDraw.setFont(font);
		StdDraw.text(px, py, string);
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public int getPX() {
		return px;
	}
	
	public int getPY() {
		return py;
	}
	
	public Tag getTag() {
		return tag;
	}
}
