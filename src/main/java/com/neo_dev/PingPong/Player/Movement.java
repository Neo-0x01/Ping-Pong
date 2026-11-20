package com.neo_dev.PingPong.Player;

import com.neo_dev.PingPong.Element.Paddle;
import com.neo_dev.PingPong.Game.KeyInput;
import com.neo_dev.PingPong.Engine.Updatable;

import java.awt.event.KeyEvent;

public class Movement implements Updatable{
    Paddle paddle;
    int speed;

    public Movement(Paddle paddle,int speed ){
        this.paddle = paddle;
        this.speed = speed;
    }

    public void moveUp(){
        paddle.moveBy(0,-speed);
    }

    public void moveDown(){
        paddle.moveBy(0,speed);
    }

    public void moveRight(){
        paddle.moveBy(speed,0);
    }

    public void moveLeft(){
        paddle.moveBy(-speed,0);
    }

    public void moveTo(int x , int y){
        paddle.moveTo(x,y);
    }

    public void moveBy(int x , int y){
        paddle.moveBy(x,y);
    }

    @Override
    public void update() {
        if (KeyInput.isPressed(KeyEvent.VK_W) || KeyInput.isPressed(KeyEvent.VK_UP)) {
            if(!(this.paddle.getTop()<=0))
            {this.moveUp();}
        }
        if (KeyInput.isPressed(KeyEvent.VK_S) || KeyInput.isPressed(KeyEvent.VK_DOWN)) {
            if(!(this.paddle.getBottom()>=710)){
                this.moveDown();
            }
        }
        if (KeyInput.isPressed(KeyEvent.VK_A) || KeyInput.isPressed(KeyEvent.VK_LEFT)) {
            if(!(this.paddle.getLeft()<=0)){
                this.moveLeft();
            }
        }
        if (KeyInput.isPressed(KeyEvent.VK_D) || KeyInput.isPressed(KeyEvent.VK_RIGHT)) {
            if(!(this.paddle.getRight()>=1280)){
                this.moveRight();
            }
        }
    }
}
