package com.neo_dev.PingPong.app;

import com.formdev.flatlaf.FlatDarkLaf;
import com.neo_dev.PingPong.Configuration.AppConfiguration;
import com.neo_dev.PingPong.Configuration.Configuration;
import com.neo_dev.PingPong.Game.GamePanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class WindowInitialize{
    private static final Logger logger = LoggerFactory.getLogger(WindowInitialize.class);
    private static JFrame frame;
    private static AppConfiguration appConfig;
    private GamePanel gamePanel;

    public void start() throws Exception {
        if(logger.isInfoEnabled()) {
            logger.info("Initializing window(JFrame).");
        }
        appConfig = (AppConfiguration) Configuration.loadOrCreate(new AppConfiguration(),"config\\AppConfig.properties");
        SwingUtilities.invokeLater(() -> initializeFrame());
    }

    private void initializeFrame() {
        setTheme();
        frame = new JFrame();
        device = frame.getGraphicsConfiguration().getDevice();

//        frame.getRootPane().putClientProperty("FlatLaf.fullWindowContent", true);
//        frame.getRootPane().putClientProperty(
//                FlatClientProperties.TITLE_BAR_BACKGROUND,
//                new Color(0, 0, 0, 0)
//        );

        setTitle("Hello World");
        setSize();
        try {
            gamePanel = new GamePanel(frame);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setLayoutCenter();
        frame.add(gamePanel);
        setImportantStuff();
        frame.setVisible(true);
        gamePanel.updateSize();
    }

    private void setSize() {
        frame.setPreferredSize(new Dimension(appConfig.getWidth(),appConfig.getHeight()));
        frame.setSize(new Dimension(appConfig.getWidth(),appConfig.getHeight()));
    }

    private void setLayoutCenter(){
        SpringLayout layout = new SpringLayout();
        frame.setLayout(layout);
        Container contentPane = frame.getContentPane();
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, gamePanel, 0, SpringLayout.VERTICAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, gamePanel, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
    }

    public void setImportantStuff(){
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleClosing();
            }
        });
//        frame.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//               updateSize();
//            }
//        });
    }

    public void setTheme(){
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
    }

    private void setTitle(String title){
        frame.setTitle(title);
    }

    public void handleClosing(){
        try {
            Configuration.save(appConfig,"config\\AppConfig.properties");
            if(logger.isInfoEnabled()) {
                logger.info("Application Configuration saved,");
            }
        }catch (IOException ex){
            if(logger.isErrorEnabled()){
                logger.error("Cannot save Application Configuration.");
            }
        }
        gamePanel.handleClosing();
        System.exit(0);
    }

    public static void setMinimumSize(Dimension dimensionSize){
        Insets insets = frame.getInsets();
//        logger.info("dimensionSize: "+dimensionSize);
        Dimension buttonSize = UIManager.getDimension("TitlePane.buttonSize");
        int addedWidth = insets.left + insets.right;
        int addedHeight = insets.top + insets.bottom + buttonSize.height;
//        logger.info("Added: "+addedWidth+" : "+addedHeight);
        frame.setMinimumSize(new Dimension((int) dimensionSize.getWidth()+addedWidth, (int) dimensionSize.getHeight()+addedHeight));
    }

    public static JFrame getFrame(){
        return frame;
    }

    private static boolean isFullScreen;
    private static Rectangle windowedBounds;
    private static GraphicsDevice device;

    public static void setFullScreen2() {
        frame.dispose();                                     // must happen before changing decorations
        frame.setUndecorated(true);
        frame.setBounds(frame.getGraphicsConfiguration().getBounds()); // cover the current monitor
        frame.setVisible(true);
    }

    public static void setFullScreen() {
        if(!isFullScreen){
            frame.dispose();
            frame.setUndecorated(true);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice device = ge.getDefaultScreenDevice();

            device.setFullScreenWindow(frame);
            isFullScreen=!isFullScreen;
        }else {
            frame.dispose();
            frame.setUndecorated(false);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice device = ge.getDefaultScreenDevice();

            device.setFullScreenWindow(null);
            frame.setVisible(true);

            isFullScreen=!isFullScreen;
        }
    }

}