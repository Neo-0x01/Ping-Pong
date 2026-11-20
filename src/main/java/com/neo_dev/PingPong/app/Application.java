package com.neo_dev.PingPong.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void launch(){
        if (logger.isInfoEnabled()) {
            logger.info("Application Started.");
        }
        WindowInitialize windowInitialize = new WindowInitialize();
        try {
            windowInitialize.start();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
