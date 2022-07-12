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
        int i = 0;

        gamePanel.monsters[i] = new MON_GreenSlime(gamePanel);
        gamePanel.monsters[i].worldX = gamePanel.getTileSize()*23;
        gamePanel.monsters[i].worldY = gamePanel.getTileSize()*36;

        i++;

        gamePanel.monsters[i] = new MON_GreenSlime(gamePanel);
        gamePanel.monsters[i].worldX = gamePanel.getTileSize()*23;
        gamePanel.monsters[i].worldY = gamePanel.getTileSize()*37;

        i++;

        gamePanel.monsters[i] = new MON_GreenSlime(gamePanel);
        gamePanel.monsters[i].worldX = gamePanel.getTileSize()*23;
        gamePanel.monsters[i].worldY = gamePanel.getTileSize()*38;

        i++;

        gamePanel.monsters[i] = new MON_GreenSlime(gamePanel);
        gamePanel.monsters[i].worldX = gamePanel.getTileSize()*23;
        gamePanel.monsters[i].worldY = gamePanel.getTileSize()*39;
    }
}
