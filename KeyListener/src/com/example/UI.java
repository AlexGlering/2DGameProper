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
    public String currentDialogue;

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
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);

        //Title State
        if(gamePanel.gameState == gamePanel.titleState){
            drawTitleScreen();
        }

        //Play state
        if(gamePanel.gameState == gamePanel.playState){
            //Do playstate stuff
        }
        //Pause state
        if(gamePanel.gameState == gamePanel.pauseState){
            drawPauseScreen();
        }
        //Dialogue state
        if(gamePanel.gameState == gamePanel.dialogueState){
            drawDialogueScreen();
        }
    }

    public void drawTitleScreen(){
        //Title screen color
        g2.setColor(new Color(30, 144, 255));
        g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        //Title name
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 70F));
        String text = "Blue Boy Adventure";
        int x = getXForCenteredText(text);
        int y = gamePanel.getTileSize()*3;

        //Text shadow
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+3, y+3);

        //Main text color
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        //Blue boy image
        x = gamePanel.getScreenWidth()/2 - ((gamePanel.getTileSize()*2)/2);
        y += gamePanel.getTileSize()*2;
        g2.drawImage(gamePanel.player.down1, x, y, gamePanel.getTileSize()*2, gamePanel.getTileSize()*2, null);

        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 35F));

        text = "NEW GAME";
        x = getXForCenteredText(text);
        y += gamePanel.getTileSize()*3.5;
        g2.drawString(text, x, y);

        text = "LOAD GAME";
        x = getXForCenteredText(text);
        y += gamePanel.getTileSize();
        g2.drawString(text, x, y);

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gamePanel.getTileSize();
        g2.drawString(text, x, y);

    }

    public void drawPauseScreen(){
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gamePanel.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        //window
        int x = gamePanel.getTileSize()*2;
        int y = gamePanel.getTileSize()/2;
        int width = gamePanel.getScreenWidth() - (gamePanel.getTileSize()*4);
        int height = gamePanel.getTileSize()*4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
        x += gamePanel.getTileSize();
        y += gamePanel.getTileSize();

        for(String line : currentDialogue.split("/n")){
            g2.drawString(line, x, y);
            y+=40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0,0,0,210); //a = opacity
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

    }


    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gamePanel.screenWidth/2 - length/2;
        return x;
    }
}
