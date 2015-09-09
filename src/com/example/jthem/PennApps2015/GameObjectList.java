package com.example.jthem.PennApps2015;

import java.util.ArrayList;
import java.util.Iterator;

public class GameObjectList {

    private ArrayList<GameObject> list;
    
    public GameObjectList() {
        list = new ArrayList<GameObject>();
    }
    
    public GameObjectList(GameObject obj) {
    	list = new ArrayList<GameObject>();
    	list.add(obj);
    }
    
    public void add(GameObject go) {
        list.add(go);
    }
    
    public void drawList() {
        for (GameObject go : list)
            go.draw();
    }
    
    public void moveList() {
        Iterator<GameObject> iter = list.listIterator();
        while (iter.hasNext()) {
            GameObject go = iter.next();
            go.move();
            if (!go.isVisible())
                iter.remove();
        }
    }
    
    public Iterator<GameObject> getIterator() {
        return list.listIterator();
    }
    
    // supposed to be used from player bullets list with enemy list as argument
    public void checkCollisions(GameObjectList gol) {
        Iterator<GameObject> here = list.listIterator();
        while (here.hasNext()) {
            GameObject hereObj = here.next();
            Iterator<GameObject> other = gol.getIterator();
            while (other.hasNext()) {
                GameObject otherObj = other.next();
                if (hereObj.collide(otherObj)) {
                    here.remove();
                    other.remove();
                    break;
                    // TODO: do death animations stuff?
                }
            }
        }
    }
    
    // supposed to be used from an enemy list with the player as an argument
    public boolean checkCollisions(Player pl) {
        Iterator<GameObject> here = list.listIterator();
        while (here.hasNext()) {
            // if an enemy collides with player
            if (here.next().collide(pl) && !pl.isImmune()) {
                pl.kill();
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<GameObject> getList() {
    	return list;
    }
    
    public int size() {
    	return list.size();
    }
}
