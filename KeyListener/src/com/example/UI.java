package com.example;

import Item.ITEM_KEY;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gamePanel;
    Font arial_40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    public UI(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        ITEM_KEY key = new ITEM_KEY();
        keyImage = key.image;
    }

    public void showMessage(String text){
        this.message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawImage(keyImage, gamePanel.getTileSize()/2, gamePanel.getTileSize()/2,
                gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        g2.drawString("x " + gamePanel.player.hasKey, 74, 65);

        //Message
        if(messageOn){
            g2.setFont(g2.getFont().deriveFont(20F));
            g2.drawString(message, gamePanel.getTileSize()/2, gamePanel.getTileSize()*2);

            messageCounter++;

            //message disappears after 2*60FPS
            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }
        }

    }
}
