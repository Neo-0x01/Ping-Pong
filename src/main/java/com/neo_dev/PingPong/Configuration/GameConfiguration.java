package com.neo_dev.PingPong.Configuration;

import java.util.Properties;

import static com.neo_dev.PingPong.Configuration.Configuration.loadIntProperty;
import static com.neo_dev.PingPong.Configuration.Configuration.saveProperty;

public class GameConfiguration implements Config {

    private static int playerSpeed;
    private static int paddleHeight;
    private static int paddleWidth;

    @Override
    public void loadConfiguration(Properties properties) {
        setPlayerSpeed(loadIntProperty(properties,ConfigurationValues.PLAYER_SPEED));
        setPaddleHeight(loadIntProperty(properties,ConfigurationValues.PADDLE_HEIGHT));
        setPaddleWidth(loadIntProperty(properties,ConfigurationValues.PADDLE_WIDTH));
    }

    @Override
    public void saveConfiguration(Properties properties) {
        saveProperty(properties,getPlayerSpeed(),ConfigurationValues.PLAYER_SPEED);
        saveProperty(properties,getPaddleHeight(),ConfigurationValues.PADDLE_HEIGHT);
        saveProperty(properties,getPaddleWidth(),ConfigurationValues.PADDLE_WIDTH);
    }

    public static int getPlayerSpeed() {
        return playerSpeed;
    }

    public static void setPlayerSpeed(int playerSpeed) {
        GameConfiguration.playerSpeed = playerSpeed;
    }

    public static int getPaddleHeight() {
        return paddleHeight;
    }

    public static void setPaddleHeight(int paddleHeight) {
        GameConfiguration.paddleHeight = paddleHeight;
    }

    public static int getPaddleWidth() {
        return paddleWidth;
    }

    public static void setPaddleWidth(int paddleWidth) {
        GameConfiguration.paddleWidth = paddleWidth;
    }
}
