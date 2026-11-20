package com.neo_dev.PingPong.Player;

import com.neo_dev.PingPong.Element.Paddle;
import com.neo_dev.PingPong.Engine.Renderable;
import com.neo_dev.PingPong.Engine.Updatable;
import com.neo_dev.PingPong.Configuration.GameConfiguration;

import java.awt.Graphics2D;


public class Player implements Renderable, Updatable {
    int score;
    int speed;
    Paddle paddle;
    Movement movement;

    public Player(){
        this.speed = GameConfiguration.getPlayerSpeed();
        paddle = new Paddle();
        movement = new Movement(paddle,speed);
    }

    @Override
    public void update() {
        movement.update();
    }

    @Override
    public void render(Graphics2D graphics2D) {
        paddle.render(graphics2D);
    }
}
