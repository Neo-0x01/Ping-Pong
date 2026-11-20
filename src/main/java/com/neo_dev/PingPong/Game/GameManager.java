package com.neo_dev.PingPong.Game;

import com.neo_dev.PingPong.Configuration.Configuration;
import com.neo_dev.PingPong.Engine.Renderable;
import com.neo_dev.PingPong.Engine.Updatable;
import com.neo_dev.PingPong.Player.Player;
import com.neo_dev.PingPong.Configuration.GameConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.Timer;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

public class GameManager {
    private static final Logger logger = LoggerFactory.getLogger(GameManager.class);

    ArrayList<Renderable> renderableObjects = new ArrayList<>();
    ArrayList<Updatable> updatableObjects = new ArrayList<>();
    Timer gametimer;
    GamePanel gamePanel;
    GameConfiguration gameConfig;
    public GameManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void start() throws IOException {
        gameConfig = (GameConfiguration) Configuration.loadOrCreate(new GameConfiguration(),"config\\GameConfig.properties");
        Player player = new Player();
        renderableObjects.add(player);
        updatableObjects.add(player);
        updatableObjects.add(gamePanel);
        loop();
        gametimer.start();
        if(logger.isInfoEnabled()) {
            logger.info("Game Loop Started.");
        }
    }

    public void loop(){
        int delay = 16;
        gametimer  = new Timer(delay, event -> gamePanel.repaint());
    }

    public void render(Graphics2D g2d){
        update();
        renderableObjects.forEach(object -> object.render(g2d));
    }

    public void update(){
        updatableObjects.forEach(Updatable::update);
    }

    public void handleClosing(){
        try {
            Configuration.save(gameConfig,"config\\GameConfig.properties");
            if(logger.isInfoEnabled()) {
                logger.info("Game Configuration saved,");
            }
        }catch (IOException ex){
            if(logger.isErrorEnabled()){
                logger.error("Cannot save Game Configuration.");
            }
        }
    }
}
