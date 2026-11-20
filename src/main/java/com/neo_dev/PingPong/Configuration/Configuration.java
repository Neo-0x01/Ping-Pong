package com.neo_dev.PingPong.Configuration;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Configuration {
    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);

    public static Config loadOrCreate(Config config, String fileName)throws IOException{
        Properties prop = new Properties();
        Path configPath = Paths.get(fileName);

        // Create File if not found.
        if(!Files.exists(configPath)){
            createFile(configPath);
        }

        // Load File
        try (InputStream input = Files.newInputStream(configPath)) {
            prop.load(input);
            config.loadConfiguration(prop);
            if (logger.isInfoEnabled()) {
                logger.info("{} loaded", config.getClass().getName());
            }
        }
        return config;
    }

    public static void save(@NotNull Config config, String fileName) throws IOException {
        Properties props = new Properties();
        try (OutputStream file = Files.newOutputStream(Paths.get(fileName))) {
            config.saveConfiguration(props);
            props.store(file, "Application Preferences");
        }
    }

    private static void createFile(@NotNull Path file) throws IOException {
        Files.createDirectories(file.getParent());
        Files.createFile(file);
    }

    public static int loadIntProperty(Properties properties, ConfigurationValues configuration){
        return  Integer.parseInt(properties.getOrDefault(configuration.key(),configuration.defaultValue()).toString());
    }

    public static void saveProperty(Properties properties, int value, ConfigurationValues configuration){
        properties.setProperty(configuration.key(),String.valueOf(value));
    }
}
