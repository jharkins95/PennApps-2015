package com.example.jthem.PennApps2015;

public class GameObject {
    
    public final int BUFFER = 100;
    
    public enum Tag {
        ALLY, ENEMY
    }

    protected double posX;
    protected double posY;
    
    protected double velX;
    protected double velY;
    
    protected double r; // radius
    
    protected Tag tag;
    
    protected boolean visible;
    
    public GameObject(double pX, double pY, double vX, double vY, double r, Tag t) {
        this.posX = pX;
        this.posY = pY;
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
    
    public boolean collide(GameObject other) {
        return Math.sqrt(Math.pow(posX - other.posX, 2) + 
                Math.pow(posY - other.posY, 2)) < (r + other.r);
    }
    
    public void draw() {
     // by default, does nothing
    }
    
    public double getPosX() {
        return posX;
    }
    
    public double getPosY() {
        return posY;
    }
    
    public double getVelX() {
        return velX;
    }
    
    public double getVelY() {
        return velY;
    }

}
