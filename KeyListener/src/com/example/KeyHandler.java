package com.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    //Debug
    boolean checkDrawTime = false;
    public GamePanel gamePanel;

    public KeyHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Not in use
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(key == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(key == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(key == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if(key == KeyEvent.VK_P) {
            if(gamePanel.gameState == gamePanel.playState){
                gamePanel.gameState = gamePanel.pauseState;
            } else if (gamePanel.gameState == gamePanel.pauseState){
                gamePanel.gameState = gamePanel.playState;
            }
        }

        //Debug
        if(key == KeyEvent.VK_T) {
            if(!checkDrawTime){
                checkDrawTime = true;
            }
            else if(checkDrawTime){
                checkDrawTime = false;
            }
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(key == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(key == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(key == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
