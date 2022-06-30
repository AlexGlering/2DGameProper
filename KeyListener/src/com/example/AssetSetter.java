package com.example;

import Entity.NPC_OldMan;
import Item.ITEM_BOOTS;
import Item.ITEM_CHEST;
import Item.ITEM_DOOR;
import Item.ITEM_KEY;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setItem() {
    }

    public void setNPC() {
        gamePanel.npc[0] = new NPC_OldMan(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.getTileSize() * 21;
        gamePanel.npc[0].worldY = gamePanel.getTileSize() * 21;
    }
}
