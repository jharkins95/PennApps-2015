package com.example.jthem.PennApps2015;

public class GameObject {
    
    public enum Tag {
        ALLY, ENEMY
    }

    protected int posX;
    protected int posY;
    
    protected int velX;
    protected int velY;
    
    protected int r; // radius
    
    protected Tag tag;
    
    protected boolean visible;
    
    public GameObject(int pX, int pY, int vX, int vY, Tag t) {
        this.posX = pX;
        this.posY = pY;
        this.velX = vX;
        this.velY = vY;
        this.tag = t;
    }
    
    public boolean isVisible() {
        return true;
    }
    
    public void move() {
        posX += velX;
        posY += velY;
    }
    
    public void draw() {
        
    }
    
    public int getPosX() {
        return posX;
    }
    
    public int getPosY() {
        return posY;
    }
    
    public int getVelX() {
        return velX;
    }
    
    public int getVelY() {
        return velY;
    }

}
