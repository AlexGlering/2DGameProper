package com.example;

import Entity.NPC_OldMan;
import Item.ITEM_DOOR;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setItem() {
        gamePanel.items[0] = new ITEM_DOOR(gamePanel);
        gamePanel.items[0].worldX = gamePanel.getTileSize()*23;
        gamePanel.items[0].worldY = gamePanel.getTileSize()*25;
    }

    public void setNPC() {
        gamePanel.npcs[0] = new NPC_OldMan(gamePanel);
        gamePanel.npcs[0].worldX = gamePanel.getTileSize() * 21;
        gamePanel.npcs[0].worldY = gamePanel.getTileSize() * 21;
    }
}
