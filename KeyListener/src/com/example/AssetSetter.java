package com.example;

import Entity.NPC_OldMan;
import Monster.MON_GreenSlime;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setItem() {
        //TODO implement some items
    }

    public void setNPC() {
        gamePanel.npcs[0] = new NPC_OldMan(gamePanel);
        gamePanel.npcs[0].worldX = gamePanel.getTileSize() * 21;
        gamePanel.npcs[0].worldY = gamePanel.getTileSize() * 21;
    }

    public void setMonster(){
        gamePanel.monsters[0] = new MON_GreenSlime(gamePanel);
        gamePanel.monsters[0].worldX = gamePanel.getTileSize()*23;
        gamePanel.monsters[0].worldY = gamePanel.getTileSize()*36;

        gamePanel.monsters[1] = new MON_GreenSlime(gamePanel);
        gamePanel.monsters[1].worldX = gamePanel.getTileSize()*23;
        gamePanel.monsters[1].worldY = gamePanel.getTileSize()*37;
    }
}
