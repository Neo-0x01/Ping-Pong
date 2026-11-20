package com.neo_dev.PingPong.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;


public class KeyInput extends KeyAdapter {
    private static final boolean[] keys = new boolean[1000];
    private static final boolean[] typed = new boolean[1000];

    public static boolean isPressed(int keyCode) {
        if (keyCode >= 0 && keyCode < keys.length) {
            return keys[keyCode];
        }
        return false;
    }

    public static boolean isTyped(int keyCode) {
        if (keyCode < 0 || keyCode >= typed.length) {return false;}
        boolean result = typed[keyCode];
        typed[keyCode] = false;
        return result;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {return;}
        if (!keys[e.getKeyCode()]) {
            typed[e.getKeyCode()] = true;
        }
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {return;}
        keys[e.getKeyCode()] = false;
        typed[e.getKeyCode()] = false;
    }
}
