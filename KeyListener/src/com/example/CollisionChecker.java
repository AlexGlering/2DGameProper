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
                entityCollisionDirection(entity);
                if (entity.collisionArea.intersects(gamePanel.items[i].collisionArea)) {
                    if(gamePanel.items[i].collision){
                        entity.collisionOn = true;
                    }
                    if(player) {
                        index = i;
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

    public int checkEntity(Entity entity, Entity[] target){
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if(target[i] != null){

                //get entity's collisionArea position
                entity.collisionArea.x = entity.worldX + entity.collisionArea.x;
                entity.collisionArea.y = entity.worldY + entity.collisionArea.y;
                //get item's collisionArea position
                target[i].collisionArea.x = target[i].worldX + target[i].collisionArea.x;
                target[i].collisionArea.y = target[i].worldY + target[i].collisionArea.y;

                //simulating entity movement and check where it will be, after it moved
                entityCollisionDirection(entity);
                if (entity.collisionArea.intersects(target[i].collisionArea)) {
                    if(target[i] != entity){
                        entity.collisionOn = true;
                        index = i;
                    }
                }

                //resetting positions
                entity.collisionArea.y = entity.collisionAreaDefaultY;
                entity.collisionArea.x = entity.collisionAreaDefaultX;
                target[i].collisionArea.x = target[i].collisionAreaDefaultX;
                target[i].collisionArea.y = target[i].collisionAreaDefaultY;
            }
        }
        return  index;
    }

    public boolean checkPlayer(Entity entity){

        boolean contactPlayer = false;

        //get entity's collisionArea position
        entity.collisionArea.x = entity.worldX + entity.collisionArea.x;
        entity.collisionArea.y = entity.worldY + entity.collisionArea.y;
        //get item's collisionArea position
        gamePanel.player.collisionArea.x = gamePanel.player.worldX + gamePanel.player.collisionArea.x;
        gamePanel.player.collisionArea.y = gamePanel.player.worldY + gamePanel.player.collisionArea.y;

        //simulating entity movement and check where it will be, after it moved
        entityCollisionDirection(entity);

        if (entity.collisionArea.intersects(gamePanel.player.collisionArea)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }

        //resetting positions
        entity.collisionArea.y = entity.collisionAreaDefaultY;
        entity.collisionArea.x = entity.collisionAreaDefaultX;
        gamePanel.player.collisionArea.x = gamePanel.player.collisionAreaDefaultX;
        gamePanel.player.collisionArea.y = gamePanel.player.collisionAreaDefaultY;

        return contactPlayer;

    }

    private void entityCollisionDirection(Entity entity) {
        switch (entity.direction) {
            case "up" -> entity.collisionArea.y -= entity.speed;
            case "down" -> entity.collisionArea.y += entity.speed;
            case "left" -> entity.collisionArea.x -= entity.speed;
            case "right" -> entity.collisionArea.x += entity.speed;
        }
    }
}
