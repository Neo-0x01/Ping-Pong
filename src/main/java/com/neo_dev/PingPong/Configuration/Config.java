package com.neo_dev.PingPong.Configuration;

import java.util.Properties;

public interface Config {
    void loadConfiguration(Properties properties);
    void saveConfiguration(Properties properties);
}
