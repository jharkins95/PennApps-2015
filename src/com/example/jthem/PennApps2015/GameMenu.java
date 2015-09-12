package com.example.jthem.PennApps2015;

import edu.princeton.cs.introcs.*;
import java.awt.Font;
import java.util.ArrayList;
import java.util.ListIterator;

import com.example.jthem.PennApps2015.MenuTextItem.Tag;

public class GameMenu {
	
	private MenuTextItem currentCursorItem;
	private ArrayList<MenuTextItem> textItems;
	private ListIterator<MenuTextItem> textItemsIter;
	
	public GameMenu() {
		textItems = new ArrayList<MenuTextItem>();
		currentCursorItem = new MenuTextItem(400, 400, "Single Player Game",
			      new Font(Font.SANS_SERIF, Font.BOLD, 60), Tag.SINGLE_PLAYER_GAME);
		textItems.add(currentCursorItem);
		textItems.add(new MenuTextItem(400, 300, "AI Game",
			      new Font(Font.SANS_SERIF, Font.BOLD, 60), Tag.AI_GAME));
		textItems.add(new MenuTextItem(400, 200, "Options",
			      new Font(Font.SANS_SERIF, Font.BOLD, 60), Tag.OPTIONS));
		textItemsIter = textItems.listIterator();
		textItemsIter.next();
	}
	public void draw() {
		StdDraw.clear(StdDraw.BLACK);
		StdDraw.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
		for (MenuTextItem textItem : textItems) {
			if (textItem == currentCursorItem) {
				textItem.setColor(StdDraw.GREEN);
			} else {
				textItem.setColor(StdDraw.WHITE);
			}
			textItem.draw();
		}
	}
	
	public void updateMenuCursor(MenuTextItem item) {
		currentCursorItem = item;
	}
	
	public ListIterator<MenuTextItem> getTextItemsIter() {
		return textItemsIter;
	}
	
	public MenuTextItem getCurrentCursorItem() {
		return currentCursorItem;
	}
	
	public ArrayList<MenuTextItem> getMenuItems() {
		return textItems;
	}
}
