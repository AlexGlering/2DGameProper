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
    public int checkItem(Entity entity, boolean player){
        int index = 999;

        for (int i = 0; i < gamePanel.items.length; i++) {
            if(gamePanel.items[i] != null){

                //get entity's collisionArea position
                entity.collisionArea.x = entity.worldX + entity.collisionArea.x;
                entity.collisionArea.y = entity.worldY + entity.collisionArea.y;
                //get item's collisionArea position
                gamePanel.items[i].collisionArea.x = gamePanel.items[i].worldX + gamePanel.items[i].collisionArea.x;
                gamePanel.items[i].collisionArea.y = gamePanel.items[i].worldY + gamePanel.items[i].collisionArea.y;

                //simulating entity movement and check where it will be, after it moved
                switch (entity.direction) {
                    case "up" -> {
                        entity.collisionArea.y -= entity.speed;
                        if (entity.collisionArea.intersects(gamePanel.items[i].collisionArea)) {
                            //System.out.println("Up collision");
                            if(gamePanel.items[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    }
                    case "down" -> {
                        entity.collisionArea.y += entity.speed;
                        if (entity.collisionArea.intersects(gamePanel.items[i].collisionArea)) {
                            //System.out.println("Down collision");
                            if(gamePanel.items[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    }
                    case "left" -> {
                        entity.collisionArea.x -= entity.speed;
                        if (entity.collisionArea.intersects(gamePanel.items[i].collisionArea)) {
                            //System.out.println("Left collision");
                            if(gamePanel.items[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    }
                    case "right" -> {
                        entity.collisionArea.x += entity.speed;
                        if (entity.collisionArea.intersects(gamePanel.items[i].collisionArea)) {
                            //System.out.println("Right collision");
                            if(gamePanel.items[i].collision){
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = i;
                            }
                        }
                    }
                }

                //resetting positions
                entity.collisionArea.y = entity.collisionAreaDefaultY;
                entity.collisionArea.x = entity.collisionAreaDefaultX;
                gamePanel.items[i].collisionArea.x = gamePanel.items[i].collisionAreaDefaultX;
                gamePanel.items[i].collisionArea.y = gamePanel.items[i].collisionAreaDefaultY;
            }
        }
        return  index;
    }
}
