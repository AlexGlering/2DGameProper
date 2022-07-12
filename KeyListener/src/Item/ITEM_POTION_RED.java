package Item;

import Entity.Entity;
import com.example.GamePanel;

public class ITEM_POTION_RED extends Entity {

    int healingValue = 5;

    public ITEM_POTION_RED(GamePanel gamePanel) {
        super(gamePanel);
        name = "Red Potion";
        type = type_consumable;
        down1 = setup("/Object/potion_red", gamePanel.getTileSize(), gamePanel.getTileSize());
        itemDescription = "[Red Potion]/nReplenishes HP by" + healingValue + ".";
    }

    @Override
    public void use(Entity entity){
        gamePanel.gameState = gamePanel.dialogueState;
        gamePanel.ui.currentDialogue = "You drank the " + name + "!/n" +
        "You feel refreshed";

        entity.life += healingValue;
        if(gamePanel.player.life > gamePanel.player.maxLife){
            gamePanel.player.life = gamePanel.player.maxLife;
        }
        gamePanel.playSFX(2);
    }
}
