package com.example.jthem.PennApps2015;

public class GameObject {
    
    public final int BUFFER = 100;
    
    public enum Tag {
        ALLY, ENEMY
    }

    protected int posX;
    protected int posY;
    
    protected double dPosX;
    protected double dPosY;
    
    protected int velX;
    protected int velY;
    
    protected int r; // radius
    
    protected Tag tag;
    
    protected boolean visible;
    
    public GameObject(int pX, int pY, int vX, int vY, int r, Tag t) {
        this.posX = pX;
        this.posY = pY;
        this.dPosX = (double) pX;
        this.dPosY = (double) pY;
        visible = true;
        this.r = r;
        this.velX = vX;
        this.velY = vY;
        this.tag = t;
    }
    
    public boolean isVisible() {
        return (posX > -BUFFER && posX < Canvas.MAX_X_RES + BUFFER) && 
               (posY > -BUFFER && posY < Canvas.MAX_Y_RES + BUFFER);
    }
    
    public void move() {
        posX += velX;
        posY += velY;
        clip();
    }
    
    public void clip() {
        // by default, does nothing
    }
    
    public void updateDoublePosition() {
        dPosX = (double) posX;
        dPosY = (double) posY;
    }
    
    public boolean collide(GameObject other) {
        return Math.sqrt(Math.pow(dPosX - other.dPosX, 2) + 
                Math.pow(dPosY - other.dPosY, 2)) < (r + other.r);
    }
    
    public void draw() {
     // by default, does nothing
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
