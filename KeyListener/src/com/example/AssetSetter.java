package com.example;

import Item.ITEM_BOOTS;
import Item.ITEM_CHEST;
import Item.ITEM_DOOR;
import Item.ITEM_KEY;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setItem(){
        //instantiating item
        gamePanel.items[0] = new ITEM_KEY();
        //setting item position on map
        gamePanel.items[0].worldX= 23 * gamePanel.getTileSize();
        gamePanel.items[0].worldY= 7 * gamePanel.getTileSize();

        gamePanel.items[1] = new ITEM_KEY();
        gamePanel.items[1].worldX= 23 * gamePanel.getTileSize();
        gamePanel.items[1].worldY= 40 * gamePanel.getTileSize();

        gamePanel.items[2] = new ITEM_KEY();
        gamePanel.items[2].worldX= 38 * gamePanel.getTileSize();
        gamePanel.items[2].worldY= 8 * gamePanel.getTileSize();

        gamePanel.items[3] = new ITEM_DOOR();
        gamePanel.items[3].worldX= 10 * gamePanel.getTileSize();
        gamePanel.items[3].worldY= 11 * gamePanel.getTileSize();

        gamePanel.items[4] = new ITEM_DOOR();
        gamePanel.items[4].worldX= 8 * gamePanel.getTileSize();
        gamePanel.items[4].worldY= 28 * gamePanel.getTileSize();

        gamePanel.items[5] = new ITEM_DOOR();
        gamePanel.items[5].worldX= 12 * gamePanel.getTileSize();
        gamePanel.items[5].worldY= 22 * gamePanel.getTileSize();

        gamePanel.items[6] = new ITEM_CHEST();
        gamePanel.items[6].worldX= 10 * gamePanel.getTileSize();
        gamePanel.items[6].worldY= 7 * gamePanel.getTileSize();

        gamePanel.items[7] = new ITEM_BOOTS();
        gamePanel.items[7].worldX= 37 * gamePanel.getTileSize();
        gamePanel.items[7].worldY= 42 * gamePanel.getTileSize();


    }
}
