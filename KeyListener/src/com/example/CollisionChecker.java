package com.example;

import Entity.Entity;

public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.collisionArea.x;
        int entityRightWorldX = entity.worldX + entity.collisionArea.x + entity.collisionArea.width;
        int entityTopWorldY = entity.worldY + entity.collisionArea.y;
        int entityBottomWorldY = entity.worldY + entity.collisionArea.y + entity.collisionArea.height;

        int entityLeftCol = entityLeftWorldX/gamePanel.getTileSize();
        int entityRightCol = entityRightWorldX/gamePanel.getTileSize();
        int entityTopRow = entityTopWorldY/gamePanel.getTileSize();
        int entityBottomRow = entityBottomWorldY/gamePanel.getTileSize();

        int tileNum1, tileNum2;

        switch (entity.direction){
            case "up":
               entityTopRow = (entityTopWorldY - entity.speed)/gamePanel.getTileSize();
               tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
               tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];

               if(gamePanel.tileManager.tile[tileNum1].collision ||
                       gamePanel.tileManager.tile[tileNum2].collision){
                   entity.collisionOn = true;}
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gamePanel.getTileSize();
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];

                if(gamePanel.tileManager.tile[tileNum1].collision ||
                        gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;}
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gamePanel.getTileSize();
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];

                if(gamePanel.tileManager.tile[tileNum1].collision ||
                        gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;}
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gamePanel.getTileSize();
                tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];

                if(gamePanel.tileManager.tile[tileNum1].collision ||
                        gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;}
                break;
        }
    }
}
