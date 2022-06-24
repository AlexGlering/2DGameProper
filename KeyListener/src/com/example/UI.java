package com.example;

import Item.ITEM_KEY;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gamePanel;
    Font arial_40, arial_60B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public UI(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_60B = new Font("Arial", Font.BOLD, 60);
        ITEM_KEY key = new ITEM_KEY();
        keyImage = key.image;
    }

    public void showMessage(String text){
        this.message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        if(gameFinished){
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);

            String text;
            int textLength;
            int x;
            int y;

            //First final message
            text = "You got the treasure";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = (gamePanel.screenWidth/2) - (textLength/2);
            y = gamePanel.screenHeight/2 - gamePanel.tileSize*3;
            g2.drawString(text, x, y);

            //Second final message
            g2.setFont(arial_60B);
            g2.setColor(Color.ORANGE);
            text = "CONGRATULATIONS!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = (gamePanel.screenWidth/2) - (textLength/2);
            y = gamePanel.screenHeight/2 + gamePanel.tileSize*2;
            g2.drawString(text, x, y);

            //Stop the game
            gamePanel.gameThread = null;

        } else {
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gamePanel.getTileSize()/2, gamePanel.getTileSize()/2,
                    gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            g2.drawString("x " + gamePanel.player.hasKey, 74, 65);

            //Message
            if(messageOn){
                g2.setFont(g2.getFont().deriveFont(20F)); //setting font size for message string
                g2.drawString(message, gamePanel.getTileSize()/2, gamePanel.getTileSize()*2); //setting message placement

                messageCounter++;

                //message disappears after 2*60FPS
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
