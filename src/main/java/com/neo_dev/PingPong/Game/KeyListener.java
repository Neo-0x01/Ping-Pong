package com.neo_dev.PingPong.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyListener extends KeyAdapter {
    private static final boolean[] typed = new boolean[1000];
    private static final boolean[] held = new boolean[1000];

    public static boolean isTyped(int code) {
        if (code < 0 || code >= typed.length) return false;
        boolean result = typed[code];
        typed[code] = false;
        return result;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= held.length) return;
        if (!held[e.getKeyCode()]) {
            typed[e.getKeyCode()] = true;
        }
        held[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= held.length) return;
        held[e.getKeyCode()] = false;     // ready for the next press
    }
}
