package com.example;

import Entity.Entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
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

        //Title state
        if(gamePanel.gameState == gamePanel.titleState) {
            if(gamePanel.ui.titleScreenState == 0){
                if (key == KeyEvent.VK_W) {
                    gamePanel.ui.commandNum--;
                    if(gamePanel.ui.commandNum<0){
                        gamePanel.ui.commandNum = 2;
                    }
                }
                if (key == KeyEvent.VK_S) {
                    gamePanel.ui.commandNum++;
                    if(gamePanel.ui.commandNum>2){
                        gamePanel.ui.commandNum = 0;
                    }
                }
                if (key == KeyEvent.VK_ENTER) {
                    switch (gamePanel.ui.commandNum){
                        case 0:
                            gamePanel.ui.titleScreenState = 1;
                            break;
                        case 1:
                            //add later
                            break;
                        case 2:
                            System.exit(0);
                            break;
                    }
                }
            }
            else if(gamePanel.ui.titleScreenState == 1){
                if (key == KeyEvent.VK_W) {
                    gamePanel.ui.commandNum--;
                    if(gamePanel.ui.commandNum<0){
                        gamePanel.ui.commandNum = 3;
                    }
                }
                if (key == KeyEvent.VK_S) {
                    gamePanel.ui.commandNum++;
                    if(gamePanel.ui.commandNum>3){
                        gamePanel.ui.commandNum = 0;
                    }
                }
                if (key == KeyEvent.VK_ENTER) {
                    switch (gamePanel.ui.commandNum) {
                        case 0 -> {
                            System.out.println("Do some fighter specific stuff");
                            gamePanel.gameState = gamePanel.playState;
                            gamePanel.playMusic(0);
                        }
                        case 1 -> {
                            System.out.println("Do some rogue specific stuff");
                            gamePanel.gameState = gamePanel.playState;
                            gamePanel.playMusic(0);
                        }
                        case 2 -> {
                            System.out.println("Do some sorcerer specific stuff");
                            gamePanel.gameState = gamePanel.playState;
                            gamePanel.playMusic(0);
                        }
                        case 3 -> gamePanel.ui.titleScreenState = 0;
                    }
                }
            }


        }

        //Play state
        if (gamePanel.gameState == gamePanel.playState) {
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
            if(key == KeyEvent.VK_ENTER) {
                enterPressed = true;
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

        //Pause state
        if(key == KeyEvent.VK_P) {
            if(gamePanel.gameState == gamePanel.playState){
                gamePanel.gameState = gamePanel.pauseState;
            } else if (gamePanel.gameState == gamePanel.pauseState){
                gamePanel.gameState = gamePanel.playState;
            }
        }

        //Dialogue state
        else if(gamePanel.gameState == gamePanel.dialogueState){
            if(key == KeyEvent.VK_ENTER){
                gamePanel.gameState = gamePanel.playState;
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
