package com.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        //Not in use
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W) {
            upPressed = true;
            System.out.println("up");
        }
        if(key == KeyEvent.VK_S) {
            downPressed = true;
            System.out.println("down");
        }
        if(key == KeyEvent.VK_A) {
            leftPressed = true;
            System.out.println("left");
        }
        if(key == KeyEvent.VK_D) {
            rightPressed = true;
            System.out.println("right");
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W) {
            upPressed = false;
            System.out.println("up released");
        }
        if(key == KeyEvent.VK_S) {
            downPressed = false;
            System.out.println("down released");
        }
        if(key == KeyEvent.VK_A) {
            leftPressed = false;
            System.out.println("left released");
        }
        if(key == KeyEvent.VK_D) {
            rightPressed = false;
            System.out.println("right released");
        }
    }
}
