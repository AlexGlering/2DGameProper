package com.example;

import java.awt.*;

public class EventHandler {

    GamePanel gamePanel;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        //Setting small event trigger in the middle of tile meant for triggering
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent(){

        if(hit(27, 16, "right")){damagePit(gamePanel.dialogueState);}
        if(hit(27, 16, "right")){teleport(gamePanel.dialogueState);}
        if(hit(23, 12, "up")){healingPool(gamePanel.dialogueState);}

    }

    public boolean hit(int eventCol, int eventRow, String reqDirection){
        boolean hit = false;

        //getting player position
        gamePanel.player.collisionArea.x = gamePanel.player.worldX + gamePanel.player.collisionArea.x;
        gamePanel.player.collisionArea.y = gamePanel.player.worldY + gamePanel.player.collisionArea.y;

        //getting event trigger position
        eventRect.x = eventCol* gamePanel.getTileSize() + eventRect.x;
        eventRect.y = eventRow* gamePanel.getTileSize() + eventRect.y;

        //checking for collision
        if(gamePanel.player.collisionArea.intersects(eventRect)){
            if(gamePanel.player.direction.contentEquals(reqDirection) ||
            reqDirection.contentEquals("any")){
                hit = true;
            }
        }

        //after checking for collision, reset x and y on eventRect and collisionArea
        gamePanel.player.collisionArea.x = gamePanel.player.collisionAreaDefaultX;
        gamePanel.player.collisionArea.y = gamePanel.player.collisionAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

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
