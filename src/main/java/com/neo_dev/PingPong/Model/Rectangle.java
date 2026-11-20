package com.neo_dev.PingPong.Model;

import com.neo_dev.PingPong.Engine.Renderable;

import java.awt.*;

public abstract class Rectangle implements Renderable {
    protected int positionX;
    protected int positionY;

    private int height;
    private int width;

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLeft(){
        return getPositionX()-(getWidth()/2);
    }

    public int getRight(){
        return getPositionX()+(getWidth()/2);
    }

    public int getTop(){
        return getPositionY()-(getHeight()/2);
    }

    public int getBottom(){
        return getPositionY()+(getHeight()/2);
    }

    @Override
    public void render(Graphics2D g2d){
        g2d.setColor(Color.WHITE);
        g2d.fillRect(getLeft(),getTop(),getWidth(),getHeight());
        g2d.setColor(Color.GREEN);
        g2d.fillRoundRect(positionX-2,positionY-2,4,4,300,300);
    }

}
