package com.example;

import java.awt.*;

public class EventHandler {

    GamePanel gamePanel;
    EventRect[][] eventRect;

    public EventHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        //Setting small event trigger in the middle of tile meant for triggering
        eventRect = new EventRect[gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];

        int col = 0;
        int row = 0;
        while(col < gamePanel.getMaxWorldCol() && row < gamePanel.getMaxWorldRow()){
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gamePanel.getMaxWorldCol()){
                col = 0;
                row++;
            }
        }


    }

    public void checkEvent(){

        if(hit(27, 16, "right")){damagePit(gamePanel.dialogueState);}
        if(hit(27, 16, "right")){teleport(gamePanel.dialogueState);}
        if(hit(23, 12, "up")){healingPool(gamePanel.dialogueState);}

    }

    public boolean hit(int col, int row, String reqDirection){
        boolean hit = false;

        //getting player position
        gamePanel.player.collisionArea.x = gamePanel.player.worldX + gamePanel.player.collisionArea.x;
        gamePanel.player.collisionArea.y = gamePanel.player.worldY + gamePanel.player.collisionArea.y;

        //getting event trigger position
        eventRect[col][row].x = col* gamePanel.getTileSize() + eventRect[col][row].x;
        eventRect[col][row].y = row* gamePanel.getTileSize() + eventRect[col][row].y;

        //checking for collision
        if(gamePanel.player.collisionArea.intersects(eventRect[col][row])){
            if(gamePanel.player.direction.contentEquals(reqDirection) ||
            reqDirection.contentEquals("any")){
                hit = true;
            }
        }

        //after checking for collision, reset x and y on eventRect and collisionArea
        gamePanel.player.collisionArea.x = gamePanel.player.collisionAreaDefaultX;
        gamePanel.player.collisionArea.y = gamePanel.player.collisionAreaDefaultY;
        eventRect[col][row].x =  eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y =  eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    public void damagePit(int gameState){
        gamePanel.gameState = gameState;
        gamePanel.ui.currentDialogue = "You fell into a pit.";
        gamePanel.player.life -= 1;
    }

    public void healingPool(int gameState){

        if(gamePanel.keyHandler.enterPressed){
            gamePanel.gameState = gameState;
            gamePanel.ui.currentDialogue = "You feel refreshed. Life was restored.";
            gamePanel.player.life = gamePanel.player.maxLife;
        }
    }

    public void teleport(int gameState){
        gamePanel.gameState = gameState;
        gamePanel.player.worldX = gamePanel.getTileSize()*37;
        gamePanel.player.worldY = gamePanel.getTileSize()*10;

    }
}
