package com.neo_dev.PingPong.Element;

import com.neo_dev.PingPong.Configuration.GameConfiguration;
import com.neo_dev.PingPong.Model.Rectangle;

public class Paddle extends Rectangle {
    public Paddle(){
        setHeight(GameConfiguration.getPaddleHeight());
        setWidth(GameConfiguration.getPaddleWidth());
    }
    public void moveTo(int x, int y){
        positionX = x;
        positionY = y;
    }

    public void moveBy(int dx, int dy){
        positionX += dx;
        positionY += dy;
    }

}
