package com.neo_dev.PingPong.Configuration;

import java.util.Properties;

import static com.neo_dev.PingPong.Configuration.Configuration.loadIntProperty;
import static com.neo_dev.PingPong.Configuration.Configuration.saveProperty;

public class AppConfiguration implements Config {
    private int width;
    private int height;

    @Override
    public void loadConfiguration(Properties prop){
        setWidth(loadIntProperty(prop, ConfigurationValues.WIDTH));
        setHeight(loadIntProperty(prop, ConfigurationValues.HEIGHT));
    }

    @Override
    public void saveConfiguration(Properties prop){
        saveProperty(prop, width,ConfigurationValues.WIDTH);
        saveProperty(prop, height,ConfigurationValues.HEIGHT);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
