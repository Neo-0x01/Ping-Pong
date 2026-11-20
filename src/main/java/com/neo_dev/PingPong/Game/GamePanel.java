package com.neo_dev.PingPong.Game;

import com.neo_dev.PingPong.Engine.Updatable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static java.lang.Math.min;
import com.neo_dev.PingPong.app.WindowInitialize;

public class GamePanel extends JPanel implements Updatable{
    private static final Logger logger = LoggerFactory.getLogger(GamePanel.class);
    GameManager gameManager = new GameManager(this);
    private final JFrame mainFrame;
    public static final int GAME_WIDTH = 700;
    public static final int GAME_HEIGHT = 450;

    public GamePanel (JFrame mainFrame) throws IOException {
        this.mainFrame = mainFrame;
        if(logger.isInfoEnabled()) {
            logger.info("Starting GameManager.");
        }
        gameManager.start();
        this.setFocusable(true); // Crucial for receiving key events
        this.requestFocusInWindow();
        this.addKeyListener(new KeyInput()); // Link the handler
        this.addKeyListener(new KeyListener());
        Dimension maxDimensions = mainFrame.getSize();
        this.setPreferredSize(new Dimension(1280,720));
        this.setMaximumSize(new Dimension(1280,720));
        this.setMinimumSize(new Dimension(700,450));
        this.setSize(new Dimension(1280,720));
        mainFrame.setMinimumSize(new Dimension(1280,720));
//        logger.info("Size:"+this.getSize().height+ " : "+ this.getSize().width);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight()-2);
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRect(0,0, getWidth()-2, getHeight()-2);

        gameManager.render(g2d);
    }

    public void handleClosing(){
        gameManager.handleClosing();
    }

    public void updateSize(){
        WindowInitialize.setMinimumSize(new Dimension(700,450));
    }

    @Override
    public void update(){
        if(KeyInput.isTyped(KeyEvent.VK_ESCAPE)){
            System.out.println("pressed");
            WindowInitialize.setFullScreen();
        }
    }

}
