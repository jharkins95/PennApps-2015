package com.example.jthem.PennApps2015;

public class GameObject {

    private int posX;
    private int posY;
    
    private int velX;
    private int velY;
    
    boolean visible;
    
    public GameObject(int pX, int pY, int vX, int vY) {
        this.posX = pX;
        this.posY = pY;
        this.velX = vX;
        this.velY = vY;
    }
    
    public boolean isVisible() {
        return true;
    }
    
    public void draw() {
        
    }

}
