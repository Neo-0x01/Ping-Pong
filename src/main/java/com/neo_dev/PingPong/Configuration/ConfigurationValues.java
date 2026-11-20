package com.neo_dev.PingPong.Configuration;

public enum ConfigurationValues {
    FOLDER("config/", "config/"),
    WIDTH("width", 1280),
    HEIGHT("height",720),
    PLAYER_SPEED("playerSpeed",10),
    PADDLE_HEIGHT("paddleHeight",50),
    PADDLE_WIDTH("paddleWidth",15);

    private final String key;
    private final Object defaultValue;
    ConfigurationValues(String key, Object defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String key() {
        return key;
    }

    public Object defaultValue() {
        return defaultValue;
    }
}
