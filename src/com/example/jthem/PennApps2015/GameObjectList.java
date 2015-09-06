package com.example.jthem.PennApps2015;

import java.util.ArrayList;
import java.util.Iterator;

public class GameObjectList {

    private ArrayList<GameObject> list;
    
    public GameObjectList() {
        list = new ArrayList<GameObject>();
    }
    
    public void add(GameObject go) {
        list.add(go);
    }
    
    public void drawList() {
        /*
        Iterator<GameObject> iter = list.listIterator();
        while (iter.hasNext()) {
            iter.next().draw();
        }*/
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

}
