package com.example;

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

    }
}
