package com.example;

import java.awt.*;

public class UI {

    GamePanel gamePanel;
    Graphics2D g2;
    Font arial_40, arial_60B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public UI(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_60B = new Font("Arial", Font.BOLD, 60);
    }

    public void showMessage(String text){
        this.message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if(gamePanel.gameState == gamePanel.playState){
            //Do playstate stuff
        }
        if(gamePanel.gameState == gamePanel.pauseState){
            drawPauseScreen();
        }
    }

    public void drawPauseScreen(){
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gamePanel.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gamePanel.screenWidth/2 - length/2;
        return x;
    }
}
